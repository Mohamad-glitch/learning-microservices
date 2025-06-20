package com.pm.patientservice.kafka;

// all the code for kafka Producer
// this class will be responsible for sending event to given kafka topic

import com.pm.patientservice.repository.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    // this how we define the message type, and we use kafka template to send these messages as well
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder().setPatientId(patient.getId().toString())
                .setEmail(patient.getEmail())
                .setName(patient.getName())
                .setEventType("PATIENT_CREATED") //<- this used to describe particular event inside a topic like if
                // I have a service that uses event 2 like create and delete a patient this will tell here oh we have done that
                .build();

        try{

            kafkaTemplate.send("patient", event.toByteArray());// here we convert response to a byte array
            log.info("Sent event to Kafka topic");

        }catch(Exception e){
            log.error("error while sending patient event: {}", event);
        }
    }

}
