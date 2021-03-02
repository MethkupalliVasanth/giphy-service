package com.example.giphyservice.service;

import com.example.giphyservice.dto.ListGiphyResponse;
import com.example.giphyservice.transformer.GiphyTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

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

    public ListGiphyResponse getGify(String searchTerm) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder("http://api.giphy.com/v1/gifs/search");

        Header header = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Header> headers = new ArrayList<>();
        headers.add(header);
        CloseableHttpClient httpClient = HttpClients.custom().disableContentCompression().build();
        builder.setParameter("api_key", apiKey).setParameter("q", searchTerm).setParameter("limit", "5");


        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");

        HttpResponse response = httpClient.execute(httpGet);
        return giphyTransformer.transformGiphyResponse(response);
    }


}
