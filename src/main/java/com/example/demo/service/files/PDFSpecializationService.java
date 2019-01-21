package com.example.demo.service.files;

import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;

import java.io.File;

public interface PDFSpecializationService {
    File writePdf(Specialization specialization);
}
