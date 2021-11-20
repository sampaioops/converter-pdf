package com.sampaio.converterpdf.configuration.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<Route> DELEGATE = new ThreadLocal<>();

    public enum Route {
        PRIMARY, REPLICA
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DELEGATE.get();
    }

    public void unload() {
        DELEGATE.remove();
    }
}
