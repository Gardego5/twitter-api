package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Credentials;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials dtoToEntity(CredentialsDto credentialsDto);

    CredentialsDto entityToDto(Credentials credentials);

    List<CredentialsDto> entitiesToDtos(List<Credentials> credentialsList);

}
