package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.utils.RequestUtils;
import com.thiendz.tool.fplautolms.utils.ThreadUtils;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnswerBaseService {
    private static final String URL_HACK_QUIZ_BASE = "%s/ilias.php?ref_id=%s&active_id=&pass=0&cmd=outUserPassDetails&cmdClass=iltestevaluationgui&cmdNode=q4:ll:vx&baseClass=ilRepositoryGUI";

    User user;
    Quiz quiz;

    Document document;
    List<AnswerBase> answerBaseList;

    public AnswerBaseService(User user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public List<AnswerBase> getAnswerBaseList() {
        return answerBaseList;
    }

    public void start() throws LmsException, IOException {
        getDocument();
        getAnswerBase();
    }

    private void getDocument() throws IOException {
        String url = String.format(URL_HACK_QUIZ_BASE, user.getServer().getUrlServer(), quiz.getId());
        document = RequestUtils.get(url, user.getCookie());
    }

    private void getAnswerBase() throws LmsException {
        Element elmTbody = document.selectFirst("tbody");
        assert elmTbody != null;
        Elements elmsTr = elmTbody.select("tr");
        answerBaseList = elmsTr.stream().map(element -> {
            Elements elmsTd = element.select("td");
            if (elmsTd.size() < 3)
                return null;
            AnswerBase answerBase = new AnswerBase();
            answerBase.setId(Integer.parseInt(elmsTd.get(1).text()));
            answerBase.setQuestion(Objects.requireNonNull(elmsTd.get(2).selectFirst("a")).text());
            return answerBase;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        if (elmsTr.size() != answerBaseList.size())
            throw new LmsException("Không lấy được dữ liệu bảng.");
    }

    public void getAnswerBaseValue() {
        List<AnswerBaseValueService> answerBaseValueServiceList = answerBaseList.stream()
                .map(answerBase -> new AnswerBaseValueService(user, quiz, answerBase))
                .collect(Collectors.toList());
        ThreadUtils threadUtils = new ThreadUtils(answerBaseValueServiceList, answerBaseValueServiceList.size());
        threadUtils.execute();
        threadUtils.await();
        answerBaseList = answerBaseValueServiceList.stream()
                .map(AnswerBaseValueService::getAnswerBase)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Collections.sort(answerBaseList);
    }
}
