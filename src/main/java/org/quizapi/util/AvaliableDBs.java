package org.quizapi.util;

public enum AvaliableDBs {
    SQLITE("sqlite:"),
    MYSQL("mysql:"),
    POSTEGRESQL("postegresql:");

    private final String dbName;

    AvaliableDBs(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName ;
    }

    public String toString(){
        return getDbName();
    }
}
