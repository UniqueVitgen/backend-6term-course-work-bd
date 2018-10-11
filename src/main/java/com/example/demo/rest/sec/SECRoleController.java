package com.example.demo.rest.sec;

import com.example.demo.entity.sec.SECRole;
import com.example.demo.service.sec.SECRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/sec-role")
public class SECRoleController {
    @Autowired
    private SECRoleService secRoleService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<SECRole> findAll() {
        return secRoleService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    SECRole save(@RequestBody SECRole secRole) {
        return secRoleService.save(secRole);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    SECRole edit(@RequestBody SECRole secRole) {
        return secRoleService.edit(secRole);
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            secRoleService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
