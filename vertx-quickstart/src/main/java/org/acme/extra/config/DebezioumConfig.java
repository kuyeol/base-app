package org.acme.extra.config;

import io.debezium.config.Configuration;
import jakarta.ws.rs.Produces;
import java.io.File;
import java.io.IOException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class DebezioumConfig {

  @ConfigProperty(name = "quarkus.datasource.jdbc.url")
  String url;
  @ConfigProperty(name = "quarkus.datasource.username")
  String USERNAME;
  @ConfigProperty(name = "quarkus.datasource.password")
  String PASSWORD;

  @Produces
  public Configuration configurationProduce() throws
      IOException {



    SqlParser jdbcParser = SqlParser.parse(url);

    File fileOffset = File.createTempFile("offset", ".dat");
    File fileDbHistory = File.createTempFile("dbhistory", ".dat");

    return io.debezium.config.Configuration.create()
                                           .with("name", "debezium-connector")
                                           // configures MySQL connector
                                           .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                                           .with("offset.storage",
                                               "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                                           .with("offset.storage.file.filename", fileOffset.getAbsolutePath())
                                           .with("offset.flush.interval.ms", "60000")
                                           // Configures database location
                                           .with("database.hostname", jdbcParser.getHost())
                                           .with("database.port", jdbcParser.getPort())
                                           .with("database.user", USERNAME)
                                           .with("database.allowPublicKeyRetrieval", "true")
                                           .with("database.password", PASSWORD)
                                           .with("database.dbname", jdbcParser.getDatabase())
                                           .with("database.include.list", jdbcParser.getDatabase())
                                           // Debezium only sends events for the modifications of OutboxEvent table and not all tables
                                           .with("table.include.list", jdbcParser.getDatabase() + ".OutboxEvent")
                                           .with("include.schema.changes", "false")
                                           .with("database.server.id", "10181")
                                           .with("database.server.name", "movies-mysql-db-server")
                                           .with("database.history",
                                               "io.debezium.relational.history.FileDatabaseHistory")
                                           .with("database.history.file.filename", fileDbHistory.getAbsolutePath())
                                           .build();
  }



  /* // TODO: DATACONNECT CODE WRITE
  = $(hostname -I | cut -d ' ' -f 1)
curl --location 'http://localhost:8083/connectors' \
   --header 'Accept: application/json' \
   --header 'Content-Type: application/json' \
   --data '{
   "name": "cdc-using-debezium-connector",
   "config": {
       "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
       "database.hostname": "192.168.1.110",
       "database.port": "5443",
       "database.user": "postgres",
       "database.password": "123",
       "database.dbname": "cdc-using-debezium",
       "database.server.id": "184054",
       "table.include.list": "public.User",
       "topic.prefix": "cdc-using-debezium-topic"
   }
   */
}













