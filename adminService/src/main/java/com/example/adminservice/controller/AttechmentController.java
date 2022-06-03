package com.example.adminservice.controller;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.repository.AttechmentRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attechment")
public class AttechmentController {

    private final AttechmentRepositroy attechmentRepositroy;

    @PostMapping("/oneupload")
    public ResponseEntity uploadOne(MultipartHttpServletRequest request) throws IOException {

        MultipartFile one = request.getFile("One");
        Attachment attachment=null;
        if (one!=null && !one.isEmpty()){
            Attachment attachment1=new Attachment();
            attachment1.setContentType(one.getContentType());
            attachment1.setName(one.getName());
            attachment1.setSize(one.getSize());
            attachment1.setBytes(one.getBytes());
            attechmentRepositroy.save(attachment1);
        }
        return ResponseEntity.ok("Upload");
    }


    @PostMapping("/uploadMany")
    public ResponseEntity UploadMany(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            for (MultipartFile file : request.getFiles(fileNames.next())) {
                Attachment attachment=null;
                if (file!=null && !file.isEmpty()){

                    Attachment attachment1=new Attachment();
                    attachment1.setContentType(file.getContentType());
                    attachment1.setName(file.getName());
                    attachment1.setSize(file.getSize());
                    attachment1.setBytes(file.getBytes());
                    attechmentRepositroy.save(attachment1);
                }
            }

        }
        return ResponseEntity.ok("uploadmany");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> download(@PathVariable Long id){
        Optional<Attachment> byId = attechmentRepositroy.findById(id);
        Attachment attechment = byId.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attechment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"file")
                .body(attechment.getBytes());
    }


}
