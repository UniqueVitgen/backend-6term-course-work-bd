package com.example.demo.rest.sec;

import com.example.demo.entity.form.SECEventForm;
import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECEvent;
import com.example.demo.service.sec.SECEventService;
import com.example.demo.service.sec.SECService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/sec-event")
public class SECEventController {

    @Autowired
    private SECEventService secEventService;

    @Autowired
    private SECService secService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<SECEvent> findAll() {
        return secEventService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    SECEvent save(@RequestBody SECEventForm secEvent) {
        return secEventService.save(secEvent);
    }

    @RequestMapping(value = "/save-{idSec}", method = RequestMethod.POST)
    public @ResponseBody
    SECEvent saveBySec(@RequestBody SECEvent secEvent, @PathVariable("idSec") Integer idSec) {
        SEC sec = secService.findById(idSec);
        secEvent.setSec(sec);
        return secEventService.save(secEvent);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    SECEvent edit(@RequestBody SECEventForm secEvent) {
        return secEventService.edit(secEvent);
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            secEventService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
