package com.thiendz.tool.fplautolms.selenium.impl;

import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.selenium.interf.LmsDriver;
import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.utils.consts.Environments;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LmsChromeDriver implements LmsDriver {
    private static final String PATH_DRIVER = OsUtils.getScriptDir() + "\\driver\\chromedriver.exe";
    User user;
    ChromeDriver chromeDriver;

    public LmsChromeDriver(User user) {
        this.user = user;
    }

    @Override
    public void init() throws FileNotFoundException {
        if (!checkDriverExists())
            throw new FileNotFoundException(Messages.DRIVER_NOT_INSTALL);

        System.setProperty("webdriver.chrome.driver", PATH_DRIVER);

        ChromeOptions chromeOptions = new ChromeOptions();
        if (!Environments.SHOW_IMAGE) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.managed_default_content_settings.images", 2);
            chromeOptions.setExperimentalOption("prefs", prefs);
        }
        if (Environments.FULL_SCREEN) {
            chromeOptions.addArguments("start-maximized");
        }
        if (!Environments.ENABLE_AUTOMATION) {
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }

        chromeDriver = new ChromeDriver(chromeOptions);

        if (!Environments.SHOW_BROWSER) {
            chromeDriver.manage().window().setPosition(new Point(-32000, -32000));
        }

        chromeDriver.get(user.getServer().getUrlServer());

        Cookie[] cookies = parseCookies();
        for (Cookie cookie : cookies) {
            chromeDriver.manage().addCookie(cookie);
        }
        chromeDriver.navigate().refresh();
    }

    @Override
    public WebDriver getWebDriver() {
        return chromeDriver;
    }

    private boolean checkDriverExists() {
        return new File(PATH_DRIVER).exists();
    }

    private Cookie[] parseCookies() {
        String[] strCookies = user.getCookie().replaceAll("\\s|cookie:", "").split(";");
        Cookie[] cookies = new Cookie[strCookies.length];
        for (int i = 0; i < cookies.length; i++) {
            String[] pairs = strCookies[i].split("=");
            cookies[i] = new Cookie(pairs[0].trim(), pairs.length > 1 ? pairs[1].trim() : "");
        }
        return cookies;
    }
}
