package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Room;

import java.util.List;

public interface RoomService {
    Room create(Room quarto);
    List<Room> findAll();
    Room findById(Long id);
    void delete(Long id);
}
