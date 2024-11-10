# Blog Application - Spring Boot with MongoDB

This is a simple **Blog Application** built with **Spring Boot** that supports **CRUD operations**. It uses **MongoDB** as the database to store blog posts. Users can create, read, update, and delete blog posts.

## Features

- Create a new blog post
- View all blog posts
- Update existing blog posts
- Delete blog posts
- Simple REST API for managing blog posts

## Prerequisites

Before you begin, ensure you have the following installed on your local machine:

- Java 17 or higher
- Maven
- [MongoDB](https://www.mongodb.com/try/download/community) running locally or use a cloud service like MongoDB Atlas

## Clone the Repository

To clone this project, run the following command:

```bash
git clone https://github.com/your-username/your-repo.git
```

## Setup

#### 1. Configure the application.properties File

Clone the repository and navigate to the src/main/resources directory. If you don't already have an application.properties file, create one by copying the template:

```bash
cp src/main/resources/application-template.properties src/main/resources/application.properties
```

Update the application.properties file with your MongoDB configuration.

#### 2. Run the application

By running through IntellJ IDEA or VSCODE's gui or using CLI

By CLI

#### 1. Install Dependencies

```bash
mvn install
```

#### 2. Run the application

```bash
mvn spring-boot:run
```
