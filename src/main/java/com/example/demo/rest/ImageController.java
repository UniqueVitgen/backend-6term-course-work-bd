package com.example.demo.rest;

import com.example.demo.entity.ImageModel;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/get-by-filename", method = RequestMethod.GET)
    public @ResponseBody
    ImageModel getByFilename(String filename) {
        return imageService.findByFilename(filename);
    }
}
