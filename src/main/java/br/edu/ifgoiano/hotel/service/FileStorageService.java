package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.MessageResponseDTO;
import br.edu.ifgoiano.hotel.model.FileResponse;
import br.edu.ifgoiano.hotel.model.FileResponseDownload;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    FileResponse saveFile(Long roomId, MultipartFile file);

    FileResponseDownload downloadFile(Long roomId, String filename);

    List<FileResponse> downloadAllPhotos(Long roomId);
}
