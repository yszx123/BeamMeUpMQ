BeamMeUpMQ
==========

RESTful API that transfers messages from one RabbitMQ queue to another queue or exchange.

## How it works

__Steps :__

1. Read a message in the source queue
2. Send a new message with the content of step 1. in the destination queue or exchange
3. Repeat step 1. and 2. until you run out of messages

> To avoid circular problems (example: in the case of the use of a dead letter queue as a source queue), an additional Step 0. is made, that counts the number of messages in the source queue at the beginning of the execution. By default, Step 3. will not be repeated more than Step 0. times. You can overwrite this behavior by manually specifying the “limit” parameter (limit=0 will transfer all messages; limit = 3 will only transfer the first 3 messages...)


## Usage and functionalities

>`M` in the tables colums stands for mandatory



#### From queue to queue of a topic

**URL:** /beammeupmq/services/rabbitmq/copy/topicq2q.json

**HTTP Method:** POST

**Parameters**

Name | M | Default | Description 
----------------------- |:---:|:-----:| ----------------------- 
sourceHost              | X |       | The source RabbitMQ server address 
sourcePort              | X |       | The source RabbitMQ server port 
sourceUsername          | X |       | The source RabbitMQ server access username 
sourcePassword          | X |       | The source RabbitMQ server access password associated to the username 
sourceVirtualHost       | X |       | The source RabbitMQ virtual host 
sourceExchange          | X |       | The source RabbitMQ exchange name 
sourceRoutingKey        | X |       | The source RabbitMQ routing key 
sourceQueue             | X |       | The source RabbitMQ queue name 
destinationHost         | X |       | The destination RabbitMQ server address 
destinationPort         | X |       | The destination RabbitMQ server port 
destinationUsername     | X |       | The destination RabbitMQ server access username 
destinationPassword     | X |       | The destination RabbitMQ server access password associated to the username 
destinationVirtualHost  | X |       | The destination RabbitMQ virtual host 
destinationExchange     | X |       | The destination RabbitMQ exchange name 
destinationQueue        | X |       | The destination RabbitMQ queue name 
number                  |   |       | The number of messages to copy. I not provided all messages are copied 
requeue                 |   | false | If `true`, the messages will be requeued 




## Relesases
### Relesase 0.1
- Copy messages from queue to queue of a topic

## Installation
It is possible to eather download the precopiled .war file or clone the project and make and rebuild it. For the second option please refer to Development section and come back after compiling the .war file. For the first option please follow the next steps:

- Download the .war file from [here](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/target/beammeupmq.war)
- Deploy the file on your web server
- (Optional) Navigate to the web application working directory of BeatMeUpMQ into your web server and find the file WEB-INF/classes/logback-beammeupmq.xml and edit it in order to provide a full path(<File> tag value) for the application private log file. It is also possible to change log levels and maximum number of historical log files
- (Optional) Execute a simple tests betwean two queues to validate the installation

### Troubleshooting
- The server should be authorized to establish connections to the remote RabbitMQ servers for the given ports and credentials. 


## Development

### Maven2 build command
		mvn clear license:update-file-header install

## License
The BeatMeUpMQ is under The MIT License (MIT). Here there is the project associated license information:

- [The MIT License (MIT)](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/license/arte_license/license.txt)
- [BeamMeUpMQ project source files' header](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/license/arte_license/header.txt)
