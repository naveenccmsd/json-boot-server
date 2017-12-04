# Mixing RESTEasy Spring Boot Starter with Gcloud

This RESTEasy Spring Boot starter integrated from Paypal Resteasy started and Gcloud examples.

## Features
* Enables RESTEasy for Spring Boot applications
* Supports JAX-RS providers, resources and sub-resources as Spring beans
* Supports RESTEasy Asynchronous Job Service
* Supports Gcloud Deploy

## Quick start

1. Log in using gcloud SDK (`gcloud auth login` in command line)

1. Set your current project using `gcloud config set project PROJECT_ID`

1. Compile using Maven: `mvn install -DskipTests` in command line from your base project directory

1. Run `mvn spring-boot:run`

1. Visit `http://localhost:8080`

1. Deploy to Gcloud `mvn appengine:deploy`

1. Visit `http://YOUR_PROJECT.appspot.com`.

## Links ##
 - [Paypal Rest easy](https://github.com/paypal/resteasy-spring-boot)
 - [Gcloud quickstart](https://cloud.google.com/appengine/docs/standard/java/quickstart-java8)
 - [Gcloud starte](https://github.com/rbarbioni/gcloud-spring-boot-starter)

