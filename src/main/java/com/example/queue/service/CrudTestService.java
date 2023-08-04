package com.example.queue.service;

import com.example.queue.dto.request.OrderDto;
import com.example.queue.dto.response.FooDto;

import java.io.IOException;
import java.util.List;

public interface CrudTestService {

    List<FooDto> getAllInfo() throws IOException;

    FooDto getInfoById(Long id)  throws IOException;

    FooDto updateInfoById(Long id,OrderDto orderDto) throws IOException;

    String deleteById(Long id)  throws IOException;

    FooDto addNewInfo(OrderDto orderDto) throws IOException;
}
