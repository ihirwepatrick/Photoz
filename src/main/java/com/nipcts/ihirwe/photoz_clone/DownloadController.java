package com.nipcts.ihirwe.photoz_clone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class DownloadController {
    @Autowired
    private PhotozService photozService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Photo photo = photozService.getById(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
            .filename(photo.getFileName())
            .build();
        headers.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
} 