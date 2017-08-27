package com.ldz;

import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

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
    public void addSystem(String name) {
        this.session.run(createNodeQuery("SYSTEM", name));
    }

    @Override
    public void addEntity(String name) {
        this.session.run(createNodeQuery("ENTITY", name));
    }

    private String createNodeQuery(String nodeLabel, String name) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE (n:").append(nodeLabel);

        query.append("{ name:'").append(name).append("' }");

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

    @Override
    public void addParamterToNode(String nodeName, String nodeTag, AbstractMap.SimpleEntry<String, String> attribute) {

        StringBuilder query = new StringBuilder();
        query.append("MATCH (n:").append(nodeTag).append(" {name: '").append(nodeName).append("'})\n")
                .append("SET n.").append(attribute.getKey()).append(" = '").append(attribute.getValue()).append("'");
        this.session.run(query.toString());

    }

    @Override
    public void deleteNode(String nodeName) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (n { name: '").append(nodeName).append("' })").append("\n").append("DETACH DELETE n");
        this.session.run(query.toString());
    }
}
