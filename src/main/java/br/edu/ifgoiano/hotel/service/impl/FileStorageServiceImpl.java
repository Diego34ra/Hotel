package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.request.MessageResponseDTO;
import br.edu.ifgoiano.hotel.model.FileResponse;
import br.edu.ifgoiano.hotel.model.FileResponseDownload;
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
    public FileResponse saveFile(Long roomId, MultipartFile file) {
        try {
            Path uploadPath = Paths.get(getAbsolutePath() + uploadDir, roomId.toString());
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String originalFilename = file.getOriginalFilename();
            String contentType = Files.probeContentType(filePath);
            long sizeInBytes = Files.size(filePath);
            String size = formatSize(sizeInBytes);
            String downloadUri = "/api/v1/hotel/rooms/" + roomId + "/download-photo/"+originalFilename;

            return new FileResponse(originalFilename,contentType,size,downloadUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileResponseDownload downloadFile(Long roomId, String filename) {
        try {
            Path file = Paths.get(getAbsolutePath()+uploadDir).resolve(roomId.toString()).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return new FileResponseDownload(resource,contentType);
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
                            long sizeInBytes = Files.size(file);
                            String size = formatSize(sizeInBytes);
                            String originalFilename = resource.getFilename();
                            String downloadUri = "/api/v1/hotel/rooms/" + roomId + "/download-photo/"+originalFilename;
                            photos.add(new FileResponse(originalFilename,contentType,size,downloadUri));
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

    public static String formatSize(long sizeInBytes) {
        double size = sizeInBytes;
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }
        return String.format("%.2f %s", size, units[unitIndex]);
    }
}
