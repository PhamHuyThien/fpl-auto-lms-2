package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.utils.consts.Messages;

public class ContactController implements Runnable {
    public static void start() {
        new Thread(new ContactController()).start();
    }

    @Override
    public void run() {
        OsUtils.openTabBrowser(Messages.APP_CONTACT);
    }
}
