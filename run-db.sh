#!/bin/bash
set -euo pipefail
docker volume create omniguardian-db-data
docker run -d \
    --name omniguardian-db \
    -p 5432:5432 \
    -v omniguardian-db-data:/var/lib/postgresql/data \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    postgres:15.2