package com.purerangers.database;

@FunctionalInterface
public interface SQLReadable {
    Object[] readSQLObject();
}
