package com.example.giphyservice.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.giphyservice.dto.ListGiphyResponse;
import com.example.giphyservice.exception.ISCRestAPIGeneralException;
import com.example.giphyservice.exception.ISCRestAPINotFoundException;
import com.example.giphyservice.transformer.GiphyTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class GiphyService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GiphyTransformer giphyTransformer;

    @Value("${api.key}")
    private String apiKey;

    public ListGiphyResponse getGify(String searchTerm) {

        HttpGet httpGet = createHttpGet(searchTerm);
        httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
        HttpResponse response;

        CloseableHttpClient httpClient;
        try {
            httpClient = HttpClients.custom().disableContentCompression().build();
        } catch (Exception e) {
            throw new ISCRestAPIGeneralException(e.getMessage());
        };

        try {
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            throw new ISCRestAPIGeneralException("ERROR GETTING GIFS FROM GIPHY");
        }

        return giphyTransformer.transformGiphyResponse(response);


    }

    private HttpGet createHttpGet(String searchTerm) {
        URIBuilder builder;
        try {
            builder = new URIBuilder("http://api.giphy.com/v1/gifs/search");
        } catch (Exception e) {
            throw new ISCRestAPIGeneralException(e.getMessage());
        }

        builder.setParameter("api_key", apiKey).setParameter("q", searchTerm).setParameter("limit", "5");

        HttpGet httpGet;
        try {
            httpGet = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            throw new ISCRestAPINotFoundException(e.getMessage());
        }
        httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
        return httpGet;
    }


}
