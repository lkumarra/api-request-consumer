# API Request Consumer

This project is a Java-based system designed to consume messages from a Kafka topic, process those messages as HTTP requests, and validate the responses. The system includes various components such as request models, response validators, and property readers to streamline the process.

## Features

- **Kafka Integration**: Consumes messages from Kafka using a configurable consumer.
- **HTTP Request Processing**: Processes incoming Kafka messages into HTTP requests.
- **Response Validation**: Validates the responses against expected results.
- **Configuration Management**: Manages configurations via properties files.

## Project Structure

```plaintext
api-request-consumer-master/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/api/request/consumer/
│   │   │       ├── App.java
│   │   │       ├── helpers/
│   │   │       │   └── PropertyReaderHelper.java
│   │   │       ├── models/
│   │   │       │   └── ExpectedResponseWrapper.java
│   │   │       ├── requestConsumer/
│   │   │       │   └── RequestConsumer.java
│   │   │       ├── requestModal/
│   │   │       │   └── APIRequest.java
│   │   │       ├── requestProcessor/
│   │   │       │   └── RequestProcessor.java
│   │   │       ├── responseModal/
│   │   │       │   └── ResponseModel.java
│   │   │       ├── responseValidor/
│   │   │       │   ├── IResponseValidator.java
│   │   │       │   ├── ResponseValidator.java
│   │   │       │   └── ResponseValidatorProcessor.java
│   │   │       └── responseWrapper/
│   │   │           └── ResponseWrapper.java
│   │   └── resources/
│   │       ├── kafka-consumer.properties
│   │       └── logback.xml
│   └── test/
│       └── java/
│           └── org/api/request/consumer/
│               └── AppTest.java
└── README.md
```

## Modules

### 1. `App.java`
The entry point of the application. It initializes and runs the application.

### 2. `PropertyReaderHelper.java`
Helper class for reading properties from the `kafka-consumer.properties` file. It manages configurations like Kafka consumer settings.

### 3. `ExpectedResponseWrapper.java`
Model class that wraps the expected response details, which are used to validate the actual response.

### 4. `RequestConsumer.java`
Class responsible for consuming messages from the Kafka topic. It listens for incoming messages and processes them.

### 5. `APIRequest.java`
Model class representing the structure of the API request to be made based on the consumed message.

### 6. `RequestProcessor.java`
Class that processes the `APIRequest` object. It handles the logic of making the HTTP request.

### 7. `ResponseModel.java`
Model class representing the structure of the HTTP response received after the request is made.

### 8. `IResponseValidator.java`
An interface defining the contract for response validation. All response validators should implement this interface.

### 9. `ResponseValidator.java`
Concrete implementation of the `IResponseValidator` interface. It contains the logic for validating the HTTP response against the expected response.

### 10. `ResponseValidatorProcessor.java`
Class that processes the validation of the response using the implemented `ResponseValidator`.

### 11. `ResponseWrapper.java`
Class that wraps the response details, making it easier to handle and validate responses.

### 12. `kafka-consumer.properties`
Properties file containing the Kafka consumer configurations like the broker list, group ID, and topic names.

### 13. `logback.xml`
Configuration file for logging using Logback. It defines the logging levels, format, and output destinations.

### 14. `AppTest.java`
Unit tests for the `App.java` class to ensure the application initializes and runs as expected.

## Getting Started

### Prerequisites

- Java 11 or higher
- Apache Kafka
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/lkumarra/api-request-consumer.git
    cd api-request-consumer-master
    ```

2. Install dependencies and build the project:
    ```bash
    mvn clean install
    ```

3. Update the `kafka-consumer.properties` file with your Kafka configurations.

### Running the Project

To start the API Request Consumer:

```bash
mvn exec:java -Dexec.mainClass="org.api.request.consumer.App"
```

### Running Tests

Run unit tests with the following command:

```bash
mvn test
```

## Configuration

All configurations can be set in the `kafka-consumer.properties` file:

- **Kafka Configuration**: Set the Kafka broker URL, topic name, and group ID.
- **Logback Configuration**: Adjust logging settings in the `logback.xml` file.

## Contributing

Contributions are welcome! Please fork this repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or issues, please contact [lkumarra](https://github.com/lkumarra).
