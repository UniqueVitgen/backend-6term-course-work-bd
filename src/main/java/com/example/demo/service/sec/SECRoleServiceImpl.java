package com.example.demo.service.sec;

import com.example.demo.entity.sec.SECRole;
import com.example.demo.repository.sec.SECRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SECRoleServiceImpl implements SECRoleService {
    @Autowired
    private SECRoleRepository secRoleRepository;

    @Override
    public List<SECRole> findAll() {
        return secRoleRepository.findAll();
    }

    @Override
    public SECRole findById(Integer id) {
        return secRoleRepository.findById(id).get();
    }

    @Override
    public SECRole save(SECRole secRole) {
        return secRoleRepository.save(secRole);
    }

    @Override
    public SECRole edit(SECRole secRole) {
        return secRoleRepository.save(secRole);
    }

    @Override
    public void delete(SECRole secRole) {
        secRoleRepository.delete(secRole);
    }

    @Override
    public void delete(Integer id) {
        secRoleRepository.deleteById(id);
    }
}
