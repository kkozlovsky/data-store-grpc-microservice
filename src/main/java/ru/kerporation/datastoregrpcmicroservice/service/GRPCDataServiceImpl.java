package ru.kerporation.datastoregrpcmicroservice.service;

import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kerporation.datastoregrpcmicroservice.model.Data;
import ru.kerporation.grpccommon.AnalyticsServerGrpc;
import ru.kerporation.grpccommon.GRPCAnalyticsRequest;
import ru.kerporation.grpccommon.GRPCData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GRPCDataServiceImpl implements GRPCDataService {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final SummaryService summaryService;

    @GrpcClient(value = "data-store-async")
    private AnalyticsServerGrpc.AnalyticsServerStub asyncStub;

    @Value("${fetch.batch-size}")
    private int batchSize;

    @PostConstruct
    public void init() {
        fetchMessages();
    }

    @Override
    @SneakyThrows
    public void fetchMessages() {
        executorService.scheduleAtFixedRate(() -> asyncStub.askForData(GRPCAnalyticsRequest.newBuilder().setBatchSize(batchSize).build(), new StreamObserver<>() {
            @Override
            public void onNext(final GRPCData grpcData) {
                summaryService.handle(new Data(grpcData));
            }

            @Override
            public void onError(final Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                System.out.println("Batch was handled.");
            }
        }), 0, 10, TimeUnit.SECONDS);
    }

}