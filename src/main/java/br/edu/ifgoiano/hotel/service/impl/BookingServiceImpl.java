package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.BookingController;
import br.edu.ifgoiano.hotel.controller.RoomController;
import br.edu.ifgoiano.hotel.controller.UserController;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceBadRequestException;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.*;
import br.edu.ifgoiano.hotel.repository.BookingRepository;
import br.edu.ifgoiano.hotel.repository.CheckInRepository;
import br.edu.ifgoiano.hotel.service.BookingService;
import br.edu.ifgoiano.hotel.service.HospitalityService;
import br.edu.ifgoiano.hotel.service.RoomService;
import br.edu.ifgoiano.hotel.service.UserService;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        Booking bookingCreate = mapper.mapTo(booking,Booking.class);
        bookingCreate.setCheckIn(null);
        bookingCreate.setCheckOut(null);
        bookingCreate.setCheckInDatePlanned(booking.getCheckInDatePlanned());
        bookingCreate.setCheckOutDatePlanned(booking.getCheckOutDatePlanned());

        User client = mapper.mapTo(userService.findById(bookingCreate.getClient().getId()), User.class);

        Room room = roomService.findById(bookingCreate.getRoom().getId());

        if(!checkAvailability(room.getId(),bookingCreate.getCheckInDatePlanned(), bookingCreate.getCheckOutDatePlanned()))
            throw new ResourceBadRequestException("O quarto não está disponível para reserva");
        room.setAvailable(false);

        bookingCreate.setClient(client);
        bookingCreate.setRoom(room);
        bookingCreate.setTotalValue(bookingCreate.getSumTotalValue(room.getPrice()));
        bookingCreate.setBookingStatus(BookingStatus.getPadrao());

        BookingOutputDTO bookingOutputDTO = mapper.mapTo(bookingRepository.save(bookingCreate),BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(bookingCreate.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));

        bookingOutputDTO.getRoom().setKey(room.getId());
        return bookingOutputDTO;
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
        return mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(booking.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));
    }

    @Override
    public BookingOutputDTO checkIn(Long bookingId, Long empolyeeId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));

        User employee = mapper.mapTo(userService.findById(empolyeeId), User.class);

        if(booking.getBookingStatus() == BookingStatus.CANCELED)
            throw new ResourceBadRequestException("Não é possível fazer checkIn de reserva cancelada.");

        if(employee.getRole() == UserRole.CLIENT)
            throw new ResourceBadRequestException("O id mencionado não tem permição para fazer checkIn.");

        CheckIn checkIn = new CheckIn();
        checkIn.setDate(new Date());
        checkIn.setEmployee(employee);
        booking.setCheckIn(checkIn);

        BookingOutputDTO bookingOutputDTO = mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(booking.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));

        bookingOutputDTO.getCheckIn().getEmployee()
                .add(linkTo(methodOn(UserController.class)
                        .findById(empolyeeId))
                        .withSelfRel());
        return bookingOutputDTO;
    }

    @Override
    public BookingOutputDTO checkOut(Long bookingId, Long empolyeeId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));

        User employee = mapper.mapTo(userService.findById(empolyeeId), User.class);

        if(booking.getBookingStatus() == BookingStatus.CANCELED)
            throw new ResourceBadRequestException("Não é possível fazer checkOut de reserva cancelada.");

        if(employee.getRole() == UserRole.CLIENT)
            throw new ResourceBadRequestException("O id mencionado não tem permição para fazer checkOut.");

        CheckOut checkOut = new CheckOut();
        checkOut.setDate(new Date());
        checkOut.setEmployee(employee);
        booking.setCheckOut(checkOut);
        booking.setBookingStatus(BookingStatus.FINISHED);
        booking.getRoom().setAvailable(true);

        BookingOutputDTO bookingOutputDTO = mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(booking.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));

        bookingOutputDTO.getCheckOut().getEmployee()
                .add(linkTo(methodOn(UserController.class)
                        .findById(empolyeeId))
                        .withSelfRel());
        return bookingOutputDTO;
    }

    @Override
    public List<BookingSimpleOutputDTO> findAll() {
        List<BookingSimpleOutputDTO> bookingDTO = mapper
                .toList(bookingRepository.findAll(), BookingSimpleOutputDTO.class);
        return bookingDTO.stream()
                .map(outputDTO -> outputDTO.add(linkTo(methodOn(BookingController.class)
                        .findById(outputDTO.getKey()))
                        .withSelfRel())).toList();
    }

    @Override
    public BookingOutputDTO findById(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));
        var roomNoCommentDTO = roomService.findByIdWithoutComment(booking.getRoom().getId());
        BookingOutputDTO bookingOutputDTO = mapper.mapTo(booking,BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(booking.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));
        bookingOutputDTO.setRoom(roomNoCommentDTO);

        if(bookingOutputDTO.getCheckIn() != null){
            bookingOutputDTO.getCheckIn().getEmployee()
                    .add(linkTo(methodOn(UserController.class)
                            .findById(bookingOutputDTO.getCheckIn().getEmployee().getKey()))
                            .withSelfRel());
        }

        if(bookingOutputDTO.getCheckOut() != null){
            bookingOutputDTO.getCheckOut().getEmployee()
                    .add(linkTo(methodOn(UserController.class)
                            .findById(bookingOutputDTO.getCheckOut().getEmployee().getKey()))
                            .withSelfRel());
        }

        return bookingOutputDTO;
    }

    @Override
    public BookingOutputDTO cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma reserva com esse id."));
        booking.setBookingStatus(BookingStatus.CANCELED);
        booking.getRoom().setAvailable(true);
        return mapper.mapTo(bookingRepository.save(booking),BookingOutputDTO.class)
                .add(linkTo(methodOn(BookingController.class).findById(booking.getId())).withSelfRel())
                .add(linkTo(methodOn(RoomController.class).findById(booking.getRoom().getId())).withRel("room"));
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    private boolean checkAvailability(Long roomId, Date newBookingCheckInDate, Date newBookingCheckOutDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        boolean available = true;

        List<Booking> bookings = bookingRepository.findAllByRoomId(roomId);

        for (Booking booking : bookings) {
            Date existingCheckInDate = booking.getCheckInDatePlanned();
            Date existingCheckOutDate = booking.getCheckOutDatePlanned();

            if ((newBookingCheckInDate.before(existingCheckOutDate) || newBookingCheckInDate.equals(existingCheckOutDate)) &&
                    (newBookingCheckOutDate.after(existingCheckInDate) || newBookingCheckOutDate.equals(existingCheckInDate))) {
                available = false;
                break;
            }
        }

        return available;
    }

}
