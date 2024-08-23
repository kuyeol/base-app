package org.acme;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/vertx")                        // <1>
public class VertxResource {

  private final Vertx vertx;
  private final WebClient client;

  VertxResource(Vertx vertx) { // <2>
    this.vertx = vertx;
    this.client = WebClient.create(vertx);
  }

  @GET
  @Path("/lorem/{index}")
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.TEXT_PLAIN)
  public Uni<String> readShortFile(int index) {

    File dir = new File("D:\\Ung-ProJect\\base-app\\vertx-quickstart\\src\\main\\resources\\doc");
    File files[] = dir.listFiles();

    //for (int i = 0; i < files.length; i++) {
    //  System.out.println("file: " + files[i]);
    //  vertx.fileSystem()
    //       .readFile(String.valueOf(files[i]))
    //       .onItem()
    //       .transform(content -> content.toString(StandardCharsets.UTF_8));
    //}
    //



    String f = String.valueOf(files[index]);




    return vertx.fileSystem().readFile(f)
                .onItem().transform(content -> content.toString(StandardCharsets.UTF_8));
  }

  @GET
  @Path("/book")
  public Multi<String> readLargeFile() {



    return vertx.fileSystem().open("book.txt", new OpenOptions().setRead(true))
                .onItem().transformToMulti(file -> file.toMulti())
                .onItem().transform(content -> content.toString(StandardCharsets.UTF_8)
            + "\n------------\n");
  }

  @Inject
  EventBus bus;

  @GET
  @Path("/hello")
  public Uni<String> hello(
      @RestQuery
      String name) {
    return bus.<String>request("greetings", name)
              .onItem().transform(response -> response.body());
  }

  @GET
  @Path("/web/{str}")

  public Uni<JsonArray> retrieveDataFromWikipedia(String str) {
    String parse = "parse";
    String prop = "langlinks";
    //String search=str;
    //search="quarkus";
    String url =
        "https://en.wikipedia.org/w/api.php?action=" + parse + "&page=" + str + "&format=json&" + prop + "=langlinks";
    return client.getAbs(url).send()
                 .onItem().transform(HttpResponse::bodyAsJsonObject)
                 .onItem().transform(json -> json.getJsonObject(parse).getJsonArray(prop));
  }
}
