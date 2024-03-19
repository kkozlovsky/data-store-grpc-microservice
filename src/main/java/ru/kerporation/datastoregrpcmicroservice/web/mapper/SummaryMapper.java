package ru.kerporation.datastoregrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.kerporation.datastoregrpcmicroservice.model.Summary;
import ru.kerporation.datastoregrpcmicroservice.web.dto.SummaryDto;

@Mapper(componentModel = "spring")
public interface SummaryMapper extends Mappable<Summary, SummaryDto> {
}