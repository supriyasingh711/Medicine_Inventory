Medicine Inventory Management System
This project is an inventory management system for medicines, which includes real-time stock updates and alert notifications using Kafka. The system integrates Kafka for messaging and includes a REST API for managing medicines.

🚀 Quick Setup
1. Start Zookeeper Container
Start a Zookeeper container and expose port 2181:

docker run -p 2181:2181 zookeeper

2. Start Kafka Container
Start the Kafka container, expose port 9092, and set up environment variables for the Kafka setup:

docker run -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=<PRIVATE_IP>:2181 -e KAFKA_ADVERTISED_LISTENER=PLAINTEXT://<PRIVATE_IP>:9092 -e KAFKA_LISTENER_SECURITY_PROTOCOL=PLAINTEXT -e KAFKA_LISTENER_NAME=PLAINTEXT -e KAFKA_LISTENER_PORT=9092 -e KAFKA_LISTENER_HOSTNAME=<PRIVATE_IP> confluentinc/cp-kafka
Make sure to replace <PRIVATE_IP> with the actual private IP of your machine.

🛠️ Kafka Consumer and Producer
1. Kafka Consumer CLI
Run the following command to start the Kafka consumer and check messages from the restock-alerts topic:

docker exec -it goofy_archimedes kafka-console-consumer --bootstrap-server localhost:9092 --topic restock-alerts --from-beginning
2. Kafka Producer CLI
Run the following command to produce messages to the restock-alerts topic:

docker exec -it goofy_archimedes kafka-console-producer --broker-list localhost:9092 --topic restock-alerts
🧑‍💻 REST API Endpoints
1. Update Medicine Stock
To update the stock of a medicine, make a PUT request:
PUT /api/medicines/{id}
Request Body:
{
  "name": "Paracetamol",
  "quantity": 5
}
The request will update the medicine stock and trigger a Kafka alert if the stock is low.

2. Alert Endpoint (Fixing the GET Request Issue)
To test the alert API, make a GET request to the following endpoint:

GET http://localhost:8080/api/alert

Note: There was an issue with sending GET requests on this endpoint, but it has been fixed now.

📜 Notes
Kafka is used to send real-time restock alerts to notify the inventory system when a product is running low.

Zookeeper is used to manage Kafka cluster metadata.

🎯 Next Steps
Integrate WebSocket for real-time front-end notifications.

Improve Kafka consumer logic for better monitoring and alerting.

Add more validation and logging to the API endpoints.
