package com.example.planning_worker.utils.enums;

import java.time.LocalDateTime;

public enum TermDuration {
    MONTHLY {
        @Override
        public LocalDateTime calculateEndDate(LocalDateTime startDate) {
            return startDate.plusMonths(1);
        }
    },
    QUARTERLY {
        @Override
        public LocalDateTime calculateEndDate(LocalDateTime startDate) {
            return startDate.plusMonths(3);
        }
    },
    HALF_YEARLY {
        @Override
        public LocalDateTime calculateEndDate(LocalDateTime startDate) {
            return startDate.plusMonths(6);
        }
    };
    private String value;


    public abstract LocalDateTime calculateEndDate(LocalDateTime startDate);
}