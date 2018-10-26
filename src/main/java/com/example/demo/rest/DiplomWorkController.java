package com.example.demo.rest;

import com.example.demo.entity.DiplomWork;
import com.example.demo.service.DiplomWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/diplom-work")
public class DiplomWorkController {
    @Autowired
    private DiplomWorkService diplomWorkService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkList() {
        return diplomWorkService.getAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-all-by-leader-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkListByLeader(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findAllByLeader(idPerson);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-all-by-lector-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkListByLector(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findAllByLector(idPerson);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-by-student-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    DiplomWork diplomWorkByStudent(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findByStudent(idPerson);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/diplom-work-{id}", method = RequestMethod.GET)
    public @ResponseBody
    DiplomWork diplomWork(@PathVariable("id") Integer id) {
        return diplomWorkService.findById(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    DiplomWork save(@RequestBody DiplomWork diplomWork) {
        return diplomWorkService.save(diplomWork);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    DiplomWork edit(@RequestBody DiplomWork diplomWork) {
        return diplomWorkService.edit(diplomWork);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            diplomWorkService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
