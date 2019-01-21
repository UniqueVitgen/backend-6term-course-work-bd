package com.example.demo.rest;

import com.example.demo.entity.Lector;
import com.example.demo.service.LectorService;
import com.example.demo.service.files.WordLectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/lectors")
public class LectorController {

  @Autowired
  public LectorService lectorService;
    @Autowired
    private WordLectorService wordLectorService;

  @CrossOrigin(origins = {"http://localhost:4200"})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public @ResponseBody
  List<Lector> lectorsList() {
    return lectorService.findAll();
  }

  @RequestMapping(value = "/findAllBySecUserIsNotIn",method = RequestMethod.GET)
  public
  List<Lector> findAllBySecUserIsNotIn(@RequestParam List<Integer> ids) {
    return lectorService.findAllBySecUserIdIsNotInOrSecUserNull(ids);
  }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Resource> getWord() {
      List<Lector> lectors = lectorService.findAll();
        File file = wordLectorService.writeWord(lectors);

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

  @CrossOrigin(origins = {"http://localhost:4200"})
  @RequestMapping(value = "/edit", method = RequestMethod.PUT)
  public Lector edit(@RequestBody Lector lector) {
    return  lectorService.edit(lector);
  }
}
