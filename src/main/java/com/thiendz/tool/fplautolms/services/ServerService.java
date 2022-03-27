package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.dto.CheckAppDto;
import com.thiendz.tool.fplautolms.dto.IpInfoDto;
import com.thiendz.tool.fplautolms.dto.VoidResponseDto;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.models.Version;
import com.thiendz.tool.fplautolms.utils.MapperUtils;
import com.thiendz.tool.fplautolms.utils.OsUtils;
import com.thiendz.tool.fplautolms.utils.consts.Environments;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import com.thiendz.tool.fplautolms.utils.except.LmsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

@Slf4j
public class ServerService {

    public static ServerService serverService;

    private static final String SERVER_ADDRESS = "https://poly.thien.biz";
    private static final String SERVER_API = SERVER_ADDRESS + "/api/index.php";

    private Integer appId;

    public static void start() throws LmsException, IOException {
        serverService = new ServerService();
        serverService.init();
    }

    public Boolean pushAnalysis(User user) throws IOException {
        if (Environments.DISABLE_ANALYSIS)
            return false;

        IpInfoDto ipInfo = OsUtils.getIpInfo();
        String url = SERVER_API + "?c=push-analysis&id-tool=" + appId;
        String send = String.format("user=%s&ip=%s&city=%s&region=%s&country=%s&timezone=%s",
                user.getEmail(),
                ipInfo.getIp(),
                ipInfo.getCity(),
                ipInfo.getRegion(),
                ipInfo.getCountry(),
                ipInfo.getTimezone()
        );

        String body = postRequest(url, send);

        log.info("Request POST: {}", url);
        log.info("Request send: {}", send);
        log.info("Response: {}", body);

        return MapperUtils.objectMapper.readValue(body, VoidResponseDto.class).getStatus() == 1;
    }

    public void init() throws IOException, LmsException {
        if (Environments.DISABLE_ANALYSIS)
            return;

        String url = SERVER_API + "?c=get-tool-info";
        String send = "name=" + Messages.APP_NAME;

        String body = postRequest(url, send);

        log.info("Request POST: {}", url);
        log.info("Request send: {}", send);
        log.info("Response: {}", body);

        CheckAppDto checkAppDto = MapperUtils.objectMapper.readValue(body, CheckAppDto.class);

        if (checkAppDto.getStatus() == 0)
            throw new LmsException(Messages.TOOL_MAINTAIN);

        String strVerOld = Messages.APP_VER;
        String strVerNew = checkAppDto.getData().getVersion();
        if (new Version(strVerNew).compareTo(new Version(strVerOld)) > 0)
            throw new LmsException(String.format(Messages.VERSION_OLD, strVerOld, strVerNew, SERVER_ADDRESS));

        appId = checkAppDto.getData().getId();
    }

    private String postRequest(String url, String data) throws IOException {
        HttpClient client = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        Executor executor = Executor.newInstance(client);
        Request request = Request.Post(url).bodyString(data, ContentType.APPLICATION_FORM_URLENCODED);
        return executor.execute(request)
                .returnContent()
                .asString();
    }
}
