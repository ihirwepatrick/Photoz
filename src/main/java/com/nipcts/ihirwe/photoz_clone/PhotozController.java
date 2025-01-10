package com.nipcts.ihirwe.photoz_clone;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PhotozController {
    private final PhotozService photozService;
    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }
   
    @GetMapping("/")
    public String hello() {
        return "Hello world ";
    }
    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photozService.getAll();
    }
    @GetMapping("/photoz/{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id) {
        Photo photo = photozService.getById(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
        headers.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photozService.removeById(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //created the photo by creating the photo id using a mark annotation @RequestBody to change the photo into the Photo object
    // used the validation to prevent empty files
    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photozService.save(
            file.getBytes(), 
            file.getOriginalFilename(),
            file.getContentType()
        );
        System.out.println("Uploaded photo ID: " + photo.getId());
        return photo;
    }
}
