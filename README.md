# review-application-microservices

Microservices are deployed in docker container.

1. Create DockerFile in each service which are required to deploy in container.
  
>Jar file 

FROM openjdk:8-jdk \
ADD target/Review-Service-0.0.1-SNAPSHOT.jar Review-Service-0.0.1-SNAPSHOT.jar \
EXPOSE 8087 \
ENTRYPOINT ["java","-jar","/Review-Service-0.0.1-SNAPSHOT.jar"]
		
>War file

FROM tomcat:9.0-alpine \
COPY target/user.war /usr/local/tomcat/webapps/ \
EXPOSE 8080 \
CMD ["catalina.sh", "run"]
		
2. To build the docker image
	$Docker built -t <image_name> <destination_address> \
	$Docker built -t kafka-service . 

	To add version of image 
	
	$Docker built -t <image_name>:<version> <destination_address> \
	$Docker built -t kafka-service:v2 .

	To delete image
	
	$docker image rm review_service


	To list of docker images
	
	$Docker image ls

	2.1 In our scenario builtin images are redis, bitnami/kafka, mysql from dockerhub.

	docker built -t product_service . \
	docker built -t review_service . \
	docker built -t user_service . \
	docker built -t consumer_service .
	

	
![ScreenShot](https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Rancher_desktop_images_list_ui.png)

3. Create docker networks
	
	docker create network mysql_net \
	docker create network redis_net \
	docker create network kafka_net \
	docker create network eureka_net
	
<img width="964" alt="docker network list" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/network_list.png">

4. Create/Run the container
	
	Docker run -p 3306:3306 --name --net mysql_net mysql_container -e MYSQL_ROOT_PASSWORD=mypass -e MYSQL_DATABASE=revapp_micro -d mysql

	Docker run -d --name kafka --hostname kafka \
    --network kafka_net \
    -e KAFKA_CFG_NODE_ID=0 \
	-e KAFKA_KRAFT_CLUSTER_ID=my-cluster \
	-e KAFKA_ENABLE_KRAFT=yes \
    -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
    -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
    -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka:9093 \
    -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
    bitnami/kafka

	Docker run -d -p 6379:6379 --name redis --net redis_net redis

	Docker run -d -p 8761:8761 --net eureka_net --name eureka-server eureka-server

	Docker create -p 8086:8086 --name product_container --net eureka_net  --restart unless-stopped  -e MYSQL_HOST=mysql_container -e MYSQL_PORT=3306  -e MYSQL_DB_NAME=revapp_micro -e MYSQL_USER=root -e MYSQL_ROOT:mypass -e REDIS_HOST=redis -e REDIS_PORT=6379 -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka-server -e EUREKA_PORT=8761  product_service

	Docker network connect mysql_net product_container \
	Docker network connect redis_net product_container \
	Docker network connect kafka_net product_container

	Docker start product_container

	Docker logs -f product_container
	
	Docker run -p 8087:8087 --name review_container --net mysql_net --restart unless-stopped -e MYSQL_HOST=mysql_container -e MYSQL_PORT=3306  -e MYSQL_DB_NAME=revapp_micro -e MYSQL_USER=root -e MYSQL_ROOT:mypass -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka-server -e EUREKA_PORT=8761 -d review_service

	Docker network connect kafka_net review_container \
	Docker network connect eureka_net review_container
	
	Docker start review_container \
	Docker inspect review_container
	
	Docker create -p 8085:8080 --name user_container --net mysql_net --restart unless-stopped -e MYSQL_HOST=mysql_container -e MYSQL_PORT=3306  -e MYSQL_DB_NAME=revapp_micro -e MYSQL_USER=root -e MYSQL_ROOT:mypass -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka-server -e EUREKA_PORT=8761 -e REDIS_HOST=redis -e REDIS_PORT=6379  userdetailservice

	Docker network connect kafka_net user_container \
	Docker network connect eureka_net user_container \
	Docker network connect redis_net user_container

	Docker start user_container

	Docker logs -f user_container
	
	curl localhost:8086/products
	
5. Docker Compose

	Docker-compose -f Review_Application.yaml up \
	Docker-compose -f Review_Application.yaml up -d \
	Docker-compose -f Review_Application.yaml down \
	Docker-compose -f Review_Application.yaml stop 	 
	
	
# Product service curl

<img width="964" alt="product service curl" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/product_service_curl.png">
	
	curl localhost:8087/writereview/11
	
# Review service curl
	
<img width="964" alt="review service curl" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/review_service_curl.png">
	
	
	Docker create -p 8088:8080 --name consumer_container --net kafka_net --restart unless-stopped  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka-server -e EUREKA_PORT=8761  consumer_service

	Docker network connect eureka_net consumer_container  

	Docker start consumer_container

	Docker logs -f consumer_container

	Docker port consumer_container
	
# List of all containers
	
<img width="964" alt="List of all containers" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Container_list.png">
	
	4. Test the application 
	
# Eureka server : 
	
	http://localhost:8761/
	
<img width="964" alt="Eureka server" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Eureka_server.png">
	
# Tomcate server : 
	
	http://localhost:8085/
	
<img width="964" alt="Tomcate server" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/User_tomcat_server.png">
	
# Welcome page
	
	http://localhost:8085/user/userdetailpage
	
<img width="964" alt="welcome page" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Welcome_page_from_user_service.png">
	
# List of products from product service
	
	http://localhost:8085/user/continue
	
<img width="964" alt="List of products" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/List_of_products_from_product_services.png">
	
# Review submission form to review service
	
	http://localhost:8085/user/writereview?id=11
	
<img width="964" alt="Review submission form to review service" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Review_form_from_review_services.png">
	
# Acknowledgment review saved
	
	http://localhost:8085/user/postreview
	
<img width="964" alt="Acknowledgment review saved" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Final_submission.png">
	
# Logs consumer from kafka
	
	http://localhost:8088/kafka-consumer/ajax
	
<img width="964" alt="logs consumer from kafka" src="https://github.com/nish2008/review-application-microservices-master/blob/docker_deployment/images/Consumer_service.png">
