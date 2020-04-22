package com.olichid.restfulapi.restfulapi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User Entity")
public class User {

    @ApiModelProperty("User's ID")
    private Long id;
    @ApiModelProperty("User's name")
    private String name;
    @ApiModelProperty("User's age")
    private Integer age;
}