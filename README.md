
## Introduction

The Safeway Teste Java Project is a Java-based application that facilitates deposit and withdrawal operations for clients and companies. It utilizes JPA for the database, Java, and Spring Boot for its functionality. 

## Features

- **Deposit and Withdrawal Operations**: Users can make deposits and withdrawals from their accounts.
- **JPA Database**: Utilizes JPA for efficient and reliable database operations.
- **Email Notifications**: Sends email notifications using the SendGrid API when deposit and withdrawal operations are made.
- **Webhook Integration**: Makes API calls to a specified webhook URL [webhook.site](https://webhook.site/#!/) whenever transactions occur.

## Getting Started

### Prerequisites

Before you begin, make sure you have the following software and tools installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)

### Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/leticia-marques/teste_java.git

2. Navigate to the project directory:
    ```bash
    cd teste_java
3. Build the project using Maven:
    ```bash
    mvn clean install

### Usage
For routes and body params go to http://localhost:8080/swagger-ui/index.html


### Configuration

Before running the project, configure the application properties by filling in the necessary variables in the applications.properties file:
<ul>
    <li>safeway.teste.notification.email: The email address used for sending</li> notifications.
    <li>safeway.teste.notification.email-to: The email address that will receive notifications.</li>
    <li>safeway.teste.notification.url: The URL provided by [webhook.site] for making API calls.</li>
    <li>safeway.teste.notification.api-key: The API key generated from [app.sendgrid.com] for email notifications.</li>
<ul>

