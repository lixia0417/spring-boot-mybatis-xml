package com.neo.route;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
    	return RoutingDataSourceContext.getDataSourceRoutingKey();
//    	return "masterDataSource";
    }
    
}