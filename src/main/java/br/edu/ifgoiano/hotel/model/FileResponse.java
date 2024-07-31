package br.edu.ifgoiano.hotel.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private Resource resource;
    private String contentType;
}
