./mvnw clean package -DskipTests
cp target/content-calendar-0.0.1-SNAPSHOT.jar ./docker
cd docker
docker compose up -d --build app