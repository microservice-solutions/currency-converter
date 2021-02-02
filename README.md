### currency-converter

![GH Build](https://img.shields.io/github/workflow/status/microservice-solutions/currency-converter/Java%20CI%20with%20Maven?label=GH%20Build)

The project is built using Github Actions.\
A docker image is also created for this project and is pushed in docker hub microservicesolutions/currency-converter:latest


The swagger specs for this project can be seen with the below url (default port 8081):\
http://localhost:8081/swagger-ui/


The docker image can be pulled using:\
`docker pull microservicesolutions/currency-converter`

The docker image can be run using:\
`docker run -dt -p 8081:8081 microservicesolutions/currency-converter`
