package com.thiendz.tool.fplautolms;

import com.thiendz.tool.fplautolms.services.ServerService;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;
import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import com.thiendz.tool.fplautolms.views.DashboardView;

import java.io.IOException;

public class FplAutoLmsMain {
    public static void main(String[] args) {
        OsUtils.loadEnvironments(args);
        OsUtils.fixHTTPS();
        DashboardView.start();
        try {
            ServerService.start();
        } catch (LmsException e) {
            MsgBoxUtils.alertErr(null, e.toString());
            System.exit(0);
        } catch (IOException e) {
            MsgBoxUtils.alert(null, Messages.CONNECT_TO_SERVER_ERROR);
            System.exit(0);
        }
    }
}
