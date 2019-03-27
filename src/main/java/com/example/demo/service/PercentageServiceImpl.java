package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import com.example.demo.entity.form.PercentageForm;
import com.example.demo.entity.sec.SEC;
import com.example.demo.repository.PercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PercentageServiceImpl implements PercentageService {
    @Autowired
    private PercentageRepository percentageRepository;

    @Override
    public List<Percentage> findAllBySEC(SEC sec){
        return percentageRepository.findAllBySec(sec);
    }

    @Override
    public List<Percentage> findAllBySEC(Integer idSec) {
        return percentageRepository.findAllBySecId(idSec);
    }

    @Override
    public List<Percentage> findAll() {
        return percentageRepository.findAll();
    }

    @Override
    public Percentage save(Percentage percentage) {
        return percentageRepository.save(percentage);
    }

    @Override
    public Percentage save(PercentageForm percentageForm) {
        Percentage percentage = new Percentage();
        percentage.setId(percentageForm.getId());
        percentage.setComment(percentageForm.getComment());
        percentage.setName(percentageForm.getName());
        percentage.setStartDate(percentageForm.getStartDate());
        percentage.setEndDate(percentageForm.getEndDate());
        percentage.setSec(percentageForm.getSec());
        percentage.setPlanPercent(percentageForm.getPlanPercent());
        percentage.setFactPercent(percentageForm.getFactPercent());
        return percentageRepository.save(percentage);
    }

    @Override
    public Percentage edit(PercentageForm percentageForm) {
        Percentage percentage = percentageRepository.findById(percentageForm.getId()).get();
        percentage.setId(percentageForm.getId());
        percentage.setComment(percentageForm.getComment());
        percentage.setName(percentageForm.getName());
        percentage.setStartDate(percentageForm.getStartDate());
        percentage.setEndDate(percentageForm.getEndDate());
        percentage.setPlanPercent(percentageForm.getPlanPercent());
        percentage.setFactPercent(percentageForm.getFactPercent());
        percentage.setSec(percentageForm.getSec());
        return percentageRepository.save(percentage);
    }

    @Override
    public void delete(Percentage percentage) {
        percentageRepository.delete(percentage);
    }

    @Override
    public void delete(Integer id) {
        percentageRepository.deleteById(id);
    }
}
