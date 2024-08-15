package com.example.planning_worker.utils.enums;

import lombok.Getter;

@Getter
public enum ExpenseStatusCode {
    NEW("New"),
    APPROVED("Approved"),
    DENIED("Denied");

    private final String value;
    ExpenseStatusCode(String value) {
        this.value = value;
    }
}
