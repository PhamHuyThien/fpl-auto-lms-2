package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Server;
import com.thiendz.tool.fplautolms.services.LoginService;
import com.thiendz.tool.fplautolms.services.ServerService;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.enums.ServerName;
import com.thiendz.tool.fplautolms.utils.except.InputException;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import com.thiendz.tool.fplautolms.views.DashboardView;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class LoginController implements Runnable {
    DashboardView dashboardView;
    User user;
    Server server;
    String cookie;

    public static void start(DashboardView dashboardView) {
        new Thread(new LoginController(dashboardView)).start();
    }

    public LoginController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        server = new Server(Objects.requireNonNull(toServerName()));
        cookie = dashboardView.getTfCookie().getText().trim();
    }

    @Override
    public void run() {
        try {
            checkFormLogin();
            dashboardView.setEnabledAll(false);
            dashboardView.setProcess(Messages.WAIT_LOGIN);
            login();
            ServerService.serverService.pushAnalysis(user);
            updateDashboard();
            dashboardView.getTfRefIdCourse().setEnabled(true);
            dashboardView.getBtnGetQuiz().setEnabled(true);
            log.info(user.toString());
        } catch (IOException | LmsException e) {
            log.error(e.toString());
            reset();
            dashboardView.setProcess(Messages.LOGIN_FAIL);
        } catch (InputException e) {
            log.error(e.toString());
            reset();
            dashboardView.setProcess(e.toString());
        }
        dashboardView.getTfCookie().setEnabled(true);
        dashboardView.getCbbServer().setEnabled(true);
        dashboardView.getBtnLogin().setEnabled(true);
    }

    private void login() throws LmsException, IOException {
        LoginService loginService = new LoginService(server, cookie);
        loginService.login();
        user = loginService.getUser();
    }

    private ServerName toServerName() {
        String name = Objects.requireNonNull(dashboardView.getCbbServer().getSelectedItem()).toString();
        return ServerName.toServerName(name);
    }

    private void checkFormLogin() throws InputException {
        if (dashboardView.getTfCookie().getText().trim().equals(""))
            throw new InputException(Messages.COOKIE_NOT_EMPTY);
    }

    private void reset() {
        dashboardView.getLbHello().setText(Messages.HELLO);
        dashboardView.getLbEmail().setText(Messages.ID);
        dashboardView.getLbGender().setText(Messages.GENDER);
        dashboardView.getLbRole().setText(Messages.ROLE);
    }

    private void updateDashboard() {
        dashboardView.setUser(user);
        dashboardView.getLbHello().setText(Messages.HELLO + user.getName());
        dashboardView.getLbEmail().setText(Messages.ID + user.getId());
        dashboardView.getLbGender().setText(Messages.GENDER + user.getSex());
        dashboardView.getLbRole().setText(Messages.ROLE + user.getRole());
        dashboardView.setProcess(Messages.LOGIN_SUCCESS);
    }
}
