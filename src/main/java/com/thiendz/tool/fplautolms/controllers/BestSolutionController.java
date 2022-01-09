package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.except.InputException;
import com.thiendz.tool.fplautolms.views.AnswerView;
import com.thiendz.tool.fplautolms.views.DashboardView;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class BestSolutionController implements Runnable {
    DashboardView dashboardView;
    User user;
    Course course;
    Quiz quiz;
    List<AnswerBase> answerBaseList;
    Integer quizId;

    public static void start(DashboardView dashboardView) {
        new Thread(new BestSolutionController(dashboardView)).start();
    }

    public BestSolutionController(DashboardView dashboardView) {
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
                StartQuizController startQuizController = new StartQuizController(dashboardView);
                startQuizController.getAnswerBase();
                answerBaseList = startQuizController.getAnswerBaseList();
            }
            updateDashboard();
            updateAnswer();
        } catch (IOException e) {
            log.error(e.toString());
            dashboardView.setProcess(Messages.CONNECT_TO_SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        }
        dashboardView.setEnabledAll(true);
    }


    private void checkComboBoxQuiz() throws InputException {
        if (quizId < 0)
            throw new InputException(Messages.YOU_MUST_CHOOSE_ONE_QUIZ);

        String text = Objects.requireNonNull(dashboardView.getCbbQuiz().getSelectedItem()).toString();
        if (text.contains(Messages.NOT_SUPPORT))
            throw new InputException(Messages.QUIZ_NOT_SUPPORT);
    }

    private void updateDashboard() {
        if (quiz.getAnswerBaseList() == null) {
            quiz.setAnswerBaseList(answerBaseList);
            course.getQuizList().set(quizId, quiz);
            user.setCourse(course);
            dashboardView.setUser(user);
            answerBaseList.forEach(answerBase -> log.info(answerBase.toString()));
        }
        dashboardView.setProcess(Messages.SUCCESS);
    }

    private void updateAnswer() {
        if (dashboardView.getAnswerView() == null)
            dashboardView.setAnswerView(new AnswerView());
        dashboardView.getAnswerView().setDashboardView(dashboardView);
        dashboardView.getAnswerView().setQuiz(quiz);
        dashboardView.getAnswerView().updateComponent();
        dashboardView.getAnswerView().setVisible(true);
        dashboardView.setVisible(false);
    }
}
