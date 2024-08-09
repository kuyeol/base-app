version: '3.7'


networks:
   cdc-using-debezium-network:
       name: cdc-using-debezium-network
       driver: bridge
       external: false


services:
   cdc-using-debezium-postgres:
       image: debezium/postgres:11
       container_name: cdc-using-debezium-postgres
       hostname: cdc-using-debezium-postgres
       restart: always
       ports:
           - '5443:5432'
       environment:
           POSTGRES_PASSWORD: quarkus
           POSTGRES_USER: quarkus
           POSTGRES_DB: cdc-using-debezium
       volumes:
           - 'cdc-using-debezium-postgres-data:/var/lib/postgresql/data'
       networks:
           - cdc-using-debezium-network


   cdc-using-debezium-kafka:
       image: bitnami/kafka:3.4
       container_name: cdc-using-debezium-kafka
       hostname: cdc-using-debezium-kafka
       restart: always
       ports:
           - '9090:9092'
       environment:
           KAFKA_CFG_NODE_ID: 1
           KAFKA_KRAFT_CLUSTER_ID: q0k00yjQRaqWmAAAZv955w # base64 UUID
           KAFKA_CFG_PROCESS_ROLES: controller,broker
           KAFKA_CFG_LISTENERS: INTERNAL://cdc-using-debezium-kafka:29092,
           CONTROLLER: //cdc-using-debezium-kafka:29093,EXTERNAL://0.0.0.0:9090
           KAFKA_CFG_ADVERTISED_LISTENERS: INTERNAL://cdc-using-debezium-kafka:29092,EXTERNAL://182.218.135.229:9090
           KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
           KAFKA_CFG_CONTROLLER_QUORUM_VOTERS: 1@cdc-using-debezium-kafka:29093
           KAFKA_CFG_INTER_BROKER_LISTENER_NAME: INTERNAL
           KAFKA_CFG_CONTROLLER_LISTENER_NAMES: CONTROLLER
       networks:
           - cdc-using-debezium-network


   cdc-using-debezium-connect:
       image: debezium/connect:2.3
       container_name: cdc-using-debezium-connect
       hostname: cdc-using-debezium-connect
       restart: always
       ports:
           - '8083:8083'
       environment:
           BOOTSTRAP_SERVERS: cdc-using-debezium-kafka:29092
           GROUP_ID: 1
           CONFIG_STORAGE_TOPIC: my_connect_configs
           OFFSET_STORAGE_TOPIC: my_connect_offsets
           STATUS_STORAGE_TOPIC: my_connect_statuses
           ENABLE_DEBEZIUM_SCRIPTING: 'true'
       links:
           - cdc-using-debezium-kafka
           - cdc-using-debezium-postgres
       networks:
           - cdc-using-debezium-network


volumes:
   cdc-using-debezium-postgres-data:
       name: cdc-using-debezium-postgres-data
       driver: local