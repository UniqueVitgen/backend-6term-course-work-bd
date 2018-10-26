package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import com.example.demo.entity.form.PercentageForm;

import java.util.List;

public interface PercentageService {

    List<Percentage> findAllByDiplomWork(DiplomWork diplomWork);

    List<Percentage> findAllByDiplomWork(Integer id);

    List<Percentage> findAll();

    Percentage save(Percentage percentage);

    Percentage save(PercentageForm percentageForm);

    Percentage edit(PercentageForm percentageForm);

    void delete(Percentage percentage);

    void delete(Integer id);

}
