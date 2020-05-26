docker stop testDB
docker rm testDB

docker pull postgres
docker run --name testDB -e POSTGRES_DB=train -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5433:5432 -d postgres

sleep 3


mvn flyway:clean flyway:migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://localhost:5433/train -Ddatabase.user=admin -Ddatabase.password=password


mvn clean compile package -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://localhost:5433/train -Ddatabase.user=admin -Ddatabase.password=password


mvn flyway:clean flyway:migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://localhost:5433/train -Ddatabase.user=admin -Ddatabase.password=password

cd container
cp ../target/kanban-1.0-SNAPSHOT.war ROOT.war


docker build -t api:0.1 .

