package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Server;
import com.thiendz.tool.fplautolms.utils.RequestUtils;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class LoginService {
    private final static String URL_PERSONAL_PROFILE_BASE = "%s/ilias.php?cmdClass=ilpersonalprofilegui&cmdNode=oq:or&baseClass=ilPersonalDesktopGUI";

    Server server;
    String cookie;
    User user;

    Document document;

    public User getUser() {
        return user;
    }

    public LoginService(Server server, String cookie) {
        this.server = server;
        this.cookie = cookie;
    }

    public void login() throws IOException, LmsException {
        getDocument();
        getData();
    }

    private void getDocument() throws IOException {
        String url = String.format(URL_PERSONAL_PROFILE_BASE, server.getUrlServer());
        document = RequestUtils.get(url, cookie);
    }

    private void getData() throws LmsException {
        user = new User();
        user.setServer(server);
        user.setCookie(cookie);
        user.setName(Objects.requireNonNull(document.selectFirst("input[id='usr_lastname']")).attr("value"));
        user.setBirthday(Objects.requireNonNull(document.selectFirst("input[name='usr_birthday']")).attr("value"));
        user.setSex(Objects.requireNonNull(document.selectFirst("input[name='usr_gender']")).attr("value"));
        user.setRole(Objects.requireNonNull(document.selectFirst("span[id='ne_dr']")).text());
        user.setEmail(Objects.requireNonNull(document.selectFirst("input[id='usr_email']")).attr("value"));
        if (user.getEmail() == null)
            throw new LmsException("input[id='usr_email'] không tồn tại.");
        user.setId(user.getEmail().replaceAll("@fpt\\.edu\\.vn", ""));
    }
}
