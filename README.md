# TODO 

> [!note]
> 
> - 쿼커스 스레드온메세지
>> https://quarkus.io/guides/messaging-virtual-threads
>
> - 스프링+posrgres Noti
> >https://github.com/eugenp/tutorials/tree/master/messaging-modules/postgres-notify

# Postgres CDC

## Logical Decording

> ### 1. Insert Config parameter
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
