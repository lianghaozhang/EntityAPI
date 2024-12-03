package com.lianghaozhang.entityapi.controller;

import com.lianghaozhang.entityapi.mapper.EntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entityApi")
public class EntityController {

    @Autowired
    private EntityApi entityApi;

    @GetMapping("/test")
    public List<String> test() {
        return entityApi.getAllDbTableName();
    }

    /*@PostMapping("/test")
    public void test(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
    }*/
}
