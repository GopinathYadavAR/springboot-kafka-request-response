# Springboot-kafka-request-response
Sample Springboot application with Kafka request and response model

## Install apache kafka
 To download and install Kafka, please refer to the official guide [here](https://kafka.apache.org/quickstart).
## Create a topic
 Create topic for request and response
 1. bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-demo
 2. bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-demo-reply-topic

## Download the project 
 git clone https://github.com/GopinathYadavAR/springboot-kafka-request-response.git
 
## Run the project
    1. naviagate to project folder
    2. ./gradlew clean build
    3. gradle bootrun
    4. springboot-kafka-request-response project will run localhost:8080    

### Invoke sample REST Endpoint
    Once application started then invoke 
    POST call http://localhost:8080/kafka with below paylaod 
    {
    	"name":"Gopi",
    	"employeeId": "123"
    }
    then will response 
    {
        "name": "Gopi",
        "employeeId": "123",
        "status": "Completed"
    }
