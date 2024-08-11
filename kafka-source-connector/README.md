# CDC For Postgres

```mermaid
    flowchart LR
        
    테이블생성["`테이블 생성`"]
    함수적용["`함수 생성`"]
    트리거적용["`사용 할 테이블,함수 정의 트리거 생성`"]
    
    
    테이블생성 --> 함수적용
    함수적용 --> 트리거적용
    
```

> 테이블 생성 
> -
> >- `테이블명` : orientation_students
> >- `PRIMARY KEY` : user_name 
> ```sql
> CREATE TABLE orientation_students
>  (
>   ssn        VARCHAR(11)  NOT NULL,
>   email      VARCHAR(255) NOT NULL,
>   user_name  VARCHAR(255) NOT NULL PRIMARY KEY ,
>   full_name  VARCHAR(255) NOT NULL,
>   address    VARCHAR(255) NOT NULL,
>   state      VARCHAR(255) NOT NULL,
>   major      VARCHAR(255) NOT NULL,
>   overnight  CHARACTER DEFAULT 'N',
>   preferences      VARCHAR(1000),
>   ts         TIMESTAMP DEFAULT NOW() NOT NULL
>  );
> 
> ```

> 함수 생성
> - 
> >- `Function` : update_ts_column_on_update()
> >  - Using `Procedure`
> ```sql
> CREATE OR REPLACE FUNCTION update_ts_column_on_update()
> RETURNS TRIGGER AS $$
> BEGIN
> NEW.ts = now();
> 
> RETURN NEW;
> 
> END;
> 
> $$ language 'plpgsql';
>```
 

> 트리거 생성
> -
> > - `테이블명` :  orientation_students
> > - `Trigger` : update_orientation_students_timestamp
> >  - `BEFORE` `UPDATE` `ON` 
> > - `Procedure` : update_ts_column_on_update()
> >  - `FOR` `EACH` `ROW` `EXECUTE`
> ```sql
> CREATE TRIGGER update_orientation_students_timestamp BEFORE UPDATE
> ON orientation_students FOR EACH ROW EXECUTE PROCEDURE
> update_ts_column_on_update();
> 
>```















<details><summary> 기본 리드미 텍스트</summary>
# kafka-source-connector

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kafka-source-connector-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Messaging - Kafka Connector ([guide](https://quarkus.io/guides/kafka-getting-started)): Connect to Kafka with Reactive Messaging

## Provided Code

### Messaging codestart

Use Quarkus Messaging

[Related Apache Kafka guide section...](https://quarkus.io/guides/kafka-reactive-getting-started)

</details>