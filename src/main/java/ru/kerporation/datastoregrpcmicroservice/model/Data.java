package ru.kerporation.datastoregrpcmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.kerporation.grpccommon.GRPCData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    private Long id;
    private Long sensorId;
    private LocalDateTime timestamp;
    private double measurement;
    private MeasurementType measurementType;

    public Data(final GRPCData data) {
        this.id = data.getId();
        this.sensorId = data.getSensorId();
        this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getTimestamp().getSeconds(), data.getTimestamp().getNanos()), ZoneId.systemDefault());
        this.measurement = data.getMeasurement();
        this.measurementType = MeasurementType.valueOf(data.getMeasurementType().name());
    }

}
