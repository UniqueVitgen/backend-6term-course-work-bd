package com.example.demo.service.files;

import com.example.demo.entity.Lector;

import java.io.File;
import java.util.List;

public interface WordLectorService {
    File writeWord(List<Lector> lectors);
}
