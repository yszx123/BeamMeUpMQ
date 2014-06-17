BeamMeUpMQ
==========

RESTful API that transfers messages from one RabbitMQ queue to another queue or exchange.

#### How it works

__Steps :__

1. Read a message in the source queue
2. Send a new message with the content of step 1. in the destination queue or exchange
3. Repeat step 1. and 2. until you run out of messages

> To avoid circular problems (example: in the case of the use of a dead letter queue as a source queue), an additional Step 0. is made, that counts the number of messages in the source queue at the beginning of the execution. By default, Step 3. will not be repeated more than Step 0. times. You can overwrite this behavior by manually specifying the “limit” parameter (limit=0 will transfer all messages; limit = 3 will only transfer the first 3 messages...)

