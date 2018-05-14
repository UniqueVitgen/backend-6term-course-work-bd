package com.example.demo.rest;

import com.example.demo.entity.Group;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin(origins = {"http://localhost:4200"})
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/get-by-specialization/{id-specialization}", method = RequestMethod.GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<Group> getGroupsBySpecialization(@PathVariable("id-specialization") Integer id) {
        List<Group> groups = groupService.findAllBySpecialization(id);
        return groups;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Group> groupsList() {
        return groupService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/group-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Group group(@PathVariable("id") Integer id) {
        return groupService.get(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Group save(@RequestBody Group group) {
        group.setAmount_student(0);
        return groupService.save(group);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Group edit(@RequestBody Group group) {
        return groupService.Edit(group);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            groupService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
