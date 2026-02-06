💳 POS Transaction API

REST API to simulate credit and debit card transactions, inspired by the core behavior of a POS (Point of Sale) payment terminal.

This project focuses on clean architecture, idempotency, business validations, and clear domain modeling, aiming to demonstrate backend fundamentals using Spring Boot.

🚀 Technologies

Java 21

Spring Boot 

Spring Web (REST)

Spring Data JPA

Hibernate

Flyway

MySQL

Bean Validation

🧠 Concepts Applied

RESTful API design

Idempotency using request headers

Domain-driven validations

Entity relationships

DTOs for input and output

Database migrations with Flyway

Layered architecture (Controller / Service / Domain)

🧾 Domain Overview
🔹 Device (POS Terminal)

Represents a physical card machine registered in the system.

Can be active or inactive

Only active devices are allowed to process transactions

🔹 Transaction

Represents a credit or debit card transaction.

Amount

Transaction type (CREDIT / DEBIT)

Installments (credit only)

Status (AUTHORIZED / DENIED)

Authorization code (when approved)

Idempotency key

Linked to a Device

🔁 Transaction Endpoint
Create Transaction
POST /transactions/{deviceId}

Headers
Content-Type: application/json
Idempotency-Key: example-123

Request Body (Credit example)
{
  "amount": 150.00,
  "transactionType": "CREDIT",
  "installments": 3
}

Response
{
  "transactionId": 1,
  "status": "AUTHORIZED",
  "amount": 150.00,
  "transactionType": "CREDIT",
  "authorizationCode": "AUTH-A1B2C3D4"
}

🔐 Idempotency

The API supports idempotent requests through the Idempotency-Key header.

If the same key is sent again:

The transaction is not duplicated

The original transaction response is returned

This behavior simulates real-world payment gateways.

✅ Business Rules Implemented

Device must be active

Debit transactions cannot be split into installments

Credit transactions allow installments (up to a defined limit)

Transaction value validation

Business rules are decoupled and easy to extend

🎯 Project Purpose

Simulate a real POS transaction flow

Practice REST API development

Apply idempotency concepts

Build a clean and understandable backend project

Serve as a portfolio project for junior backend positions

👨‍💻 Author

Project developed for study and portfolio purposes.
