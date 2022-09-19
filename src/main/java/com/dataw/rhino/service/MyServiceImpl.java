package com.dataw.rhino.service;

import org.springframework.stereotype.Service;

/**
 * @author Kinyi_Chan
 * @since 2022-09-10
 */
@Service
public class MyServiceImpl implements MyService {

    @Override
    public String random() {
        return String.valueOf(Math.random() * 100);
    }

}
