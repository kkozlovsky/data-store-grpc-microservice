package ru.kerporation.datastoregrpcmicroservice.service;

import ru.kerporation.datastoregrpcmicroservice.model.Data;
import ru.kerporation.datastoregrpcmicroservice.model.MeasurementType;
import ru.kerporation.datastoregrpcmicroservice.model.Summary;
import ru.kerporation.datastoregrpcmicroservice.model.SummaryType;

import java.util.Set;

public interface SummaryService {

    Summary get(final Long sensorId,
                final Set<MeasurementType> measurementTypes,
                final Set<SummaryType> summaryTypes);

    void handle(final Data data);

}
