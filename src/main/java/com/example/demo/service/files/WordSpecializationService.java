package com.example.demo.service.files;

import com.example.demo.entity.Specialization;

import java.io.File;

public interface WordSpecializationService {
    File writeWord(Specialization specialization);
}
