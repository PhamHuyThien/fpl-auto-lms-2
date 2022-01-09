package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.LmsUtils;
import com.thiendz.tool.fplautolms.utils.RequestUtils;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class QuizService {
    private static final String URL_QUIZ_BASE = "%s/ilias.php?ref_id=%s&cmd=view&cmdClass=ilrepositorygui&cmdNode=q4&baseClass=ilRepositoryGUI";

    User user;
    Course course;
    List<Quiz> quizList;

    Document document;

    public QuizService(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void start() throws LmsException, IOException {
        getDocument();
        parseQuizList();
    }

    private void getDocument() throws IOException {
        String url = String.format(URL_QUIZ_BASE, user.getServer().getUrlServer(), course.getId());
        document = RequestUtils.get(url, user.getCookie());
    }

    private void parseQuizList() throws LmsException {
        Elements elmsItemContent = document.select("div[class='ilContainerListItemContent ']");
        if (elmsItemContent.isEmpty())
            throw new LmsException("div[class='ilContainerListItemContent '] không có phần tử nào.");
        quizList = elmsItemContent.stream().map(element -> {
            Element elmLink = element.selectFirst("a");
            Element elmDesc = element.selectFirst("div[class='ilListItemSection il_Description']");
            assert elmLink != null;
            assert elmDesc != null;
            Quiz quiz = new Quiz();
            quiz.setId(LmsUtils.parseRefId(elmLink.attr("href")));
            quiz.setName(elmLink.text().trim());
            quiz.setAutomationSupport(elmDesc.text().trim().equals(""));
            return quiz;
        }).collect(Collectors.toList());
    }
}
