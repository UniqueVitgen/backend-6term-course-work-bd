package com.example.demo.service.sec;

import com.example.demo.entity.form.SECEventForm;
import com.example.demo.entity.sec.SECEvent;
import com.example.demo.repository.sec.SECEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SECEventServiceImpl implements SECEventService {
    @Autowired
    private SECEventRepository secRepository;

    @Override
    public List<SECEvent> findAll() {
        return secRepository.findAll();
    }

    @Override
    public SECEvent findById(Integer id) {
        return secRepository.findById(id).get();
    }

    @Override
    public SECEvent save(SECEvent sec) {
        return secRepository.save(sec);
    }

    @Override
    public SECEvent save(SECEventForm sec) {
        SECEvent secEvent = new SECEvent();
        secEvent.setId(sec.getId());
        secEvent.setAddress(sec.getAddress());
        secEvent.setSec(sec.getSec());
        secEvent.setDate(sec.getDate());
        secEvent.setEndDate(sec.getEndDate());
        return secRepository.save(secEvent);
    }

    @Override
    public SECEvent edit(SECEvent sec) {
        SECEvent secEvent = secRepository.findById(sec.getId()).get();
        secEvent.setGroups(sec.getGroups());
        secEvent.setDate(sec.getDate());
        secEvent.setAddress(sec.getAddress());
        secEvent.setEndDate(sec.getEndDate());
        return secRepository.save(secEvent);
    }

    @Override
    public SECEvent edit(SECEventForm sec) {
        SECEvent secEvent = secRepository.findById(sec.getId()).get();
        secEvent.setId(sec.getId());
        secEvent.setAddress(sec.getAddress());
        secEvent.setDate(sec.getDate());
        secEvent.setEndDate(sec.getEndDate());
        return secRepository.save(secEvent);
    }

    @Override
    public void delete(SECEvent sec) {
        secRepository.delete(sec);
    }

    @Override
    public void delete(Integer id) {
        secRepository.deleteById(id);
    }
}
