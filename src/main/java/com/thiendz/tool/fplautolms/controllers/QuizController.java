package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.services.CourseService;
import com.thiendz.tool.fplautolms.services.QuizService;
import com.thiendz.tool.fplautolms.utils.LmsUtils;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.except.InputException;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import com.thiendz.tool.fplautolms.views.DashboardView;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class QuizController implements Runnable {
    DashboardView dashboardView;
    User user;
    Course course;

    Integer refId;

    public static void start(DashboardView dashboardView) {
        new Thread(new QuizController(dashboardView)).start();
    }

    public QuizController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.user = dashboardView.getUser();
    }

    @Override
    public void run() {
        try {
            dashboardView.setEnabledAll(false);
            checkFormGetQuiz();
            dashboardView.setProcess(Messages.WAIT_GET_LIST_QUIZ);
            getCourse();
            getQuiz();
            updateDashboard();
            user.setCourse(course);
            dashboardView.setEnabledAll(true);
        } catch (LmsException | InputException e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
            dashboardView.setProcess(Messages.CONNECT_TO_SERVER_ERROR);
        }
        dashboardView.getTfCookie().setEnabled(true);
        dashboardView.getCbbServer().setEnabled(true);
        dashboardView.getBtnLogin().setEnabled(true);
        dashboardView.getTfRefIdCourse().setEnabled(true);
        dashboardView.getBtnGetQuiz().setEnabled(true);
    }

    private void getQuiz() throws LmsException, IOException {
        try {
            QuizService quizService = new QuizService(user, course);
            quizService.start();
            course.setQuizList(quizService.getQuizList());
        } catch (LmsException e) {
            log.error(e.toString());
            throw new LmsException(Messages.LINK_REF_ID_WRONG_FORMAT);
        }
    }

    private void getCourse() {
        CourseService courseService = new CourseService(user, refId);
        courseService.start();
        course = courseService.getCourse();
    }

    private void checkFormGetQuiz() throws InputException {
        String refIdStr = dashboardView.getTfRefIdCourse().getText().trim();
        int id = LmsUtils.parseRefId(refIdStr);
        if (id == -1)
            throw new InputException(Messages.LINK_REF_ID_WRONG_FORMAT);
        refId = id;
    }

    private void updateDashboard() {
        dashboardView.getCbbQuiz().removeAllItems();
        dashboardView.getCbbQuiz().addItem(Messages.SELECT_QUIZ);
        for (Quiz quiz : course.getQuizList()) {
            String isSupport = quiz.isAutomationSupport() ? "" : Messages.NOT_SUPPORT;
            dashboardView.getCbbQuiz().addItem(quiz.getName() + " " + isSupport);
        }
        dashboardView.setProcess(Messages.GET_LIST_QUIZ_SUCCESS);
    }
}
