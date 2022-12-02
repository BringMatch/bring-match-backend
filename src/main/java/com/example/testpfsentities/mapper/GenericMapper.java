package com.example.testpfsentities.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GenericMapper{
    private final ModelMapper modelMapper;
//    public <B,D> B toBo(D d){
//        return modelMapper.map(d,B);
//    }
}
