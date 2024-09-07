package org.acme.kafka.pgevent;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;


//@ApplicationScoped
public class PgNotificator
    //extends RouteBuilder
{
  //
  //public static final String MOCK_ENDPOINT_CLASSIC_CONF = "mock:classic";
  //
  //public static final String MOCK_ENDPOINT_AGROALDATASOURCE = "mock:datasource";
  //
  //@ConfigProperty(name = "database.host")
  //String host;
  //
  //@ConfigProperty(name = "database.port")
  //Integer port;
  //
  //@ConfigProperty(name = "database.name")
  //String databaseName;
  //
  //@ConfigProperty(name = "quarkus.datasource.pgDatasource.username")
  //String user;
  //
  //@ConfigProperty(name = "quarkus.datasource.pgDatasource.password")
  //String password;
  //
  //
  //@Override
  //public void configure() throws Exception
  //  {
  //    // producer for simple pub-sub
  //    from( "direct:pgevent-pub" ).to( String.format( "pgevent://%s:%s/%s/testchannel?user=%s&pass=%s", host, port, databaseName, user, password ) );
  //
  //    //consumer for simple pub-sub
  //    from( String.format( "pgevent://%s:%s/%s/testchannel?user=%s&pass=%s", host, port, databaseName, user, password ) ).to( MOCK_ENDPOINT_CLASSIC_CONF );
  //
  //    // producer with datasource
  //    from( "direct:pgevent-datasource" ).to( "pgevent:///postgres/testchannel?datasource=#pgDatasource" );
  //
  //    // consumer with datasource
  //    from( "pgevent:///postgres/testchannel?datasource=#pgDatasource" ).to( MOCK_ENDPOINT_AGROALDATASOURCE );
  //  }
  //
  //



}
