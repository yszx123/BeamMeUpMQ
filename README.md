BeamMeUpMQ
==========

RESTful API that transfers messages from one RabbitMQ queue to another queue or exchange.

## How it works

__Steps :__

1. Read a message in the source queue
2. Send a new message with the content of step 1. in the destination queue or exchange
3. Repeat step 1. and 2. until it runs out of messages

> To avoid circular problems (example: in the case of the use of a dead letter queue as a source queue), an additional Step 0. is made, that counts the number of messages in the source queue at the beginning of the execution. By default, Step 3. will not be repeated more than Step 0. times. You can overwrite this behavior by manually specifying the “limit” parameter (limit=0 will transfer all messages; limit = 3 will only transfer the first 3 messages...)


## Usage and functionalities

#### Beam messages from a queue to an exchange
This method transfers messages from one source queue to a receiving exchange. 
> Please note that all queues binded to the receiving exchange will therefore get a copy of the message

**URL:** /beammeupmq/services/rabbitmq/beam/q2echange.json

**HTTP Method:** POST

**Parameters**

Name | Mandatory | Default | Description 
----------------------- |:---:|:-----:| ----------------------- 
sourceHost              | yes |       | The source RabbitMQ server address 
sourcePort              | yes |       | The source RabbitMQ server port 
sourceUsername          | yes |       | The source RabbitMQ server access username 
sourcePassword          | yes |       | The source RabbitMQ server access password associated to the username 
sourceVirtualHost       | yes |       | The source RabbitMQ virtual host 
sourceExchange          | yes |       | The source RabbitMQ exchange name 
sourceRoutingKey        | yes |       | The source RabbitMQ routing key 
sourceQueue             | yes |       | The source RabbitMQ queue name 
destinationHost         | yes |       | The destination RabbitMQ server address 
destinationPort         | yes |       | The destination RabbitMQ server port 
destinationUsername     | yes |       | The destination RabbitMQ server access username 
destinationPassword     | yes |       | The destination RabbitMQ server access password associated to the username 
destinationVirtualHost  | yes |       | The destination RabbitMQ virtual host 
destinationExchange     | yes |       | The destination RabbitMQ exchange name 
number                  | no  |       | Number of messages to handle. By default all messages are handled
requeue                 | no  | false | If `false`, the message will be transferred from one queue to another (cut and paste)<br/>If `true`, the messages will be copied from the source to the destination (copy and paste) 

#### Beam messages from one queue to another
This method transfers messages from a source queue to a destination queue.

**URL:** /beammeupmq/services/rabbitmq/beam/q2q.json

**HTTP Method:** POST

**Parameters**

Name | Mandatory | Default | Description 
----------------------- |:---:|:-----:| ----------------------- 
sourceHost              | yes |       | The source RabbitMQ server address 
sourcePort              | yes |       | The source RabbitMQ server port 
sourceUsername          | yes |       | The source RabbitMQ server access username 
sourcePassword          | yes |       | The source RabbitMQ server access password associated to the username 
sourceVirtualHost       | yes |       | The source RabbitMQ virtual host 
sourceQueue             | yes |       | The source RabbitMQ queue name 
destinationHost         | yes |       | The destination RabbitMQ server address 
destinationPort         | yes |       | The destination RabbitMQ server port 
destinationUsername     | yes |       | The destination RabbitMQ server access username 
destinationPassword     | yes |       | The destination RabbitMQ server access password associated to the username 
destinationVirtualHost  | yes |       | The destination RabbitMQ virtual host 
destinationQueue        | yes |       | The destination RabbitMQ queue name 
number                  | no  |       | Number of messages to handle. By default all messages are handled
requeue                 | no  | false | If `false`, the message will be transferred from one queue to another (cut and paste)<br/>If `true`, the messages will be copied from the source to the destination (copy and paste) 


## Releases
### Release 0.1
- Beam messages from a source queue to a destination exchange

### Release 0.1.1
- Bugfix : Beaming messages failing when the destination routing key is different than `#`
- Add the possibility to beam messages from one queue to another

## Installation
You can either download the precompiled .war file or clone the project to make your own build. For the manual build option please refer to the development section and come back after compiling the .war file. For the first option please follow the next steps:

- Build or Download the .war file from [here](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/target/beammeupmq.war)
- Deploy the file on your web server

###Configurations
- Options to start the server JVM with

Option | Description
----- | ----
-Dlogback.ContextSelector=JNDI | Configure logback to use the name of the Logback context provided in the web.xml file


###Optionnal configurations
- **Separate application logs:** Navigate to the web application working directory of BeatMeUpMQ into your web server and locate the WEB-INF/classes/logback-beammeupmq.xml file. You should edit this file, to provide a full path(<File> tag value) for the application private log file. It is also possible to change log levels and maximum number of historical log files


## Troubleshooting
- Don't forget to make necessary adjustments to your firewall rules : the server hosting the BeamMeUpMQ API should be authorized to establish connections to the remote RabbitMQ servers for the given ports and credentials (Default RabbitMQ port is `5672`).


## Development

### Maven2 build commands
- Common

		mvn clear install

- To add licence headers to new files

		mvn clear license:update-file-header install


## License
The BeatMeUpMQ is published under The MIT License (MIT). Here there is the project associated license information:

- [The MIT License (MIT)](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/license/arte_license/license.txt)
- [BeamMeUpMQ project source files' header](https://github.com/ArteGEIE/BeamMeUpMQ/blob/master/license/arte_license/header.txt)
