package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Server;
import com.thiendz.tool.fplautolms.services.LoginService;
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
            dashboardView.setProcess("Login...");
            login();
            updateDashboard();
            dashboardView.getTfRefIdCourse().setEnabled(true);
            dashboardView.getBtnGetQuiz().setEnabled(true);
            log.info(user.toString());
        } catch (LmsException e) {
            log.error(e.toString());
            dashboardView.setProcess("Cookie sai hoặc hết hạn.");
        } catch (IOException e) {
            log.error(e.toString());
            dashboardView.setProcess("Kết nối tới máy chủ thất bại.");
        } catch (InputException e) {
            log.error(e.toString());
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
            throw new InputException("Cookie không được để trống!");
    }

    private void updateDashboard() {
        dashboardView.setUser(user);
        dashboardView.getLbHello().setText("Hello: " + user.getName());
        dashboardView.getLbEmail().setText("ID: " + user.getId());
        dashboardView.getLbGender().setText("Gender: " + user.getSex());
        dashboardView.getLbRole().setText("Role: " + user.getRole());
        dashboardView.setProcess("Login success!");
    }
}
