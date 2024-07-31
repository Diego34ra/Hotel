package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceBadRequestException;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.*;
import br.edu.ifgoiano.hotel.repository.BookingRepository;
import br.edu.ifgoiano.hotel.repository.CheckInRepository;
import br.edu.ifgoiano.hotel.service.BookingService;
import br.edu.ifgoiano.hotel.service.HospitalityService;
import br.edu.ifgoiano.hotel.service.RoomService;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HospitalityService hospitalityService;

    @Autowired
    private MyModelMapper mapper;

    @Override
    public BookingOutputDTO create(BookingInputDTO booking) {
        var bookingCreate = mapper.mapTo(booking,Booking.class);

        User client = mapper.mapTo(userService.findById(bookingCreate.getClient().getId()), User.class);

        Room room = mapper.mapTo(roomService.findById(bookingCreate.getRoom().getId()), Room.class);

        if(!room.getAvailable())
            throw new ResourceBadRequestException("O quarto não está disponível para reserva");
        room.setAvailable(false);

        bookingCreate.setClient(client);
        bookingCreate.setRoom(room);
        bookingCreate.setTotalValue(bookingCreate.getSumTotalValue(room.getPrice()));
        bookingCreate.setBookingStatus(BookingStatus.getPadrao());
        return mapper.mapTo(bookingRepository.save(bookingCreate),BookingOutputDTO.class);
    }

    @Override
    public BookingOutputDTO addhospitality(Long bookingId, List<Long> hospitalityIds) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado todas as reservas informadas."));

        List<Hospitality> newRoomServices = hospitalityIds.stream()
                .map(id -> hospitalityService.findById(id))
                .toList();
        newRoomServices.forEach(roomService -> {
            if (booking.getHospitalities().stream().noneMatch(rs -> rs.getId().equals(roomService.getId()))) {
                booking.getHospitalities().add(roomService);
                booking.setTotalValue(booking.getTotalValue().add(roomService.getPrice()));
            }
        });
        return mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class);
    }

    @Override
    public BookingOutputDTO checkIn(Long bookingId, Long empolyeeId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));

        User employee = mapper.mapTo(userService.findById(empolyeeId), User.class);

        if(booking.getBookingStatus() == BookingStatus.CANCELED)
            throw new ResourceBadRequestException("Não é possível fazer checkIn de reserva cancelada.");

        if(employee.getRole() == UserRole.CLIENT)
            throw new ResourceBadRequestException("O id mencionado não é permitido fazer checkIn.");

        CheckIn checkIn = new CheckIn();
        checkIn.setDate(new Date());
        checkIn.setEmployee(employee);
        booking.setCheckIn(checkIn);
        return mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class);
    }

    @Override
    public BookingOutputDTO checkOut(Long bookingId, Long empolyeeId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));

        User employee = mapper.mapTo(userService.findById(empolyeeId), User.class);

        if(booking.getBookingStatus() == BookingStatus.CANCELED)
            throw new ResourceBadRequestException("Não é possível fazer checkOut de reserva cancelada.");

        if(employee.getRole() == UserRole.CLIENT)
            throw new ResourceBadRequestException("O id mencionado não é permitido fazer checkOut.");

        CheckOut checkOut = new CheckOut();
        checkOut.setDate(new Date());
        checkOut.setEmployee(employee);
        booking.setCheckOut(checkOut);
        booking.setBookingStatus(BookingStatus.FINISHED);
        booking.getRoom().setAvailable(true);
        return mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class);
    }

    @Override
    public List<BookingSimpleOutputDTO> findAll() {
        return mapper.toList(bookingRepository.findAll(), BookingSimpleOutputDTO.class);
    }

    @Override
    public BookingOutputDTO findById(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));
        return mapper.mapTo(booking,BookingOutputDTO.class);
    }

    @Override
    public BookingOutputDTO cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));
        booking.setBookingStatus(BookingStatus.CANCELED);
        booking.getRoom().setAvailable(true);
        return mapper.mapTo(booking,BookingOutputDTO.class);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

}
