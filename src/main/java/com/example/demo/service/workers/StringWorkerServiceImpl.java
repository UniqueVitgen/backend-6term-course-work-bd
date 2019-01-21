package com.example.demo.service.workers;

import org.springframework.stereotype.Service;

@Service
public class StringWorkerServiceImpl implements StringWorkerService {

    @Override
    public String createEmptyString(int count) {
        String string = "";
        for(int i = 0; i < count; i++) {
            string += " ";
        }
        return string;
    }
}
