# BVS  Assessment

Event Driven reactive micro-services with a Gateway.

## Components
- Producer Docker Image
- Consumer Docker Image
- RabbitMQ Messaging Docker Image
- MySQL DataBase Docker Image




## Installation

#### Use the [docker compose](https://docs.docker.com/compose/install/) to install application in any machine. 


```bash
 docker compose up
```
please restart producer docker container because rabbitmq require time start
#### or 

#### User [Kubernetes](https://kubernetes.io/) Container Orchestration for load balancing, routing, service discovery and auto scaling  

```bash
 kubectl apply -f ./deployment.yaml
```

## Usage

##### to add new routes to the database 

```json
post : http://localhost:8090/save
{
  "path":"/consumer2",
  "consumer":"consumer",
  "method":"GET"
}
```
##### to send new request to the consumer through the router
```json
post : http://localhost:8090/{consumer-path}
{
  payload
}
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
