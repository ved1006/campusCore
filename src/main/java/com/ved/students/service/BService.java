
package com.ved.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BService {

    private AService aService;

    public void doSomething() {
        aService.callB();
    }
}
