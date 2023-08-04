package com.example.queue.controller;


import com.example.queue.controller.base.BaseController;
import com.example.queue.dto.request.ZakazRequest;
import com.example.queue.dto.response.ResponseDto;
import com.example.queue.producer.MessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zakazy")
public class ZakazyController extends BaseController {

    @Autowired
    private MessageSender messageSender;


    @PostMapping("/request")
    public ResponseEntity<ResponseDto> requestZakaz(@RequestBody ZakazRequest zakazRequest, @RequestParam long time) throws JsonProcessingException {
        return constructSuccessResponse(messageSender.sendObjectToQueue(zakazRequest, time));
    }
//webclient
//    @GetMapping("/get-fact")
//    public ResponseEntity<ResponseDto> getFact(HttpServletRequest request) {
//        logger.info("Request || Type: {} || url : {}", request.getMethod(), request.getRequestURL());
//        Optional<FactDto> factDto = client.build()
//                .get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(FactDto.class)
//                .blockOptional();
//        if(factDto.isPresent()) {
//            logger.info("Response || url : {}", url);
//        }
//        return constructSuccessResponse(factDto);
//    }
}
