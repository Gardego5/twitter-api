package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Credentials;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials dtoToEntity(CredentialsDto credentialsDto);

    CredentialsDto entityToDto(Credentials credentials);

    List<CredentialsDto> entitiesToDtos(List<Credentials> credentialsList);

}
