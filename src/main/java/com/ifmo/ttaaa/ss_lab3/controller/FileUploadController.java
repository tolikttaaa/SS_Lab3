package com.ifmo.ttaaa.ss_lab3.controller;


import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;
import com.ifmo.ttaaa.ss_lab3.message.ScriptModeStartAnswer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {
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