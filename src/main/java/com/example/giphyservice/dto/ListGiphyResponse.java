package com.example.giphyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListGiphyResponse {

    @JsonProperty("data")
    private List<GiphyResponse> giphyResponseList;
}
