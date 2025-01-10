package com.nipcts.ihirwe.photoz_clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PhotozController {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};
    @GetMapping("/")
    public String hello() {
        return "Hello world ";
    }
    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return db.values();
    }
    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //created the photo by creating the photo id using a mark annotation @RequestBody to change the photo into the Photo object
    // used the validation to prevent empty files
    @PostMapping("/photoz/")
        public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
          db.put(photo.getId(), photo);
          return photo;
        }
}
