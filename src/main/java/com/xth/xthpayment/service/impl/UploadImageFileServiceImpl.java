package com.xth.xthpayment.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.xth.xthpayment.exception.AppException;
import com.xth.xthpayment.exception.ErrorCode;
import com.xth.xthpayment.service.UploadImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadImageFileServiceImpl implements UploadImageFileService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImageFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new AppException(ErrorCode.FILE_REQUIRED);
        }

        String publicValue = generatePublicValue(file.getOriginalFilename());
        log.info("publicValue={}", publicValue);

        String typeFile = getFileName(file.getOriginalFilename())[1];
        log.info("typeFile={}", typeFile);

        File fileUpload = convert(file, publicValue, typeFile);

        Map upload = cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", publicValue));
        String filePath = cloudinary.url().generate(String.format("%s.%s", publicValue, typeFile));

        cleanDisk(fileUpload);

        return filePath;
    }

    private void cleanDisk(File file) {
        Path filePath = file.toPath();
        try {
            log.info("filePath is: {}", filePath);
            Files.delete(filePath);

        } catch (IOException e) {

            log.error(e.getMessage());
        }

    }

    public File convert(MultipartFile file, String publicValue, String typeFile) throws IOException {
        File convFile = new File(String.format("%s.%s", publicValue, typeFile));
        try (InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }

    /**
     * tạo ra tên file dạng UUID_fileName
     */
    public String generatePublicValue(String originalFileName) {
        String fileName = getFileName(originalFileName)[0];
        return String.format("%s_%s", UUID.randomUUID().toString(), fileName);
    }

    // lấy tên file bỏ phần type file
    public String[] getFileName(String originalFileName) {
        return originalFileName.split("\\.");
    }
}
