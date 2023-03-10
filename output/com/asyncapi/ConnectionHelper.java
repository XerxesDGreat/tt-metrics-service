
/*
* (c) Copyright IBM Corporation 2021
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.asyncapi;
  
import java.util.logging.*;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.asyncapi.Connection;

public class ConnectionHelper {

    private static final Level LOGLEVEL = Level.ALL;
    private static final Logger logger = Logger.getLogger("com.asyncapi");

    // Create variables for the connection to Kafka
    private String CLIENT_ID = null;
    private String BOOTSTRAP_ADDRESS = null;
    private String APP_USER = null; // User name that application uses to connect to Kafka
    private String APP_PASSWORD = null; // Password that the application uses to connect to Kafka


    public ConnectionHelper (String id, Connection connection) {
        kafkaConnectionVariables(id, connection);
        logger.info("Application is starting");
    }


    private void kafkaConnectionVariables(String id, Connection connection) {
        CLIENT_ID = id;
        BOOTSTRAP_ADDRESS = connection.BOOTSTRAP_ADDRESS;
        APP_USER = connection.APP_USER;
        APP_PASSWORD = connection.APP_PASSWORD;
    }


    private Properties getProperties() {
        Properties props = new Properties();
        props.put("client.id", CLIENT_ID);
        props.put("bootstrap.servers", BOOTSTRAP_ADDRESS);
        props.put("security.protocol", "PLAINTEXT");
        
        
        return props;
    }

    public KafkaProducer createProducer() {
        Properties props = getProperties();
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        return new KafkaProducer(props);
    }

    public KafkaConsumer createConsumer(String topic) {
        Properties props = getProperties();
        props.put("group.id", CLIENT_ID);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer consumer = new KafkaConsumer(props);
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }
}
