package com.example.giphyservice.transformer;

import com.example.giphyservice.dto.ListGiphyResponse;
import org.apache.http.HttpResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class GiphyTransformerTest {

    @InjectMocks
    @Spy
    private GiphyTransformer giphyTransformer;

    @Mock
    private ListGiphyResponse listGiphyResponse;


    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getGiphyResponseFromJson_happyPath_5_responses() {
        ArrayList<HashMap<String, Object>> gifyResponseList = new ArrayList<>();

        HashMap<String, Object> gifyObject1 = new HashMap<>();
        gifyObject1.put("type", "gif");
        gifyObject1.put("id", "moto");
        gifyObject1.put("url", "http://giphy.com/1234");

        HashMap<String, Object> gifyObject2 = new HashMap<>();
        gifyObject2.put("type", "gif");
        gifyObject2.put("id", "motomoto");
        gifyObject2.put("url", "http://giphy.com/4567");

        HashMap<String, Object> gifyObject3 = new HashMap<>();
        gifyObject3.put("type", "gif");
        gifyObject3.put("id", "simba");
        gifyObject3.put("url", "http://giphy.com/6789");

        HashMap<String, Object> gifyObject4 = new HashMap<>();
        gifyObject4.put("type", "gif");
        gifyObject4.put("id", "fox");
        gifyObject4.put("url", "http://giphy.com/6749");

        HashMap<String, Object> gifyObject5 = new HashMap<>();
        gifyObject5.put("type", "gif");
        gifyObject5.put("id", "timon");
        gifyObject5.put("url", "http://giphy.com/6359");

        gifyResponseList.add(gifyObject1);
        gifyResponseList.add(gifyObject2);
        gifyResponseList.add(gifyObject3);
        gifyResponseList.add(gifyObject4);
        gifyResponseList.add(gifyObject5);


        listGiphyResponse = giphyTransformer.getGiphyResponseFromJson(gifyResponseList);
        assertEquals(listGiphyResponse.getGiphyResponseList().size(), 5);
        assertEquals(listGiphyResponse.getGiphyResponseList().get(0).getGifId(),"moto");
        assertEquals(listGiphyResponse.getGiphyResponseList().get(1).getGifId(),"motomoto");
        assertEquals(listGiphyResponse.getGiphyResponseList().get(2).getGifId(),"simba");
        assertEquals(listGiphyResponse.getGiphyResponseList().get(3).getGifId(),"fox");
        assertEquals(listGiphyResponse.getGiphyResponseList().get(4).getGifId(),"timon");
    }

    @Test
    public void test_getGiphyResponseFromJson_happyPath_lessThan5() {
        ArrayList<HashMap<String, Object>> gifyResponseList = new ArrayList<>();

        HashMap<String, Object> gifyObject1 = new HashMap<>();
        gifyObject1.put("type", "gif");
        gifyObject1.put("id", "moto");
        gifyObject1.put("url", "http://giphy.com/1234");

        HashMap<String, Object> gifyObject2 = new HashMap<>();
        gifyObject2.put("type", "gif");
        gifyObject2.put("id", "motomoto");
        gifyObject2.put("url", "http://giphy.com/4567");

        HashMap<String, Object> gifyObject3 = new HashMap<>();
        gifyObject3.put("type", "gif");
        gifyObject3.put("id", "simba");
        gifyObject3.put("url", "http://giphy.com/6789");

        gifyResponseList.add(gifyObject1);
        gifyResponseList.add(gifyObject2);
        gifyResponseList.add(gifyObject3);

        listGiphyResponse = giphyTransformer.getGiphyResponseFromJson(gifyResponseList);
        assertNull(listGiphyResponse.getGiphyResponseList());
    }
}
