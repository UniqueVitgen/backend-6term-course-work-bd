package com.example.demo.rest;

import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/status")
public class StatusContoller {
    @Autowired
    private StatusService statusService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Status> statusesList() {
        return statusService.getAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/status-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Status status(@PathVariable("id") Integer id) {
        return statusService.get(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Status save(@RequestBody Status status) {
        return statusService.save(status);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Status edit(@RequestBody Status status) {
        return statusService.edit(status);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            statusService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
