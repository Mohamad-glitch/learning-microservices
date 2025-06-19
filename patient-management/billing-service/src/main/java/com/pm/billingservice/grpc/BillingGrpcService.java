package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    /*
        What does the StreamObserver do?

        The main function of StreamObserver is to handle
        multiple messages within a single request, not multiple separate requests.

        The answer is from YouTube video is

        Running multiple responses to the client at the same time, it accepts back-and-forth communication to the client

        * */
    @Override
    public void createBillingAccount(BillingRequest billingRequest
            , StreamObserver<BillingResponse> responseObserver) {
        // from the grpc or the proto file we created a method, and now
        // we are in the method to Override it (add our logic here)

        log.info("Creating billing account for billing request: {}", billingRequest.toString());

        // Business logic - e.g save to DB, preform a calculation etx....

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("123")
                .setStatus("ACCEPTED")
                .build();

        // we can send more than one response in the same method using responseObserver.onNext(with the value);

        responseObserver.onNext(response);// this to send back the result
        responseObserver.onCompleted();// this does 'we are ready and completed the response' and then end the service or the method
    }

}































