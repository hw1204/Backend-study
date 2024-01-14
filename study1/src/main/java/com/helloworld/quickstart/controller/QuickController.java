package com.helloworld.quickstart.controller;

import com.helloworld.quickstart.dto.ItemDto;
import com.helloworld.quickstart.dto.ResponseDto;
import com.helloworld.quickstart.service.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j

public class QuickController {

    @Autowired
    private QuickService quickService;

    @GetMapping("/dummy")
    public String dummy() {
        log.info("dummy");
        return "{}";
    }

    @GetMapping("dummy2")
    public String dummy2() {
        log.info("dummy");
        return "dummy2";
    }

    // 데이터를 서버에서 읽기만
    @GetMapping("/member")
    public String getMember(@RequestParam("empNo") String empNo
    , @RequestParam("year") int year) {
        log.info("empNo: {}", empNo);
        log.info("year: {}", year);

        return "ok";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@PathVariable("id") String id) {
        log.info("id: {}", id);
        return "ok";
    }

    // 데이터를 서버에 등록
    @PostMapping("/item")
    public ResponseDto registerItem(@RequestBody ItemDto item) {    // id, name
        log.info("item: {}", item);

        // QuickService quickService = new QuickService();   위에서 선언
        boolean b = quickService.registerItem(item);
        if(b == true){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ok");
            return responseDto;
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("fail");
        return responseDto;

    }


    @GetMapping("/item")
    public ItemDto getItem(@RequestParam("id") String id){
        ItemDto res = quickService.getItemById(id);
        return res;
    }



}
