package com.nipcts.ihirwe.photoz_clone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class PhotozService {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> getAll() {
        return db.values();
    }

    public Photo getById(String id) {
        return db.get(id);
    }

    public Photo removeById(String id) {
        return db.remove(id);
    }

    public Photo save(byte [] data, String filename) {
        Photo photo = new Photo(UUID.randomUUID().toString(), filename);
        photo.setData(data);
        photo.setFileName(filename);
        db.put(photo.getId(), photo);
        return photo;
    }
} 