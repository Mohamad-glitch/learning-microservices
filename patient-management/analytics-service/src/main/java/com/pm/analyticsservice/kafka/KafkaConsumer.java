package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    // here, where the message will be caught by the kafka consumer,
    // we have sent the message as a byte array so the parameter will be a byte array

    // now we have to tell spring this is a kafka consumer like this
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    // this says like this any event in patient topic will be consumed by this method
    // and the groupId tells the kafka broker who is this consumer
    public void consumeEvents(byte[] event) {

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            // here is the business logic
            log.info("received patient event : {}",patientEvent.toString());
            System.out.println("we are at the analytic service and have this patient info " + patientEvent);



        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}",e.getMessage());
            // if we added a throw an exception, it may cause the server to go down

        }

    }

}
