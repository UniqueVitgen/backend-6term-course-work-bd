package com.example.demo.rest;

import com.example.demo.entity.Lector;
import com.example.demo.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/lectors")
public class LectorController {

  @Autowired
  public LectorService lectorService;

  @CrossOrigin(origins = {"http://localhost:4200"})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public @ResponseBody
  List<Lector> lectorsList() {
    return lectorService.findAll();
  }
}
