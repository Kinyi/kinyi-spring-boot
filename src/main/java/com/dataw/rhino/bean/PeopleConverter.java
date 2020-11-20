package com.dataw.rhino.bean;

import com.dataw.rhino.demo.People;
import com.dataw.rhino.demo.PeopleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2020-11-20
 */
@Mapper(componentModel = "spring")
public interface PeopleConverter {

    @Mappings({
            @Mapping(source = "address", target = "homeAddress"),
            @Mapping(source = "birth", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PeopleDTO domain2dto(People people);

    List<PeopleDTO> domain2dto(List<People> people);
}
