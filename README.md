# TODO 
---
## 24_8_29
## kafka Example link
article https://www.infoq.com/profile/Alex-Soto/

- [ ] https://www.infoq.com/articles/quarkus-with-kafka-streams/
- [ ] https://www.infoq.com/articles/change-data-capture-debezium/
- [ ] https://www.infoq.com/articles/data-with-quarkus-kafka/
- [ ] https://www.infoq.com/articles/testing-quarkus-reactive/
- [ ] https://www.infoq.com/articles/microservicilities-tekton/




Rest App
> Produce/@channel Emitter send message : 이벤트 생성 -> 카프카 전달

Query App
> @Consumer/ 토픽 리슨 /토픽에서 데이터 파싱/ 로직 실행 : 이벤트 리스너 
---

> [!note]
> 
> - 쿼커스 스레드온메세지
>> https://quarkus.io/guides/messaging-virtual-threads
>
> - 스프링+posrgres Noti
> >https://github.com/eugenp/tutorials/tree/master/messaging-modules/postgres-notify



# 클래스 작성 가이드



| First Header  | Content Cell  |
| Content Cell  | Content Cell  |

>### Class Name : 
> > - 클래스의 역할
>  
>### Responsibility : Behavior
> > - 클래스의 작업 결과
> 
> ### Operations : Methods

> > - 클래스 작업 목록
> > - e.g) 작업1, 작업2 ,작업3 ....
> 
>### Attribute : Status
> > - 클래스가 가지는 값, 상태, 속성
> > - e.g ) color, speed , price , count ....



****


> [!CAUTION]
> - MAVEN DEPENENCE ERROR
> - 아래 스크립트 실행하여 버전 수정
>> ```shell
>> mvn io.thorntail:thorntail-maven-plugin:2.3.0.Final:migrate-from-wildfly-swarm
>> ```

# Postgres CDC

## Logical Decording

> [!Note]
>  - 슬롯 생성
> ```sql
> SELECT * FROM pg_create_logical_replication_slot('demo_slot', 'test_decoding');
> ```
>  -  메세지 생성
> ```sql
>SELECT * FROM pg_logical_emit_message(true, 'context1', 'Hello11 11World!');
> ```
> 
> - 메세지 확인
> ```sql
>SELECT * FROM pg_logical_slot_get_changes('demo_slot', NULL, NULL);
> ```

- 메세지 생성 예제
```sql
SELECT * FROM pg_logical_slot_get_changes('demo_slot', NULL, NULL);

--SELECT * FROM pg_logical_slot_peek_changes();


SELECT * FROM pg_logical_emit_message(
        true,
        'outbox',
        '{
          "id" : "298c2cc3-71bb-4d2b-b5b4-1b14006d56e6",
          "aggregate_type" : "shipment",
          "aggregate_id" : 42,
          "payload" : {
            "customer_id" : 7398,
            "item_id" : 8123,
            "status" : "SHIPPED",
            "numberOfPackages" : 3,
            "address" : "Bob Summers, 12 Main St., 90210, Los Angeles/CA, US"
          }
        }'
              );
```



> ### 1. Insert Config parameter
> > `postgresql.conf` FIND A FILE LOCATION SCRIPT  
>```sql
>SHOW config_file;
> --- /var/lib/postgresql/data/postgresql.conf
>```
> ```
> wal_level = logical           
> max_replication_slots = 10 
> ```

>### 2. Execute Sql Script
>```sql
>SELECT * FROM pg_create_logical_replication_slot('slot_repl', 'decode_test');
>```

>### 3. Monitor & Representation
> ---
>> - 결과 제공과 결과 유지
>```sql
>SELECT * FROM pg_logical_slot_peek_changes('slot_repl', NULL, NULL);
>```
> ---
>> - 결과 제공과 확인 시 결과 제거
>```sql
>>SELECT * FROM pg_logical_slot_get_changes('slot_repl', NULL, NULL);
>```
> ---



# Postgres 알림/리스너

> [!note]
> POSTGRESQL 데이터 변경 사항 알림 받아 레디스에 복제 하기
>  - [Postgres 레퍼런스 링크](https://jdbc.postgresql.org/documentation/server-prepare/)


진행중인 서비스 개발
- 아웃박스 패턴
- 메세지 이벤트 
- 데이터 변경 캡쳐

> Repo Link
> ---
> [debezium-repo](vertx-quickstart)
