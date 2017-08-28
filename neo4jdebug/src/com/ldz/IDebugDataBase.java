package com.ldz;

import org.neo4j.driver.v1.AuthToken;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Loic on 26/08/2017.
 */
public interface IDebugDataBase {

    public void connectTodatabase(String url, AuthToken authToken);

    public void stopDatabase();

    public void addSystem(String name);

    public void addEntity(String name);

    public void addEntity(String name, Map<String, String> parameters);

    public void addParamterToNode(String nodeName, String nodeTag, AbstractMap.SimpleEntry<String, String> attribute);

    public void createLinkSystemToEntity(String systemName, String entityName);

    public void deleteNode(String nodeName);

}
