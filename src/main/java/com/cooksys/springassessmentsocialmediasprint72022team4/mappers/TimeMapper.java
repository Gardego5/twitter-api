package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.sql.Timestamp;
import java.time.Instant;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeMapper {

    default Long map(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    } 

    default Timestamp map(Long time) {
        return time == null ? null : Timestamp.from(Instant.ofEpochSecond(time));
    } 
}
