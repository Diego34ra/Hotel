package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.MessageResponseDTO;
import br.edu.ifgoiano.hotel.model.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    MessageResponseDTO saveFile(Long roomId, MultipartFile file);

    FileResponse downloadFile(Long roomId, String filename);

    List<FileResponse> downloadAllPhotos(Long roomId);
}
