package br.edu.ifgoiano.hotel.controller.dto.request;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO {
    private String status;
    private int code;
    private String message;
}
