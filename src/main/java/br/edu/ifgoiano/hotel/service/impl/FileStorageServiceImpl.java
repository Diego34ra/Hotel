package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.request.MessageResponseDTO;
import br.edu.ifgoiano.hotel.model.FileResponse;
import br.edu.ifgoiano.hotel.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getAbsolutePath() {
        String userDir = System.getProperty("user.dir");
        Path srcPath = Paths.get(userDir);
        return srcPath.toAbsolutePath().toString();
    }

    @Override
    public MessageResponseDTO saveFile(Long roomId, MultipartFile file) {
        try {
            Path uploadPath = Paths.get(getAbsolutePath() + uploadDir, roomId.toString());
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return MessageResponseDTO
                    .builder()
                    .code(200)
                    .status("Ok")
                    .message("Foto enviada com sucesso.")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileResponse downloadFile(Long roomId, String filename) {
        try {
            Path file = Paths.get(getAbsolutePath()+uploadDir).resolve(roomId.toString()).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                FileResponse fileResponse = new FileResponse(resource,contentType);
                return fileResponse;
            } else {
                throw new RuntimeException("File not found or not readable: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while downloading file: " + e.getMessage());
        }
    }

    @Override
    public List<FileResponse> downloadAllPhotos(Long roomId) {
        List<FileResponse> photos = new ArrayList<>();
        try {
            Path roomPath = Paths.get(getAbsolutePath()+uploadDir).resolve(roomId.toString());
            try (Stream<Path> filePaths = Files.list(roomPath)) {
                filePaths.forEach(file -> {
                    try {
                        Resource resource = new UrlResource(file.toUri());
                        if (resource.exists() || resource.isReadable()) {
                            String contentType = Files.probeContentType(file);
                            if (contentType == null) {
                                contentType = "application/octet-stream";
                            }
                            photos.add(new FileResponse(resource, contentType));
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing file: " + file.toString());
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving photos for room: " + roomId, e);
        }
        return photos;
    }
}
