# kata-bank-account-management

## Description
Kata bank account is an API that allows users to manage all transactions of their bank account

The principle transactions are : (withdrawl, deposit, operations history).

We developed all the transactions of the kata with the TDD methodology.

## What do you need
- Java 23

## Launch tests
```bash
$ mvn clean test
```

## Run spring boot application
```bash
$ mvn spring-boot:run

```

## REST API
```bash
POST  http://localhost:8080/account/139P560/save-operation

```

```bash
POST  http://localhost:8080/account/139P560/save-operation

```

```bash
GET  http://localhost:8080/account/139P560/operations
```
