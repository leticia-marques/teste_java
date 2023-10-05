package com.safeway.teste.api.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("recurso nao encontrado"),

    INVALID_DATA("dados invalidos");


    private String title;

    ProblemType(String title) {
       this.title = title;
    }
}

