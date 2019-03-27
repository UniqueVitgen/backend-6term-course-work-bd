package com.example.demo.rest.sec;

import com.example.demo.entity.Group;
import com.example.demo.entity.sec.SEC;
import com.example.demo.service.sec.SECService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/sec")
public class SECController {

    @Autowired
    private SECService secService;

    @RequestMapping(value = "/sec-{id}")
    public @ResponseBody
    SEC sec(@PathVariable("id") Integer id) {
        return secService.findById(id);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<SEC> findAll() {
        return secService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    SEC save(@RequestBody SEC sec) {
        return secService.save(sec);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    SEC edit(@RequestBody SEC sec) {
        return secService.edit(sec);
    }

    @GetMapping(value = "/find-by-diplom-{idDiplom}")
    public SEC findByDiplom(@PathVariable("idDiplom") Integer idDiplom) {
        return secService.findByDiplom(idDiplom);
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            secService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
