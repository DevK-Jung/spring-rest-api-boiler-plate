package com.kjung.boilerplate.moduleapi.sample.service.impl;

import com.kjung.boilerplate.moduleapi.sample.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    @Override
    public String getMessage(String name) {
        return "Hello, " + name;
    }
}
