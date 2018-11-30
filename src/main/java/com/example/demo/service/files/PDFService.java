package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;

public interface PDFService {
    File writeDiplomWork(DiplomWork diplomWork);
}
