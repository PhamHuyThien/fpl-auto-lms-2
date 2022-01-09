package com.thiendz.tool.fplautolms.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class RequestUtils {
    public static Document get(String url, String cookie) throws IOException {
        log.info("Request GET: {}", url);
        HttpClient client = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        Executor executor = Executor.newInstance(client);
        Request request = Request.Get(url).setHeader("cookie", cookie);
        String body = executor.execute(request).returnContent().asString();
        return Jsoup.parse(body);
    }
}
