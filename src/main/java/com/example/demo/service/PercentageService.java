package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import com.example.demo.entity.form.PercentageForm;
import com.example.demo.entity.sec.SEC;

import java.util.List;

public interface PercentageService {

    List<Percentage> findAllBySEC(SEC sec);

    List<Percentage> findAllBySEC(Integer idSec);

    List<Percentage> findAll();

    Percentage save(Percentage percentage);

    Percentage save(PercentageForm percentageForm);

    Percentage edit(PercentageForm percentageForm);

    void delete(Percentage percentage);

    void delete(Integer id);

}
