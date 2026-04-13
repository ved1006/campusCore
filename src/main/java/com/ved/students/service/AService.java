package com.ved.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AService {

    @Autowired
    private BService bService;

    public void callB() {
        bService.doSomething();
    }
}