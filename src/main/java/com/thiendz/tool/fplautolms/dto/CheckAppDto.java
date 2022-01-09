package com.thiendz.tool.fplautolms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckAppDto extends ResponseDto<ToolInfoDto> {
}
