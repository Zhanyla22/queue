package com.example.queue.controller;

import com.example.queue.controller.base.BaseController;
import com.example.queue.dto.request.OrderDto;
import com.example.queue.dto.response.FooDto;
import com.example.queue.dto.response.ResponseDto;
import com.example.queue.service.CrudTestService;
import com.example.queue.util.ConvertUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class CrudTestController extends BaseController {

    private final CrudTestService crudTestService;

    private static final Logger logger = LoggerFactory.getLogger(ZakazyController.class);

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNew(@RequestBody OrderDto orderDto, HttpServletRequest request1) throws IOException {
        logger.info("Request || Type: {} || url : {}", request1.getMethod(), request1.getRequestURL());
        FooDto fooDto = crudTestService.addNewInfo(orderDto);
        logger.info("Response || data : {}",ConvertUtil.toJson(fooDto));
        return constructSuccessResponse(fooDto);
    }
//TODO : data to json - done, logi в сервисе HttpServ убрать - если на уровне сервиса как узнать юрл?
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateInfo(@PathVariable Long id, @RequestBody OrderDto orderDto, HttpServletRequest request) throws IOException {
        logger.info("Request || Type: {} || url : {}", request.getMethod(), request.getRequestURL());
        FooDto fooDto = crudTestService.updateInfoById(id, orderDto);
        logger.info("Response || data : {}", ConvertUtil.toJson(fooDto));
        return constructSuccessResponse(fooDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAll(HttpServletRequest request) throws IOException {
        logger.info("Request || Type: {} || url : {}", request.getMethod(), request.getRequestURL());
        List<FooDto> fooDtoList = crudTestService.getAllInfo();
        logger.info("Response || data : {}, status : {}");
        return constructSuccessResponse(fooDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id, HttpServletRequest request) throws IOException {
        logger.info("Request || Type: {} || url : {}", request.getMethod(), request.getRequestURL());
        FooDto fooDto = crudTestService.getInfoById(id);
        logger.info("Response || data : {}, status : {}");
        return constructSuccessResponse(fooDto);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id, HttpServletRequest request) throws Exception{
        logger.info("Request || Type: {} || url : {}", request.getMethod(), request.getRequestURL());
        return constructSuccessResponse(crudTestService.deleteById(id),"info with id "+id+ " is deleted");
    }
}
