Using Dropwizard Metrics with Apache CXF, Spring Boot, Prometheus and Grafana
==============

- Build

		mvn package
         
- Run using Spring Boot Maven plugin

		mvn spring-boot:run
         
- Run using standalone JAR

		java -jar target/jax-rs-2.0-cxf-metrics-0.0.1-SNAPSHOT.jar         

- Invoke People REST service  

		curl http://localhost:19090/services/people
		
- Invoke /metrics endpoint
		
		curl -u guest:guest http://localhost:19090/metrics
        
- Run Docker containers
        
		cd docker
		docker-compose up
        
- Configure Grafana with Prometheus datasource and sample dashboard
    
		curl 'http://admin:admin@localhost:3000/api/datasources' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary '{"name":"prometheus","type":"prometheus","url":"http://prometheus:9090","access":"proxy","isDefault":true}'
	
		curl 'http://admin:admin@localhost:3000/api/dashboards/db' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary @cxf-dashboard.json

- Prometheus web UI 
 
		http://localhost:9090/graph
	 	
- Grafana web UI 
		
		http://localhost:3000
		Username/Password: admin / admin
