package br.edu.ifgoiano.hotel.controller.dto.mapper;

import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.ConvenienceOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.HospitalityOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.*;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyModelMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @PostConstruct
    public void setup() {
        MODEL_MAPPER.addMappings(new PropertyMap<User, UserSimpleOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<User, UserDetailOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Booking, BookingSimpleOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Booking, BookingOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Room, RoomNoCommentOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Room, RoomOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Comment, CommentOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Convenience, ConvenienceOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });

        MODEL_MAPPER.addMappings(new PropertyMap<Hospitality, HospitalityOutputDTO>() {
            @Override
            protected void configure() {
                map().setKey(source.getId());
            }
        });
    }

    public <S, D> D mapTo(S source, Class<D> destClass) {
        return MODEL_MAPPER.map(source, destClass);
    }

    public <D, T> List<D> toList(List<T> entityList, Class<D> outClass) {
        return entityList.stream()
                .map(entity -> MODEL_MAPPER.map(entity, outClass))
                .collect(Collectors.toList());
    }
}
