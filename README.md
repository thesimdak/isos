# isos
Backend for ISOS

## How to start locally
- run "docker-compose up -d" in local-setup folder
- start backend with argument "-Dspring.profiles.active=local"
- database has to be created using phpmyadmin
- checkout frontend and start with "npm run start" -> see the frontend project for more info

## How to setup real deployment
- build application and rename file to app.jar
- build frontend application with prod profile
- copy docker-compose.yaml in the root to the remote server
- copy backend and frontend app into the configured folders
- run docker-compose up -d