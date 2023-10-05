package com.safeway.teste.api.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;

    private String type;

    private String title;

    private String detail;

    List<Object> objects;


    @Getter
    @Builder
    static class Object {

        String name;

        String userMessage;

    }

}