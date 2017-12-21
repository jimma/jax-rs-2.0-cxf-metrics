Files in this folder are transformed from the docker-compose file in docker folder by [kompose](https://github.com/kubernetes/kompose).
Except for jaeger-all-in-one-template.yml (downloaded from jaeger), Dockerfile, and prometheus.yml.


To set up in Kubernetes, using commands below:

		mvn package
		kubectl create -f cxf-all-in-one.yaml
		kubectl create -f jaeger-all-in-one-template.yml
		kubectl create -f prometheus-deployment.yaml
		kubectl create -f grafana-all-in-one.yaml
		curl 'http://admin:admin@localhost:3000/api/datasources' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary '{"name":"prometheus","type":"prometheus","url":"http://prometheus:9090","access":"proxy","isDefault":true}'
		curl 'http://admin:admin@localhost:3000/api/dashboards/db' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary @cxf-dashboard.json
		cd ../test
		mvn clean install
		java -jar target/benchmarks.jar

