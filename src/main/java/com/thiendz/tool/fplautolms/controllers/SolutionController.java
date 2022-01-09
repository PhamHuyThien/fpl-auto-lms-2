package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.selenium.impl.LmsChromeDriver;
import com.thiendz.tool.fplautolms.selenium.interf.LmsDriver;
import com.thiendz.tool.fplautolms.services.AnswerBaseService;
import com.thiendz.tool.fplautolms.services.SeleniumSolutionService;
import com.thiendz.tool.fplautolms.services.SeleniumStartQuizService;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;
import com.thiendz.tool.fplautolms.utils.ThreadUtils;
import com.thiendz.tool.fplautolms.utils.except.InputException;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import com.thiendz.tool.fplautolms.views.DashboardView;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SolutionController implements Runnable {
    DashboardView dashboardView;
    User user;
    Course course;
    Quiz quiz;

    List<AnswerBase> answerBaseList;
    Integer quizId;

    public static void start(DashboardView dashboardView) {
        new Thread(new SolutionController(dashboardView)).start();
    }

    public SolutionController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.user = dashboardView.getUser();
        this.course = user.getCourse();
        this.quizId = dashboardView.getCbbQuiz().getSelectedIndex() - 1;
        if (quizId > -1)
            this.quiz = course.getQuizList().get(quizId);
    }

    @Override
    public void run() {
        try {
            checkComboBoxQuiz();
            dashboardView.setEnabledAll(false);
            if (quiz.getAnswerBaseList() == null) {
                startQuiz();
            }
            solution();
        } catch (IOException e) {
            log.error(e.toString());
            dashboardView.setProcess("Kết nối tới máy chủ thất bại.");
        } catch (Exception e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        }
        dashboardView.setEnabledAll(true);
    }

    private void solution() throws LmsException {
        dashboardView.setProcess("Đang giải bài ...");
        AtomicInteger process = new AtomicInteger();
        SeleniumSolutionService seleniumSolutionService = new SeleniumSolutionService(dashboardView.getLmsDriver().getWebDriver(), user, quiz);
        seleniumSolutionService.setSolutionCallback(id -> {
            process.set(id);
            dashboardView.setProcess("Đang giải " + id + "/" + quiz.getAnswerBaseList().size() + " câu hỏi...");
        });
        seleniumSolutionService.solution();
        if (process.get() >= 0) {
            dashboardView.setProcess("Submitting...");
            ThreadUtils.sleep(1000);
        }
        dashboardView.setProcess("Solving " + process.get() + "/" + quiz.getAnswerBaseList().size() + " question done.");
        MsgBoxUtils.alertInf(dashboardView, "Thành công!");
    }

    private void startQuiz() throws Exception {
        StartQuizController startQuizController = new StartQuizController(dashboardView);
        startQuizController.getAnswerBase();
        answerBaseList = startQuizController.getAnswerBaseList();
        quiz.setAnswerBaseList(answerBaseList);
    }

    private void checkComboBoxQuiz() throws InputException {
        if (dashboardView.getCbbQuiz().getSelectedIndex() == 0)
            throw new InputException("Bạn phải chọn 1 quiz!");

        String text = Objects.requireNonNull(dashboardView.getCbbQuiz().getSelectedItem()).toString();
        if (text.contains("(NOT SUPPORT)"))
            throw new InputException("Quiz này không hỗ trợ!");
    }

}
