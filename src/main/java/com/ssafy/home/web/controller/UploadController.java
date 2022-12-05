package com.ssafy.home.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("/upload")
public class UploadController {

    @Autowired
    private ServletContext servletContext;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String realPath = servletContext.getRealPath("/WEB-INF/upload/webcam");

        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String saveFolder = realPath + File.separator + today;

        File folder = new File(saveFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String originalFileName = file.getOriginalFilename();
        String saveFileName = System.nanoTime()
                + originalFileName.substring(originalFileName.lastIndexOf('.'));
        try {
            file.transferTo(new File(folder, saveFileName));
            return "upload ok!!!";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "upload fail!!!";
        } catch (IOException e) {
            e.printStackTrace();
            return "upload fail!!!";
        }
    }
}
