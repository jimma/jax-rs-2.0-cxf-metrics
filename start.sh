mvn package
cd docker
docker-compose up&
sleep 5
curl 'http://admin:admin@localhost:3000/api/datasources' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary '{"name":"prometheus","type":"prometheus","url":"http://prometheus:9090","access":"proxy","isDefault":true}'
curl 'http://admin:admin@localhost:3000/api/dashboards/db' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary @cxf-dashboard.json
cd ../test
mvn clean install
java -jar target/benchmarks.jar


# Service: http://localhost:19090/services
# Metrics: http://localhost:19090/metrics
# Prometheus: http://localhost:9090/graph
# Grafana: http://localhost:3000
# Jaeger: http://localhost:16686
