package com.thiendz.tool.fplautolms.controllers;

import com.thiendz.tool.fplautolms.utils.Messages;
import com.thiendz.tool.fplautolms.utils.OsUtils;

public class ContactController implements Runnable {
    public static void start() {
        new Thread(new ContactController()).start();
    }

    @Override
    public void run() {
        OsUtils.openTabBrowser(Messages.APP_CONTACT);
    }
}
