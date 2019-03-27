package com.example.demo.rest;

import com.example.demo.entity.Percentage;
import com.example.demo.entity.form.PercentageForm;
import com.example.demo.service.PercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/percentage")
public class PercentageController {
    @Autowired
    private PercentageService percentageService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Percentage save(@RequestBody PercentageForm percentageForm) {
        return percentageService.save(percentageForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Percentage edit(@RequestBody PercentageForm percentageForm) {
        return percentageService.edit(percentageForm);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Percentage> findAll() {
        return percentageService.findAll();
    }

    @RequestMapping(value = "/get-by-sec/{id-sec}", method = RequestMethod.GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<Percentage> getPercentagesByDiplomWork(@PathVariable("id-sec") Integer id) {
        List<Percentage> percentages = percentageService.findAllBySEC(id);
        return percentages;
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            percentageService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
