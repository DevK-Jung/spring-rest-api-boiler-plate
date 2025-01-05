package com.kjung.boilerplate.moduleapi.sample.controller;

import com.kjung.boilerplate.moduleapi.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sample")
public class SampleController {

    private final SampleService sampleService;

    @GetMapping
    public String getSample(@RequestParam(required = false) String name) {
        return sampleService.getMessage(name);
    }
}
