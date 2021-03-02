package com.example.giphyservice.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GiphyMapper {

    @Mapping(source = "url", target = "url")
    @Mapping(source = "id", target= "gifId")
    public abstract GiphyResponse giphyResponseToListGiphyResponse(GiphyRequest giphyRequest);
}
