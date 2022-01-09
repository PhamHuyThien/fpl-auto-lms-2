package com.thiendz.tool.fplautolms.selenium.interf;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public interface LmsDriver {
    void init() throws FileNotFoundException;
    WebDriver getWebDriver();
}
