package com.example.giphyservice.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GiphyMapper {

    GiphyMapper INSTANCE = Mappers.getMapper(GiphyMapper.class);

    @Mapping(source = "giphyRequest.url", target = "url")
    @Mapping(source = "giphyRequest.id", target= "gifId")
    GiphyResponse giphyResponseToListGiphyResponse(GiphyRequest giphyRequest);
}
