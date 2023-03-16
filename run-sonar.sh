#!/usr/bin/env bash
./gradlew sonarqube \
  -Dsonar.projectKey=SubOms2019 \
  -Dsonar.host.url=http://sonarqubedev.eastus.azurecontainer.io:9000 \
  -Dsonar.login=535ac8bc0a4cfef9dbd97d3896102f019f7fc207
