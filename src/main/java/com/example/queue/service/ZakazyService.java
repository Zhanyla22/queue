package com.example.queue.service;

import com.example.queue.dto.response.ZakazResponse;

import java.util.Locale;

public interface ZakazyService {

     ZakazResponse getOrderById(Long id, Locale locale);
}
