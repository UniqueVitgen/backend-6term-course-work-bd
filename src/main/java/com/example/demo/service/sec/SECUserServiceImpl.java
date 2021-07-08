package com.example.demo.service.sec;

import com.example.demo.entity.sec.SECUser;
import com.example.demo.repository.sec.SECUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SECUserServiceImpl implements SECUserService {
    @Autowired
    private SECUserRepository secUserRepository;

    @Override
    public List<SECUser> findAll() {
        return secUserRepository.findAll();
    }

    @Override
    public SECUser findById(Integer id) {
        return secUserRepository.findById(id).get();
    }

    @Override
    public SECUser save(SECUser secUser) {
        return secUserRepository.save(secUser);
    }

    @Override
    public SECUser edit(SECUser secUser) {
        return secUserRepository.save(secUser);
    }

    @Override
    public void delete(SECUser secUser) {
        secUserRepository.delete(secUser);
    }

    @Override
    public void delete(Integer id) {
        secUserRepository.deleteById(id);
    }
}
