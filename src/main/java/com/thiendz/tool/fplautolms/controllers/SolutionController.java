package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.services.SeleniumSolutionService;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;
import com.thiendz.tool.fplautolms.utils.ThreadUtils;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
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
            dashboardView.setProcess(Messages.CONNECT_TO_SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        }
        dashboardView.setEnabledAll(true);
    }

    private void solution() throws LmsException {
        dashboardView.setProcess(Messages.WAIT_SOLUTION);
        AtomicInteger process = new AtomicInteger();
        SeleniumSolutionService seleniumSolutionService = new SeleniumSolutionService(dashboardView.getLmsDriver().getWebDriver(), user, quiz);
        seleniumSolutionService.setSolutionCallback(id -> {
            process.set(id);
            dashboardView.setProcess(String.format(Messages.PROCESS_SOLUTION, id, quiz.getAnswerBaseList().size()));
        });
        seleniumSolutionService.solution();
        if (process.get() >= 0) {
            dashboardView.setProcess(Messages.SUBMITTING);
            ThreadUtils.sleep(1000);
        }
        dashboardView.setProcess(String.format(Messages.PROCESS_SOLUTION, process.get(), quiz.getAnswerBaseList().size()));
        MsgBoxUtils.alertInf(dashboardView, Messages.SUCCESS);
    }

    private void startQuiz() throws Exception {
        StartQuizController startQuizController = new StartQuizController(dashboardView);
        startQuizController.getAnswerBase();
        answerBaseList = startQuizController.getAnswerBaseList();
        quiz.setAnswerBaseList(answerBaseList);
    }

    private void checkComboBoxQuiz() throws InputException {
        if (dashboardView.getCbbQuiz().getSelectedIndex() == 0)
            throw new InputException(Messages.YOU_MUST_CHOOSE_ONE_QUIZ);

        String text = Objects.requireNonNull(dashboardView.getCbbQuiz().getSelectedItem()).toString();
        if (text.contains(Messages.NOT_SUPPORT))
            throw new InputException(Messages.QUIZ_NOT_SUPPORT);
    }

}
