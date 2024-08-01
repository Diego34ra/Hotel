package br.edu.ifgoiano.hotel.controller.dto.mapper;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyModelMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @PostConstruct
    public void setup() {
        // Definição de mapeamentos básicos
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
