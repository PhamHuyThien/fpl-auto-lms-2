package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.RequestUtils;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class AnswerBaseValueService implements Runnable {
    private final static String URL_GET_ANSWER_BASE = "%s/ilias.php?ref_id=%s&active_id=&pass=0&evaluation=%s&cmd=outCorrectSolution&cmdClass=iltestevaluationgui&cmdNode=q4:ll:vx&baseClass=ilRepositoryGUI";

    User user;
    Quiz quiz;
    AnswerBase answerBase;

    Document document;

    public AnswerBaseValueService(User user, Quiz quiz, AnswerBase answerBase) {
        this.user = user;
        this.quiz = quiz;
        this.answerBase = answerBase;
    }

    public AnswerBase getAnswerBase() {
        return answerBase;
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException | LmsException e) {
            answerBase = null;
            log.error(e.toString());
        }
    }

    public void start() throws IOException, LmsException {
        getDocument();
        parseListAnswerText();
        parseBestSolutionId();
    }

    private void getDocument() throws IOException {
        String url = String.format(URL_GET_ANSWER_BASE, user.getServer().getUrlServer(), quiz.getId(), answerBase.getId());
        document = RequestUtils.get(url, user.getCookie());
    }

    private void parseListAnswerText() throws LmsException {
        Element elmTable = document.selectFirst("table[class='nobackground ilClearFloat'],table[class='nobackground middle ilClearFloat']");
        if (elmTable == null)
            throw new LmsException("table[class='nobackground ilClearFloat'],table[class='nobackground middle ilClearFloat'] không tồn tại.");
        Elements elmsRows = elmTable.select("tr");
        List<String> stringList = elmsRows.stream().map(element -> {
            Elements elmsCols = element.select("td");
            if (elmsCols.size() > 1)
                return elmsCols.get(1).text();
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        if (elmsRows.size() != stringList.size())
            throw new LmsException("Thiếu dữ liệu từ bảng.");
        answerBase.setAnswerTextList(stringList);
    }

    private void parseBestSolutionId() throws LmsException {
        String name = String.format("multiple_choice_result_q%s_bestsolution", answerBase.getId());
        Elements elmsBestSolutionIds = document.select(String.format("input[name='%s'][checked]", name));
        List<Integer> integerList = elmsBestSolutionIds.stream().map(element -> {
            String solutionId = element.attr("value");
            try {
                return Integer.parseInt(solutionId);
            } catch (NumberFormatException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        if (elmsBestSolutionIds.size() != integerList.size())
            throw new LmsException("Thiếu dữ liệu từ input.");
        answerBase.setBestSolutionIdList(integerList);
    }


}
