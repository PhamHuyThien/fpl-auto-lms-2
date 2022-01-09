
package com.thiendz.tool.fplautolms.utils;

import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Form;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.utils.enums.QuizState;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LmsUtils {

    public static List<Form> buildForm(Document document) {
        Elements elmsForm = document.select("form");
        return elmsForm.stream().map(element -> {
            Form form = new Form();
            form.setMethod(element.attr("method"));
            form.setUrl(element.attr("action"));
            Elements elmsInput = element.select("input");
            HashMap<String, String> hashMap = new HashMap<>();
            for (Element value : elmsInput) {
                hashMap.put(value.attr("name"), value.attr("value"));
            }
            form.setParam(hashMap);
            return form;
        }).collect(Collectors.toList());
    }

    public static QuizState getQuizState(User account, Quiz quiz) throws IOException {
        final String URL_BASE = "%s/ilias.php?ref_id=%s&cmd=infoScreen&cmdClass=ilobjtestgui&cmdNode=q4:ll&baseClass=ilRepositoryGUI";
        String url = String.format(URL_BASE, account.getServer().getUrlServer(), quiz.getId());
        HttpClient client = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        Executor executor = Executor.newInstance(client);
        Request request = Request.Get(url).setHeader("cookie", account.getCookie());
        Response response = executor.execute(request);
        String body = response.returnContent().asString();
        Document document = Jsoup.parse(body);
        return getQuizState(document);
    }

    public static QuizState getQuizState(Document document) {
        try {
            String value = document.selectFirst("input[class='btn btn-default btn-primary']").attr("value");
            if (value.trim().equals("Bắt đầu bài kiểm tra")) {
                return QuizState.NOT_WORKING;
            }
            if (value.trim().equals("Tiếp tục bài kiểm tra")) {
                return QuizState.WORKING;
            }
            if (value.trim().equals("Start New Test Pass")) {
                return QuizState.NEW_WORK;
            }
        } catch (NullPointerException e) {
            return QuizState.FINISH_WORK;
        }
        return null;
    }

    public static int parseRefId(String href) {
        List<String> strRefs = StringUtils.regex("ref_id=([0-9]+)&", href, String.class);
        if (strRefs.size() == 0)
            return -1;
        return Integer.parseInt(strRefs.get(0).replaceAll("ref_id=|&", ""));
    }

}
