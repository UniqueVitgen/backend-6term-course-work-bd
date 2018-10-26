package com.example.demo.entity.file;

import com.example.demo.entity.ImageModel;
import com.sun.scenario.effect.ImageData;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {

    private byte[] fileContent;

    private String fileName;

    private String contentType;

    private File file;

    private String destPath = System.getProperty("java.io.tmpdir");

    private FileOutputStream fileOutputStream;
    public CustomMultipartFile(ImageModel imageModel) {
        fileName = imageModel.getFilename();
        contentType = imageModel.getContentType();
        fileContent = imageModel.getContent();

    }
    @Override
    public String getName() {
        return "file";
    }

    @Nullable
    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Nullable
    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        fileOutputStream = new FileOutputStream(destPath);
        fileOutputStream.write(fileContent);

    }
}
