cd docker
docker-compose up&
sleep 3
curl 'http://admin:admin@localhost:3000/api/datasources' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary '{"name":"prometheus","type":"prometheus","url":"http://prometheus:9090","access":"proxy","isDefault":true}'
curl 'http://admin:admin@localhost:3000/api/dashboards/db' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary @cxf-dashboard.json
docker run -d -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 -p5775:5775/udp -p6831:6831/udp -p6832:6832/udp \
  -p5778:5778 -p16686:16686 -p14268:14268 -p9411:9411 jaegertracing/all-in-one:latest
cd ../test
mvn clean install
java -jar target/benchmarks.jar


# Service: http://localhost:19090/services
# Metrics: http://localhost:19090/metrics
# Prometheus: http://localhost:9090/graph
# Grafana: http://localhost:3000
# Jaeger: http://localhost:16686
