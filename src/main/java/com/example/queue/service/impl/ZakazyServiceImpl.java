package com.example.queue.service.impl;

import com.example.queue.Exception.BaseException;
import com.example.queue.dto.response.ZakazResponse;
import com.example.queue.entity.ZakazEntity;
import com.example.queue.repository.ZakazyRepo;
import com.example.queue.service.ZakazyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ZakazyServiceImpl implements ZakazyService {

    private final MessageSource messageSource;

    private final ZakazyRepo zakazyRepo;

    @Override
    public ZakazResponse getOrderById(Long id, Locale locale) {
        ZakazEntity zakazEntity = zakazyRepo.findById(id).orElseThrow(
                () -> new BaseException(messageSource.getMessage("order.notfound", null, locale), HttpStatus.NOT_FOUND)
        );
        if (zakazEntity != null) {
            return ZakazResponse.builder()
                    .id(zakazEntity.getId())
                    .productName(zakazEntity.getProductEntity().getName())
                    .userName(zakazEntity.getUserEntity().getUserName())
                    .quantity(zakazEntity.getQuantity())
                    .price(zakazEntity.getSumm())
                    .status(zakazEntity.getStatus())
                    .build();

        }
        else return null;
    }
}
