package com.nipcts.ihirwe.photoz_clone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PhotozService {
    private static final Logger logger = LoggerFactory.getLogger(PhotozService.class);
    
    private Map<String, Photo> db = new HashMap<>();

    public Collection<Photo> getAll() {
        return db.values();
    }

    public Photo getById(String id) {
        logger.info("Fetching photo with ID: {}", id);
        Photo photo = db.get(id);
        if (photo == null) {
            logger.warn("Photo not found with ID: {}", id);
        }
        return photo;
    }

    public Photo removeById(String id) {
        return db.remove(id);
    }

    public Photo save(byte[] data, String filename, String contentType) {
        Photo photo = new Photo(UUID.randomUUID().toString(), filename);
        photo.setData(data);
        photo.setFileName(filename);
        photo.setContentType(contentType);
        db.put(photo.getId(), photo);
        logger.info("Saved photo with ID: {}", photo.getId());
        return photo;
    }
} 