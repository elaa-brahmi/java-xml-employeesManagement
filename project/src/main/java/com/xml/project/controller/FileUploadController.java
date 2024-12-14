package com.xml.project.controller;

import com.xml.project.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private XMLService xmlService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            // Define the path for the existing file and the uploaded file
            String newFilePath = "C:\\pfaspringboot\\project\\" + file.getOriginalFilename();
            String existingFilePath = "C:\\pfaspringboot\\project\\tache.xml"; // Adjust the path as needed

            // Save the uploaded file to disk
            file.transferTo(new File(newFilePath));

            // Merge the new file with the existing file
            xmlService.mergeXMLFiles(newFilePath, existingFilePath);

            // Return success message
            return ResponseEntity.ok("File uploaded and merged successfully.");


        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing the files: " + e.getMessage());
        }
    }
}
