package ru.kerporation.datastoregrpcmicroservice.repository;

import ru.kerporation.datastoregrpcmicroservice.model.Data;
import ru.kerporation.datastoregrpcmicroservice.model.MeasurementType;
import ru.kerporation.datastoregrpcmicroservice.model.Summary;
import ru.kerporation.datastoregrpcmicroservice.model.SummaryType;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {

    Optional<Summary> findBySensorId(final long sensorId,
                                     final Set<MeasurementType> measurementTypes,
                                     final Set<SummaryType> summaryTypes);

    void handle(final Data data);

}