#!/usr/bin/env bash
mvn clean package docker:build -DskipTests
docker images --all
docker run payment-docker-image
docker ps
docker exec -it 097097598fff curl http://localhost:8080/application/health