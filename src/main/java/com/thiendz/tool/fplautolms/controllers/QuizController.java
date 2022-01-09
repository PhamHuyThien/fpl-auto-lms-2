package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.services.CourseService;
import com.thiendz.tool.fplautolms.services.QuizService;
import com.thiendz.tool.fplautolms.utils.LmsUtils;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;
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
            checkFormGetQuiz();
            dashboardView.setEnabledAll(false);
            dashboardView.setProcess("Get list quiz ...");
            getCourse();
            getQuiz();
            updateDashboard();
            user.setCourse(course);
        } catch (LmsException e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
            dashboardView.setProcess("Không thể request tới server.");
        } catch (InputException e) {
            log.error(e.toString());
            dashboardView.setProcess(e.toString());
        }
        dashboardView.setEnabledAll(true);
    }

    private void getQuiz() throws LmsException, IOException {
        try {
            QuizService quizService = new QuizService(user, course);
            quizService.start();
            course.setQuizList(quizService.getQuizList());
        } catch (LmsException e) {
            log.error(e.toString());
            throw new LmsException("RefId không hợp lệ");
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
            throw new InputException("RefId không hợp lệ!");
        refId = id;
    }

    private void updateDashboard() {
        dashboardView.getCbbQuiz().removeAllItems();
        dashboardView.getCbbQuiz().addItem("Select quiz...");
        for (Quiz quiz : course.getQuizList()) {
            String isSupport = quiz.isAutomationSupport() ? "" : "(NOT SUPPORT)";
            dashboardView.getCbbQuiz().addItem(quiz.getName() + " " + isSupport);
        }
        dashboardView.setProcess("Get list quiz done.");
    }
}
