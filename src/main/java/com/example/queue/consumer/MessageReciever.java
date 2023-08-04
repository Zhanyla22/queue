package com.example.queue.consumer;

import com.example.queue.Exception.BaseException;
import com.example.queue.dto.request.ZakazRequest;
import com.example.queue.entity.ProductEntity;
import com.example.queue.entity.UserEntity;
import com.example.queue.entity.ZakazEntity;
import com.example.queue.enums.Status;
import com.example.queue.repository.ProductRepo;
import com.example.queue.repository.UserRepo;
import com.example.queue.repository.ZakazyRepo;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageReciever {

    private final ProductRepo productRepo;

    private final ZakazyRepo zakazyRepo;

    private final UserRepo userRepo;

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageReciever.class);


    @JmsListener(destination = "request")
    public void onMessage(ZakazRequest message) {
        LOGGER.info("заказ : = '{}'", message);
        ProductEntity productEntity = productRepo.findById(message.getProductId()).orElseThrow(
                () -> new BaseException("нет такого продукта с айди " + message.getProductId(), HttpStatus.NOT_FOUND)
        );
        UserEntity userEntity = userRepo.findById(message.getUserId()).orElseThrow(
                () -> new BaseException("юзер не найден " + message.getUserId(), HttpStatus.NOT_FOUND)
        );
        if (message.getProductQuantity() <= productEntity.getQuantity()) {
            zakazyRepo.save(ZakazEntity.builder()
                    .productEntity(productEntity)
                    .summ(message.getProductQuantity() * productEntity.getPrice())
                    .userEntity(userEntity)
                    .build());
        } else
            throw new BaseException("недостаточное количество продукта, на базе имеется  " + productEntity.getQuantity(), HttpStatus.CONFLICT);
    }

//    @JmsListener(destination = "request")
//    public void onMessage(String message) {
//        LOGGER.info("заказ : = '{}'", message);
//    }

    @JmsListener(destination = "request")
    public void onMessage(String message) {
        ZakazRequest zakazRequest = parseJsonToZakazy(message);
        ProductEntity productEntity = productRepo.findById(zakazRequest.getProductId()).orElseThrow(
                () -> new BaseException("нет такого продукта с айди " + zakazRequest.getProductId(), HttpStatus.NOT_FOUND)
        );
        UserEntity userEntity = userRepo.findById(zakazRequest.getUserId()).orElseThrow(
                () -> new BaseException("юзер не найден " + zakazRequest.getUserId(), HttpStatus.NOT_FOUND)
        );
        if (zakazRequest.getProductQuantity() <= productEntity.getQuantity()) {
            productEntity.setQuantity(productEntity.getQuantity() - zakazRequest.getProductQuantity());
            productRepo.save(productEntity);
            zakazyRepo.save(ZakazEntity.builder()
                    .productEntity(productEntity)
                    .summ(zakazRequest.getProductQuantity() * productEntity.getPrice())
                    .quantity(zakazRequest.getProductQuantity())
                    .userEntity(userEntity)
                    .status(Status.ACCEPTED)
                    .build());
            LOGGER.info("данные сохранены в бд '{}'", message);
        } else
            throw new BaseException("недостаточное количество продукта, на базе имеется  " + productEntity.getQuantity(), HttpStatus.CONFLICT);
    }


    private ZakazRequest parseJsonToZakazy(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ZakazRequest zakazRequest = objectMapper.readValue(json, ZakazRequest.class);
            return zakazRequest;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new BaseException("парсинг прерван", HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("парсинг прерван", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // todo
    /*
    JSF
    PrimeFaces / OmniFaces / ...
    PrimeFaces showcase - 11/12
     */
}
