# RESTEasy Spring Boot Starter with Gcloud

There was no RESTEasy Spring Boot starter out there, so PayPal team decided to create one and share it with the community.<br>

This Spring Boot starter is fully functional, has ZERO PayPal specific code on it, and can be used normally by any regular Spring Boot application that wants to have REST endpoints and prefers RESTEasy as the JAX-RS implementation.

Also, this RESTEasy Spring Boot starter integrated from Paypal Resteasy started and Gcloud examples.

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
