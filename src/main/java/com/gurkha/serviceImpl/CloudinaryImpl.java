package com.gurkha.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.gurkha.service.CloudinaryService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CloudinaryImpl implements  CloudinaryService {

    private  final Cloudinary cloudinary;
   

    @Override
    public Map upload(MultipartFile file, String folder) {
        Map<String, Object> params = new HashMap<>();
        params.put("folder", "uploads/" + folder);
        params.put("use_filename", true);
        params.put("unique_filename", false);
        params.put("resource_type", "auto");
        try {
            return cloudinary.uploader().upload(file.getBytes(), params);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

