package com.example.demo.service.sec;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECEvent;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.sec.SECRepository;
import com.example.demo.service.DiplomWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SECServiceImpl implements SECService {
    @Autowired
    private SECRepository secRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DiplomWorkService diplomWorkService;

    @Override
    public List<SEC> findAll() {
        return secRepository.findAll();
    }

    @Override
    public SEC findById(Integer id) {
        return secRepository.findById(id).get();
    }

    @Override
    public SEC findByDiplom(Integer diplomId) {
        DiplomWork diplomWork = diplomWorkService.findById(diplomId);
        SECEvent secEvent = diplomWork.getStudent().getSecEvent();
        if (secEvent != null) {
            return secEvent.getSec();
        } else {
            return null;
        }
    }

    @Override
    public SEC save(SEC sec) {
        sec = updateGroups(sec);
        return sec;
    }

    private SEC updateGroups(SEC sec) {
        if(sec.getId() != null) {
//            List<Group> previousGroups = groupRepository.findAllBySec(sec);

//            for (Group group : previousGroups) {
//                group.setSec(null);
//                groupRepository.save(group);
////            groupRepository.save(group);
//            }
//        }
//            List<Group> groups = sec.getGroups();
//            sec.setGroups(new ArrayList<>());
            sec = secRepository.save(sec);
//            for(Group group: groups) {
//                group.setSec(sec);
////            groupRepository.save(group);
            }
//            sec.setGroups(groups);
        return secRepository.save(sec);
    }

    @Override
    public SEC edit(SEC sec) {
        SEC editedSEC = secRepository.findById(sec.getId()).get();
        editedSEC.setDepartment(sec.getDepartment());
        editedSEC.setSpecializations(sec.getSpecializations());
        editedSEC.setGroups(sec.getGroups());
        editedSEC.setEndDate(sec.getEndDate());
        editedSEC.setNumber(sec.getNumber());
        editedSEC.setStartDate(sec.getStartDate());
        editedSEC.setUsers(sec.getUsers());
        return secRepository.save(editedSEC);
    }

    @Override
    public void delete(SEC sec) {
        secRepository.delete(sec);
    }

    @Override
    public void delete(Integer id) {
        secRepository.deleteById(id);
    }
}
