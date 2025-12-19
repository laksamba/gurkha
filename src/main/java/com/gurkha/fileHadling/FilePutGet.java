package com.gurkha.fileHadling;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// @Service
// public class FilePutGet {

//     @Value("${uploads.cv}")
//     private String cvPath;

//     @Value("${uploads.blog}")
//     private String blogPath;

//     // This is your base upload path from application.properties

//     public String[] saveFile(MultipartFile file, String dirType) {

//         if (file.isEmpty()) {
//             throw new RuntimeException("File is empty");
//         }

//         Path uploadPath=null;
//         try {
//             if("blog".equals(dirType))
//                 {
//              uploadPath = Paths.get(blogPath);
//             if (!Files.exists(uploadPath)) {
//                 Files.createDirectories(uploadPath); // creates the directory and parents if needed
//             }
//             }else if("cv".equals(dirType)){
//                   uploadPath = Paths.get(cvPath);
//             if (!Files.exists(uploadPath)) {
//                 Files.createDirectories(uploadPath); // creates the directory and parents if needed
//             }else{
//                 throw new IllegalArgumentException("Invalid directory type: " + dirType);
//             }

//             }

//             // 1. Create the upload directory if it doesn't exist




//             // 2. Create the file name
//             String originalFilename = file.getOriginalFilename();
//             String fileName = System.currentTimeMillis() + "_" + originalFilename; // to avoid name clashes

//             // 3. Create the full path where the file will be stored
//             Path filePath = uploadPath.resolve(fileName);

//             // 4. Save the file
//             file.transferTo(filePath.toFile());

//             // 5. Return filename and path as a String array
//             System.out.println("fileName=>"+fileName + " directory => "+ uploadPath+ "full path with name=> "+ filePath.toString());
//             return new String[]{fileName,uploadPath.toString(), filePath.toString()};
//         }catch (IOException e) {
//             throw new RuntimeException("Failed to save file");
//         }
//     }


// }

@Service
public class FilePutGet {

    @Value("${uploads.cv}")
    private String cvPath;

    @Value("${uploads.blog}")
    private String blogPath;

    public String[] saveFile(MultipartFile file, String dirType) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        Path uploadPath = null;
        
        // 1. Validate Directory Type First
        if ("blog".equals(dirType)) {
            uploadPath = Paths.get(blogPath);
        } else if ("cv".equals(dirType)) {
            uploadPath = Paths.get(cvPath);
        } else {
            // Throw exception ONLY if it's neither "blog" nor "cv"
            throw new IllegalArgumentException("Invalid directory type: " + dirType);
        }

        try {
            // 2. Create directory if it doesn't exist (Runs for both types)
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 3. Generate Unique Filename
            String originalFilename = file.getOriginalFilename();
            String fileName = System.currentTimeMillis() + "_" + originalFilename;

            // 4. Resolve Full Path and Save
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            System.out.println("Success! Path: " + filePath.toString());
            return new String[]{fileName, uploadPath.toString(), filePath.toString()};
            
        } catch (IOException e) {
            // Log the error so you can see it in Railway logs
            e.printStackTrace(); 
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        }
    }
}