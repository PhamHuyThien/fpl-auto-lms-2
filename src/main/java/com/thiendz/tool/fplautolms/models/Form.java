
package com.thiendz.tool.fplautolms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Form {

    private String url;
    private String method;
    private HashMap param;

}
