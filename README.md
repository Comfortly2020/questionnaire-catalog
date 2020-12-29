# Comfortly: Questionnaire data microservice

## Prerequisites

```bash
docker run -d --name pg-questionnaire-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=questionnaire-data -p 5433:5432 postgres:13
docker run -d --name pg-answer-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=answer-data -p 5434:5432 postgres:13
```