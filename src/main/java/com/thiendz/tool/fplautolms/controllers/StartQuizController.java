package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.lib.driverdl.impl.SeleniumChromeDriverDL;
import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;
import com.thiendz.lib.driverdl.utils.enums.OsType;
import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.selenium.impl.LmsChromeDriver;
import com.thiendz.tool.fplautolms.selenium.interf.LmsDriver;
import com.thiendz.tool.fplautolms.services.AnswerBaseService;
import com.thiendz.tool.fplautolms.services.SeleniumStartQuizService;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;
import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import com.thiendz.tool.fplautolms.views.DashboardView;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
public class StartQuizController {
    DashboardView dashboardView;
    User user;
    Course course;
    Quiz quiz;
    List<AnswerBase> answerBaseList;

    public List<AnswerBase> getAnswerBaseList() {
        return answerBaseList;
    }

    public StartQuizController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.user = dashboardView.getUser();
        this.course = user.getCourse();
        int quizId = dashboardView.getCbbQuiz().getSelectedIndex() - 1;
        if (quizId > -1)
            this.quiz = course.getQuizList().get(quizId);
    }

    public void getAnswerBase() throws Exception {
        dashboardView.setProcess("Lấy danh sách câu hỏi...");
        AnswerBaseService answerBaseService = new AnswerBaseService(user, quiz);
        do {
            try {
                answerBaseService.start();
                break;
            } catch (LmsException ex) {
                if (dashboardView.getCbAutoStartQuiz().isSelected()) {
                    loadDriver();
                    startQuiz();
                } else
                    throw new LmsException("Quiz chưa được bắt đầu hoặc hết hạn.");
            }
        } while (true);
        dashboardView.setProcess("Lấy danh sách đáp án...");
        answerBaseService.getAnswerBaseValue();
        answerBaseList = answerBaseService.getAnswerBaseList();
    }

    private void startQuiz() throws LmsException {
        dashboardView.setProcess("Bắt đầu làm bài...");
        SeleniumStartQuizService seleniumStartQuizService = new SeleniumStartQuizService(dashboardView.getLmsDriver().getWebDriver(), user, quiz);
        try {
            seleniumStartQuizService.start();
        } catch (LmsException e) {
            log.error(e.toString());
            throw new LmsException("Không thể bắt đầu làm bài, có thể bài này đã được làm rồi.");
        }
    }

    private void loadDriver() throws Exception {
        dashboardView.setProcess("Khởi động driver...");
        if (dashboardView.getLmsDriver() == null) {
            LmsDriver lmsDriver = new LmsChromeDriver(user);
            do {
                try {
                    lmsDriver.init();
                    dashboardView.setLmsDriver(lmsDriver);
                } catch (FileNotFoundException e) {
                    if (MsgBoxUtils.confirmWar(dashboardView, "Hiện tại bạn chưa cài đặt driver.\nBạn muốn tự động cài driver chứ?")) {
                        downloadDriver();
                        continue;
                    } else
                        throw new LmsException("Driver chưa được cài đặt.");
                }
                break;
            } while (true);
        }
    }

    private void downloadDriver() throws Exception {
        dashboardView.setProcess("Bắt đầu tải về driver...");
        String path = OsUtils.getScriptDir() + "\\driver";
        SeleniumDriverDL seleniumDriverDL = new SeleniumChromeDriverDL();
        seleniumDriverDL.setPathSave(path);
        seleniumDriverDL.setOsType(OsType.OS_WINDOW_32);
        seleniumDriverDL.init();
        seleniumDriverDL.download();
    }
}
