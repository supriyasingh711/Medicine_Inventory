Kafka configurations:
>Start Zookeeper Container and expose port 2181
>docker run -p 2181:2181 zookeeper
>Start Kafka Container, expose PORT 9092 and setup ENV vraiables
>docker run -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=<PRIVATE_IP>:2081 -e KAFKA_ADVANCED_LISTENERS=PLAINTEXT://<PRIVATE_IP>:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka
>The consumer & producer CLI version:
>docker exec -it goofy_archimedes kafka-console-consumer --bootstrap-server localhost:9092 --topic restock-alerts --from-beginning
>docker exec -it goofy_archimedes kafka-console-producer --broker-list localhost:9092 --topic restock-alerts
>Fixing the issue of sending get request on hitting the endpoint "http://localhost:8080/api/alert"
