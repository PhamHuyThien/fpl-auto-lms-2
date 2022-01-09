package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.dto.callback.SolutionCallback;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.LmsUtils;
import com.thiendz.tool.fplautolms.utils.ThreadUtils;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.enums.QuizState;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumSolutionService {
    private static final String URL_BASE = "%s/ilias.php?ref_id=%s&cmd=infoScreen&cmdClass=ilobjtestgui&cmdNode=q4:ll&baseClass=ilRepositoryGUI";

    WebDriver webDriver;
    User user;
    Quiz quiz;
    SolutionCallback solutionCallback;

    public SeleniumSolutionService(WebDriver webDriver, User user, Quiz quiz) {
        this.webDriver = webDriver;
        this.user = user;
        this.quiz = quiz;
        this.solutionCallback = id -> {
        };
    }

    public void setSolutionCallback(SolutionCallback solutionCallback) {
        this.solutionCallback = solutionCallback;
    }

    public void solution() throws LmsException {
        if(webDriver == null)
            throw new LmsException(Messages.DRIVER_NOT_INSTALL);
        String url = String.format(URL_BASE, user.getServer().getUrlServer(), quiz.getId());
        if (webDriver.getCurrentUrl().equals(url)) {
            webDriver.navigate().refresh();
        } else {
            webDriver.navigate().to(url);
        }
        QuizState quizState = LmsUtils.getQuizState(Jsoup.parse(webDriver.getPageSource()));
        if (quizState == QuizState.FINISH_WORK) {
            solutionCallback.updateProcess(-1);
            return;
        }
        if (quizState == QuizState.NOT_WORKING) {
            solutionCallback.updateProcess(-2);
            return;
        }
        if (quizState == QuizState.NEW_WORK) {
            solutionCallback.updateProcess(-3);
            return;
        }
        boolean firstSolution = true;
        //button bắt đầu
        WebElement webElmStart = webDriver.findElement(By.xpath("//input[@class='btn btn-default btn-primary']"));
        webElmStart.click();
        //load qua tất cả các câu hỏi
        int totalProcess = -1;
        if (quiz.getAnswerBaseList() != null) {
            totalProcess = quiz.getAnswerBaseList().size();
        }
        for (int i = 0; i < totalProcess; i++) {
            //select radio best choice
            List<Integer> bestSolutionList = quiz.getAnswerBaseList().get(i).getBestSolutionIdList();
            String inputType = bestSolutionList.size() > 1 ? "checkbox" : "radio";
            for (Integer bestSolution : bestSolutionList) {
                String XPATH = "//input[@type='%s'][@value='%d']";
                XPATH = String.format(XPATH, inputType, bestSolution);
                WebElement webElmBestChoice = webDriver.findElement(By.xpath(XPATH));
                webElmBestChoice.click();
                ThreadUtils.sleep(200);
            }
            try { // click next
                WebElement webElmButtonNext = webDriver.findElement(By.xpath("//a[@id='bottomnextbutton']"));
                webElmButtonNext.click();
            } catch (Exception e) { // next not found
                //click end of test
                WebElement webElmButtonEnd = webDriver.findElement(By.xpath("//li[@class='ilToolbarStickyItem']"));
                webElmButtonEnd.click();

                solutionCallback.updateProcess(totalProcess);
                break;
            }
            if (firstSolution) {
                firstSolution = false;
                ThreadUtils.sleep(500);
                try {
                    //turn off notifications
                    WebElement webElmDontShowMes = webDriver.findElement(By.xpath("//input[@type='checkbox']"));
                    webElmDontShowMes.click();
                    WebElement webElmSend = webDriver.findElement(By.xpath("//a[@id='tst_save_on_navigation_button']"));
                    webElmSend.click();
                } catch (Exception ignored) {
                }
            }

            solutionCallback.updateProcess(i + 1);
        }
        try { // click yes, i am want to finish
            WebElement webElmYesFinish = webDriver.findElement(By.xpath("//input[@class='btn btn-default']"));
            webElmYesFinish.click();
        } catch (Exception ignored) {
        }
    }
}
