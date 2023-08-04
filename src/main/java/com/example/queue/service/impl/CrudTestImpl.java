package com.example.queue.service.impl;

import com.example.queue.dto.request.OrderDto;
import com.example.queue.dto.response.FooDto;
import com.example.queue.service.CrudTestService;
import com.example.queue.util.ConvertUtil;
import com.example.queue.util.RequestUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CrudTestImpl implements CrudTestService {

    OkHttpClient client = new OkHttpClient();

    @Override
    public List<FooDto> getAllInfo() throws IOException {
        Request request1 = RequestUtil.getAllRequest();
        try (Response response = client.newCall(request1).execute()) {
            return ConvertUtil.toObjects(response.body().string(), FooDto.class);
        }
    }

//    @Override
//    public FooDto addNewInfo(OrderDto orderDto) throws IOException {
//        Gson gson = new Gson();
//        String jsoReqBody = gson.toJson(orderDto);
//
//        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, jsoReqBody);
//        Request request = new Request.Builder()
//                .url(ConfigReader.getParamValue("method.test"))
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return ConvertUtil.toObject(response.body().string(), FooDto.class);
//        }
//    }

    @Override
    public FooDto addNewInfo(OrderDto orderDto) throws IOException {
        Request request = RequestUtil.getPostRequest(orderDto);
        try (Response response = client.newCall(request).execute()) {
            return ConvertUtil.toObject(response.body().string(), FooDto.class);
        }
    }

    @Override
    public FooDto getInfoById(Long id) throws IOException {
        Request request = RequestUtil.getGetRequest(id);
        try (Response response = client.newCall(request).execute()) {
            return ConvertUtil.toObject(response.body().string(), FooDto.class);
        }
    }

    @Override
    public FooDto updateInfoById(Long id, OrderDto orderDto) throws IOException {
        Request request = RequestUtil.getUpdateRequest(id, orderDto);
        try (Response response = client.newCall(request).execute()) {
            return ConvertUtil.toObject(response.body().string(), FooDto.class);
        }
    }

    @Override
    public String deleteById(Long id) throws IOException {
        Request request1 = RequestUtil.getDeleteRequest(id);
        try (Response response = client.newCall(request1).execute()) {
            return response.body().string();
        }
    }
}
