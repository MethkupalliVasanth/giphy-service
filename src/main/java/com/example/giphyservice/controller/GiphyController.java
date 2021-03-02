package com.example.giphyservice.controller;

//import com.example.giphyservice.service.GifyService;

import com.example.giphyservice.service.GiphyService;
import com.example.giphyservice.dto.ListGiphyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class GiphyController {

    @Autowired
    private GiphyService giphyService;

    @GetMapping(path = "/search/{searchTerm}")
    public ResponseEntity<ListGiphyResponse> getGify(@PathVariable String searchTerm) throws URISyntaxException, IOException {
        return new ResponseEntity<>(giphyService.getGify(searchTerm), HttpStatus.OK);
    }
}

