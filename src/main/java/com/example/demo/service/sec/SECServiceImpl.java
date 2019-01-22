package com.example.demo.service.sec;

import com.example.demo.entity.Group;
import com.example.demo.entity.sec.SEC;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.sec.SECRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SECServiceImpl implements SECService {
    @Autowired
    private SECRepository secRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<SEC> findAll() {
        return secRepository.findAll();
    }

    @Override
    public SEC findById(Integer id) {
        return secRepository.findById(id).get();
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
        sec = updateGroups(sec);
        return sec;
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
