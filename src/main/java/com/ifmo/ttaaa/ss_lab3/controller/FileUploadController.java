package com.ifmo.ttaaa.ss_lab3.controller;


import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;
import com.ifmo.ttaaa.ss_lab3.exception.DirNotFoundException;
import com.ifmo.ttaaa.ss_lab3.message.ScriptModeStartAnswer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileUploadController {
    @PostMapping(value="/download")
    public ResponseEntity<InputStreamResource> handleFileDownload(@RequestParam("name") String name,
                                                                  @RequestParam("id") int id,
                                                                  @RequestParam("password") String password) {
        try {
            File newFile = new File(name);

            try {
                ScriptMode.cdCommand(id, password, name);
                ScriptMode.cdCommand(id, password, "..");
                return ResponseEntity.noContent().build();
            } catch (DirNotFoundException e) {
                try {
                    ScriptMode.cpCommand(id, password, name, newFile.getAbsoluteFile().getParent());
                } catch (DirNotFoundException ex) {
                    return ResponseEntity.noContent().build();
                }
                InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + newFile.getName())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .contentLength(newFile.length())
                        .body(resource);
            }
        } catch(Throwable e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value="/upload")
    @ResponseBody
    public ScriptModeStartAnswer handleFileUpload(@RequestParam("name") String name,
                                                  @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filepath = name + "-uploaded-" + ScriptMode.getCurNum();
                File outputFile = new File(filepath);

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(outputFile));
                stream.write(bytes);
                stream.close();
                ScriptModeStartAnswer response = ScriptModeStartAnswer.scriptModeStartAnswerByPath(outputFile.getAbsolutePath());
                response.concatResponseBefore("Successfully upload " + name + " into " + filepath + " !");
                return response;
            } catch (Exception e) {
                return ScriptModeStartAnswer.scriptModeStartAnswerError("Upload " + name + " failed => " + e.getMessage());
            }
        } else {
            return ScriptModeStartAnswer.scriptModeStartAnswerError("Failed to upload file " + name + ", because it empty.");
        }
    }
}