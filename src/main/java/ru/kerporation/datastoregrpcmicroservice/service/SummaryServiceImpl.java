package ru.kerporation.datastoregrpcmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kerporation.datastoregrpcmicroservice.model.Data;
import ru.kerporation.datastoregrpcmicroservice.model.MeasurementType;
import ru.kerporation.datastoregrpcmicroservice.model.Summary;
import ru.kerporation.datastoregrpcmicroservice.model.SummaryType;
import ru.kerporation.datastoregrpcmicroservice.model.exception.SensorNotFoundException;
import ru.kerporation.datastoregrpcmicroservice.repository.SummaryRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(final Long sensorId,
                       final Set<MeasurementType> measurementTypes,
                       final Set<SummaryType> summaryTypes) {
        return summaryRepository.findBySensorId(sensorId,
                        measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                        summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes)
                .orElseThrow(SensorNotFoundException::new);
    }

    @Override
    public void handle(final Data data) {
        summaryRepository.handle(data);
    }

}
