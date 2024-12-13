package com.xml.project.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class XMLExportController {
    private static final String XML_STORAGE_DIR = "C:\\pfaspringboot\\project\\";

    @GetMapping("/export-xml")
    public ResponseEntity<Resource> exportXML(@RequestParam("filename") String filename) {
        try {
            // Create a File object for the requested file
            File file = new File(XML_STORAGE_DIR + filename);
            // Check if the file exists
            if (!file.exists()) {
                System.out.println("File not found at: " + file.getAbsolutePath());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);}
            FileSystemResource resource = new FileSystemResource(file);
            // Set the Content-Disposition header for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            // Return the file as the response
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_XML)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
