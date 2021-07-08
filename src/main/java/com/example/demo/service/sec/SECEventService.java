package com.example.demo.service.sec;

import com.example.demo.entity.form.SECEventForm;
import com.example.demo.entity.sec.SECEvent;

import java.util.List;

public interface SECEventService {

    List<SECEvent> findAll();

    SECEvent findById(Integer id);

    SECEvent save(SECEvent sec);

    SECEvent save(SECEventForm sec);

    SECEvent edit(SECEvent sec);

    SECEvent edit(SECEventForm sec);

    void delete(SECEvent sec);

    void delete(Integer id);
}
