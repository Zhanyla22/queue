package com.example.queue.util;

import com.example.queue.dto.request.OrderDto;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static RequestBody getBody(OrderDto orderDto) {
        return okhttp3.RequestBody.create(JSON, ConvertUtil.toJson(orderDto));
    }

    public static Request getAllRequest() {
        Request request = new Request.Builder()
                .url(ConfigReader.getParamValue("method.test"))
                .get()
                .build();
        return request;
    }

    public static Request getPostRequest(OrderDto body) {
        Request request = new Request.Builder()
                .url(ConfigReader.getParamValue("method.test"))
                .post(getBody(body))
                .build();
        return request;
    }

    public static Request getGetRequest(Long id) {
        Request request = new Request.Builder()
                .url(ConfigReader.getParamValue("method.test.id") + id)
                .get()
                .build();
        return request;
    }

    public static Request getUpdateRequest(Long id, OrderDto orderDto) {
        Request request = new Request.Builder()
                .url(ConfigReader.getParamValue("method.test.id") + id)
                .post(getBody(orderDto))
                .build();
        return request;
    }

    public static Request getDeleteRequest(Long id) {
        Request request = new Request.Builder()
                .url(ConfigReader.getParamValue("method.test.id") + id)
                .delete()
                .build();
        return request;
    }
}
