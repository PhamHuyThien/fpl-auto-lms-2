package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.LmsUtils;
import com.thiendz.tool.fplautolms.utils.enums.QuizState;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumStartQuizService {
    private static final String URL_BASE = "%s/ilias.php?ref_id=%s&cmd=infoScreen&cmdClass=ilobjtestgui&cmdNode=q4:ll&baseClass=ilRepositoryGUI";

    WebDriver webDriver;
    User user;
    Quiz quiz;

    public SeleniumStartQuizService(WebDriver webDriver, User user, Quiz quiz) {
        this.webDriver = webDriver;
        this.user = user;
        this.quiz = quiz;
    }

    public void start() throws LmsException {
        String url = String.format(URL_BASE, user.getServer().getUrlServer(), quiz.getId());
        if(webDriver == null)
            throw new LmsException("Webdriver chưa được khởi tạo.");
        if (webDriver.getCurrentUrl().equals(url)) {
            webDriver.navigate().refresh();
        } else {
            webDriver.navigate().to(url);
        }
        QuizState quizState = LmsUtils.getQuizState(Jsoup.parse(webDriver.getPageSource()));
        if (quizState != QuizState.NOT_WORKING)
            throw new LmsException("quiz state not working");
        try {
            WebElement webElmStart = webDriver.findElement(By.xpath("//input[@class='btn btn-default btn-primary']"));
            webElmStart.click();
        } catch (Exception e) {
            throw new LmsException("input[@class='btn btn-default btn-primary'] không tồn tại.");
        }
    }
}
