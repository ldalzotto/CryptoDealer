package com.ldz;

import org.neo4j.driver.v1.*;

import java.util.AbstractMap;

/**
 * Created by Loic on 26/08/2017.
 */
public class DebugDataBase implements IDebugDataBase {

    private static DebugDataBase instance;
    private Driver driver;
    private Session session;

    public static DebugDataBase getInstance() {
        if (instance == null) {
            instance = new DebugDataBase();
        }
        return instance;
    }

    @Override
    public void connectTodatabase(String url, AuthToken authToken) {
        this.driver = GraphDatabase.driver(url, authToken);
        this.session = driver.session();
        this.session.run("MATCH (n)\n" +
                "DETACH DELETE n");
    }

    @Override
    public void stopDatabase() {
        this.session.close();
        this.driver.close();
    }

    @Override
    public void addSystem(String name, AbstractMap.SimpleEntry<String, String>[] nodeProperties) {
        this.session.run(createNodeQuery("SYSTEM", name, nodeProperties));
    }

    @Override
    public void addEntity(String name, AbstractMap.SimpleEntry<String, String>[] nodeProperties) {
        this.session.run(createNodeQuery("ENTITY", name, nodeProperties));
    }

    private String createNodeQuery(String nodeLabel, String name, AbstractMap.SimpleEntry<String, String>[] nodeProperties) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE (n:").append(nodeLabel);

        query.append("{ name:'").append(name).append("' }");

        for (AbstractMap.SimpleEntry<String, String> simpleEntry :
                nodeProperties) {
            query.append(simpleEntry.getKey()).append(": '").append(simpleEntry.getValue()).append("', ");
        }
        query.append(")");
        return query.toString();
    }

    @Override
    public void createLinkSystemToEntity(String systemName, String entityName) {
        this.session.run(this.createLinkQuery(systemName, "SYSTEM", entityName, "ENTITY", "CONTAINS"));
    }

    private String createLinkQuery(String sourceName, String sourceTag, String targetName, String targetTag, String relationName) {

        StringBuilder query = new StringBuilder();

        query.append("MATCH (u:").append(sourceTag).append(" {name:'").append(sourceName).append("'}), (r:")
                .append(targetTag).append(" {name:'").append(targetName).append("'})")
                .append("\n")
                .append("CREATE UNIQUE (u)-[:").append(relationName).append("]->(r)");

        return query.toString();
    }
}
