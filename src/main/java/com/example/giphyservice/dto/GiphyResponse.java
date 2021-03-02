package com.example.giphyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GiphyResponse {

    @JsonProperty("gif_id")
    private String gifId;

    private String url;
}
