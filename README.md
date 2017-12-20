Using Dropwizard Metrics with Apache CXF, Spring Boot, Prometheus, Grafana and Jaeger
==============

 - Start everything needed

		chmod a+x start.sh
		./start.sh

 - After start, endpoints available

		Service: http://localhost:19090/services
		Metrics: http://localhost:19090/metrics  Username/Password: guest / guest
		Prometheus: http://localhost:9090/graph
		Grafana: http://localhost:3000  Username/Password: admin / admin
		Jaeger: http://localhost:16686

 - If you want to see more data in the monitors, re-run the tests

		java -jar test/target/benchmarks.jar
