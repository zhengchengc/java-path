package com.olichid.restfulapi.restfulapi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "User Entity")
public class User {

    @ApiModelProperty("User's ID")
    private Long id;

    @NotNull
    @Size(min = 2, max = 10)
    @ApiModelProperty("User's name")
    private String name;

    @NotNull
    @Max(130)
    @Min(18)
    @ApiModelProperty("User's age")
    private Integer age;

    @NotNull
    @Email
    @ApiModelProperty("User Email")
    private String email;
}