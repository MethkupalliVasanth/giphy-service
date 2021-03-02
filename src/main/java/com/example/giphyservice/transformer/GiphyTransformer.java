package com.example.giphyservice.transformer;

import com.example.giphyservice.dto.GiphyMapper;
import com.example.giphyservice.dto.GiphyRequest;
import com.example.giphyservice.dto.ListGiphyResponse;
import com.example.giphyservice.dto.GiphyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GiphyTransformer {

    public ListGiphyResponse transformGiphyResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        Header contentEncodingHeader = entity.getContentEncoding();

        if (contentEncodingHeader != null) {
            HeaderElement[] encodings = contentEncodingHeader.getElements();
            for (int i = 0; i < encodings.length; i++) {
                if (encodings[i].getName().equalsIgnoreCase("gzip")) {
                    entity = new GzipDecompressingEntity(entity);
                    break;
                }
            }
        }

        String output = EntityUtils.toString(entity, Charset.forName("UTF-8").name());
        JSONObject jsonObject = new JSONObject(output);
        List<HashMap<String, Object>> jsonList = (List<HashMap<String, Object>>) jsonObject.toMap().get("data");
        return getGiphyResponseFromJson(jsonList);
    }

    public ListGiphyResponse getGiphyResponseFromJson(List<HashMap<String, Object>> gifyHashMapList) {
        if (gifyHashMapList.size() < 5) {
            return new ListGiphyResponse();
        } else {
            List<GiphyResponse> gifyRequestList = new ArrayList<>();
            ListGiphyResponse listGiphyResponse = new ListGiphyResponse();
            ObjectMapper objectMapper = new ObjectMapper();

            for (Map<String, Object> entry : gifyHashMapList) {

                GiphyRequest giphyRequest = objectMapper.convertValue(entry, GiphyRequest.class);
                GiphyResponse giphyResponse = GiphyMapper.INSTANCE.giphyResponseToListGiphyResponse(giphyRequest);
                gifyRequestList.add(giphyResponse);

            }

            listGiphyResponse.setGiphyResponseList(gifyRequestList);


            return listGiphyResponse;
        }

    }
}
