# Online Medication Platform  
The frontend and the backend of an online medication platform that allows doctors to monitor patients in real-time and prescribe meds.  
The backend is implemented in Java with Spring Boot adhering to OOP principles. 
Monitoring devices record and send patients' data to a RabbitMQ broker which are further read and persisted by the backend.
The frontend is developed in ReactJS and Websockets to notify patients when to take their meds.

## System Architecture  
![System Architecture](images/system_architecture.png)

## Deployment Diagram  
![Deployement Diagram](images/deployment_diagram.png)

## Database Design
![Database Design](images/db_design.png)