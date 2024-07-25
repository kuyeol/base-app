package org.acme.microprofile.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/html")
public class Resource {


@GET
@Produces(MediaType.TEXT_HTML)
  public String get() {
String description= """
    <H1>Hello World</H1>
    <button> <a href="/q/graphql-ui\s">button</a></button>
    \u000D
    """;
  return "<html>\n"+
      "<body>\n"+
        description.substring(0,5)+
      "<p>"+description+
      "</p>\n" +
      "</body>\n" +
      "</html>";




}

}
