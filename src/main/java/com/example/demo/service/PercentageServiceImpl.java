package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import com.example.demo.entity.form.PercentageForm;
import com.example.demo.repository.PercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PercentageServiceImpl implements PercentageService {
    @Autowired
    private PercentageRepository percentageRepository;

    @Override
    public List<Percentage> findAllByDiplomWork(DiplomWork diplomWork) {
        return percentageRepository.findAllByDiplomWork(diplomWork);
    }

    @Override
    public List<Percentage> findAllByDiplomWork(Integer id) {
        return percentageRepository.findAllByDiplomWorkId(id);
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
        percentage.setDiplomWork(percentageForm.getDiplomWork());
        percentage.setPercent(percentageForm.getPercent());
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
        percentage.setPercent(percentageForm.getPercent());
        //percentage.setDiplomWork(percentageForm.getDiplomWork());
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
