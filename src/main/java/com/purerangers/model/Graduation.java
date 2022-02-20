package com.purerangers.model;

import java.sql.Date;

@FunctionalInterface
public interface Graduation {
    boolean checkGraduation(Date date);
}
