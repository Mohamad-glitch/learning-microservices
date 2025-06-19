package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    // all this code just to access the service form this service
    // it will be managed from spring

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    // variable to hold the GRPC client
    // every time we use stub in more than one it will waite
    // until the first one finishes like first come, first served
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    // we need to add a environment variable in docker to get these values
    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int port
            ) {

        log.info("Connecting to Billing Service at {}:{}", serverAddress, port);

        // this to get the values then inject them to access the service (GRPC in this case)
        // if we used service like AWS the address will change to another thing in this code it will work well
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, port)
                .usePlaintext().build();

        blockingStub = BillingServiceGrpc.newBlockingStub(channel);

    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {

        BillingRequest request = BillingRequest.newBuilder().setName(name).setEmail(email).build();

        // the blockingStub is the client, and it will waite until the request finishes
        BillingResponse response = blockingStub.createBillingAccount(request);

        log.info("Received response from Billing Service via GRPC: {}", response);

        return response;
    }

}
