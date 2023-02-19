package com.distribuida.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DatabaseConfig {

    @Inject
    @ConfigProperty(name = "mongo.hostname", defaultValue = "localhost")
    String hostname;

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.dbname", defaultValue = "testdb")
    String dbName;

    @Inject
    @ConfigProperty(name = "mongo.user",defaultValue = "root")
    String user;

    @Inject
    @ConfigProperty(name = "mongo.password",defaultValue = "root")
    String password;

    @Produces
    public MongoClient mongoClient(){
        return MongoClients.create(String
                .format("mongodb://%s:%s@%s:%s/%s?authSource=admin",user,password,hostname,port,dbName)
                .toString());
    }
    @Produces
    public MongoDatabase getDatabase(MongoClient mongoClient){
        return mongoClient.getDatabase(dbName);

    }





}
