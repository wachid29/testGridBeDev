package com.the.basic.tech.info.Controllers;


import com.the.basic.tech.info.Entity.*;
import com.the.basic.tech.info.Services.postDataService;
import com.the.basic.tech.info.Utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class authController {

    @Autowired
    private postDataService postDataService;
    @PostMapping("/signup")
    private ResponseEntity<MessageModel> signup(@RequestBody tabelUser tabelUser) {
        ResponseEntity responseEntity = postDataService.signup(tabelUser);
        return responseEntity;
    }

    @PostMapping("/login")
    private ResponseEntity<MessageModel> login(@RequestBody tabelUser tabelUser) {
        ResponseEntity responseEntity = postDataService.login(tabelUser);
        return responseEntity;
    }

    @PostMapping("/addBarang")
    private ResponseEntity<MessageModel> addBarang(@RequestBody tabelBarang tabelBarang) {
        ResponseEntity responseEntity = postDataService.addDataBarang(tabelBarang);
        return responseEntity;
    }


}

