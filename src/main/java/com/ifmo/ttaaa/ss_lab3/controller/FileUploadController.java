package com.ifmo.ttaaa.ss_lab3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("clientId") String id,
                                                 @RequestParam("clientPassword") String password,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded-" + id)));
                stream.write(bytes);
                stream.close();
                return "Successfully upload " + name + " into " + name + "-uploaded-" + id + " !";
            } catch (Exception e) {
                return "Upload " + name + " failed => " + e.getMessage();
            }
        } else {
            return "Failed to upload file " + name + ", because it empty.";
        }
    }

}