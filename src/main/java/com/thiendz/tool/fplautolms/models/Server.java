
package com.thiendz.tool.fplautolms.models;

import com.thiendz.tool.fplautolms.utils.enums.ServerName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Server {

    private static final String URL_BASE_SERVER = "http://%s-lms.poly.edu.vn";
    private final String urlServer;

    public Server(ServerName serverName) {
        this.urlServer = String.format(URL_BASE_SERVER, serverName.toString());
    }
}
