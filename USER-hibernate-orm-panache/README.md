




INSERT INTO t_artists (id, name, bio, created_date) VALUES
(1, 'Agustina Lakin', 'Est et numquam deleniti dolore placeat. Sit quo quis voluptas. Ducimus non perferendis nemo.', '2021-05-20T15:40:00.548331Z'),

INSERT INTO t_publishers (id, name, created_date) VALUES
(1, 'Leaf Books', '2021-05-20T15:40:00.592040Z'),

INSERT INTO t_items (dtype, id, artist_fk, publisher_fk, title, isbn, price, language, nb_of_pages, publication_date, description, created_date) VALUES
('Book', 101, 77, 67, 'Butter In a Lordly Dish', '9781877664281', 73, 'ENGLISH', 27, '2018-06-21', 'Sit perspiciatis ipsum ut. Et quas est consectetur dolorem at. Voluptatum est voluptas voluptas repellendus. Enim quia ipsam sit et voluptates. Nulla corrupti temporibus aut autem itaque.', '2021-05-20T15:40:00.702118Z'),

INSERT INTO t_items (dtype, id, artist_fk, title, price, genre, music_company, description, created_date) VALUES
('CD', 1, 36, 'The OJays', 83, 'Pop', 'Carroll-Kuhn', 'Sunt repellendus ratione esse quos ea. Quibusdam quisquam id est harum dolores commodi. Similique qui consequatur rerum voluptatum ad reiciendis eos. Dolor et vel odio ea sed quam. Dolor quos veniam et voluptas maiores aut consequatur.', '2021-05-20T15:40:00.651551Z'),















# Quarkus demo: Hibernate ORM with Panache and RESTEasy

This is a minimal CRUD service exposing a couple of endpoints over REST,
with a front-end based on Angular so you can play with it from your browser.

While the code is surprisingly simple, under the hood this is using:
 - RESTEasy to expose the REST endpoints
 - Hibernate ORM with Panache to perform the CRUD operations on the database
 - A PostgreSQL database; see below to run one via Docker
 - ArC, the CDI inspired dependency injection tool with zero overhead
 - The high performance Agroal connection pool
 - Infinispan based caching
 - All safely coordinated by the Narayana Transaction Manager

## Requirements

To compile and run this demo you will need:

- JDK 17+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.

### Configuring GraalVM and JDK 17+

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 17+ `java` command is on the path.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image)
for help setting up your environment.

## Building the demo

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

## Running the demo

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

In this mode you can make changes to the code and have the changes immediately applied, by just refreshing your browser.

    Hot reload works even when modifying your JPA entities.
    Try it! Even the database schema will be updated on the fly.

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

> ./mvnw package

Next we need to make sure you have a PostgreSQL instance running (Quarkus automatically starts one for dev and test mode). To set up a PostgreSQL database with Docker:

> docker run -it --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3

Connection properties for the Agroal datasource are defined in the standard Quarkus configuration file,
`src/main/resources/application.properties`.

Then run it:

> java -jar ./target/quarkus-app/quarkus-run.jar

    Have a look at how fast it boots.
    Or measure total native memory consumption...

### Run Quarkus as a native application

You can also create a native executable from this application without making any
source code changes. A native executable removes the dependency on the JVM:
everything needed to run the application on the target platform is included in
the executable, allowing the application to run with minimal resource overhead.

Compiling a native executable takes a bit longer, as GraalVM performs additional
steps to remove unnecessary codepaths. Use the  `native` profile to compile a
native executable:

> ./mvnw package -Dnative

After getting a cup of coffee, you'll be able to run this binary directly:

> ./target/hibernate-orm-panache-quickstart-1.0.0-SNAPSHOT-runner

    Please brace yourself: don't choke on that fresh cup of coffee you just got.
    
    Now observe the time it took to boot, and remember: that time was mostly spent to generate the tables in your database and import the initial data.
    
    Next, maybe you're ready to measure how much memory this service is consuming.

N.B. This implies all dependencies have been compiled to native;
that's a whole lot of stuff: from the bytecode enhancements that Panache
applies to your entities, to the lower level essential components such as the PostgreSQL JDBC driver, the Undertow webserver.

## See the demo in your browser

Navigate to:

<http://localhost:8080/index.html>

Have fun, and join the team of contributors!

## Running the demo in Kubernetes

This section provides extra information for running both the database and the demo on Kubernetes.
As well as running the DB on Kubernetes, a service needs to be exposed for the demo to connect to the DB.

Then, rebuild demo docker image with a system property that points to the DB. 

```bash
-Dquarkus.datasource.jdbc.url=jdbc:postgresql://<DB_SERVICE_NAME>/quarkus_test
```
