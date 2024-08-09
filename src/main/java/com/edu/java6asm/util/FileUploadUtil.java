package com.edu.java6asm.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadUtil {
    // Thay đổi đường dẫn nếu cần
    public static final String UPLOADED_FOLDER = "D:/FPT Polytechnic/Java 6/Assignment/restaurant-management/public/assets/images/";

    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        // Tạo thư mục nếu chưa tồn tại
        File directory = new File(UPLOADED_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs(); // Dùng mkdirs() để tạo tất cả các thư mục cần thiết
        }

        // Lưu tệp
        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Files.write(path, multipartFile.getBytes());

        return path.toString(); // Trả về đường dẫn tuyệt đối
    }
}
