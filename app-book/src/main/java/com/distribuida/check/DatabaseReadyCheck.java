package com.distribuida.check;

import com.mongodb.client.MongoClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import java.util.Map;


@Readiness
@ApplicationScoped
public class DatabaseReadyCheck implements HealthCheck {

    @Inject
    private MongoClient mongoClient;
    @Inject
    @ConfigProperty(name = "mongo.dbname", defaultValue = "testdb")
    String dbName;

    @Override
    public HealthCheckResponse call() {
        try {
            mongoClient.getDatabase(dbName)
                    .runCommand(new Document(Map.of("ping",1)));
            return HealthCheckResponse.named("database ready")
                    .up()
                    .build();
        }catch (Exception e){
            return HealthCheckResponse.named("database not ready")
                    .down()
                    .build();

        }
    }
}
