package ru.kerporation.datastoregrpcmicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kerporation.datastoregrpcmicroservice.model.MeasurementType;
import ru.kerporation.datastoregrpcmicroservice.model.Summary;
import ru.kerporation.datastoregrpcmicroservice.model.SummaryType;
import ru.kerporation.datastoregrpcmicroservice.service.SummaryService;
import ru.kerporation.datastoregrpcmicroservice.web.dto.SummaryDto;
import ru.kerporation.datastoregrpcmicroservice.web.mapper.SummaryMapper;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    @GetMapping("/summary/{sensorId}")
    public SummaryDto getSummary(@PathVariable final long sensorId,
                                 @RequestParam(value = "mt", required = false) final Set<MeasurementType> measurementTypes,
                                 @RequestParam(value = "st", required = false) final Set<SummaryType> summaryTypes) {
        final Summary summary = summaryService.get(sensorId, measurementTypes, summaryTypes);
        return summaryMapper.toDto(summary);
    }
}