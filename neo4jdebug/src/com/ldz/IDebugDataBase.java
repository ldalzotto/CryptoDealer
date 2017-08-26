package com.ldz;

import org.neo4j.driver.v1.AuthToken;

import java.util.AbstractMap;

/**
 * Created by Loic on 26/08/2017.
 */
public interface IDebugDataBase {

    public void connectTodatabase(String url, AuthToken authToken);

    public void stopDatabase();

    public void addSystem(String name, AbstractMap.SimpleEntry<String, String>... nodeProperties);

    public void addEntity(String name, AbstractMap.SimpleEntry<String, String>... nodeProperties);

    public void createLinkSystemToEntity(String systemName, String entityName);

}
