
package com.thiendz.tool.fplautolms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Server server;
    private String cookie;
    private String username;
    private String password;
    private String name;
    private String id;
    private String sex;
    private String role;
    private String birthday;
    private String email;
    Course course;
}
