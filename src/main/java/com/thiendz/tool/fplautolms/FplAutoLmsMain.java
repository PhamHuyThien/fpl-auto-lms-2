package com.thiendz.tool.fplautolms;

import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.views.DashboardView;

public class FplAutoLmsMain {
    public static void main(String[] args) {
        OsUtils.loadEnvironments(args);
        OsUtils.fixHTTPS();
        DashboardView.start();
    }
}
