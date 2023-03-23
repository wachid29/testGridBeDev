package com.the.basic.tech.info.Controllers;


import com.the.basic.tech.info.Services.mainService;
import com.the.basic.tech.info.Utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class mainController {

    @Autowired
    private mainService mainService;


    @GetMapping("/fibonacci")
    private ResponseEntity<MessageModel> fibonacci(@RequestParam int input) {
        ResponseEntity responseEntity = mainService.fibonacci(input);
        return responseEntity;
    }
    @GetMapping("/longestWord")
    private ResponseEntity<MessageModel> longestWord(@RequestParam String input) {
        ResponseEntity responseEntity = mainService.longestWord(input);
        return responseEntity;
    }

    @GetMapping("/ListData")
    public ResponseEntity ListData(){

        ResponseEntity responseEntity = mainService.getListData();
        return responseEntity;
    }

    @GetMapping("/data")
    public ResponseEntity data(){

        ResponseEntity responseEntity = mainService.getData();
        return responseEntity;
    }

}

