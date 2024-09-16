package com.apiexamples.examples.service;

import com.apiexamples.examples.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    public RegistrationDto addRegistration(RegistrationDto registrationDto);

    public void deleteRecordById(long id);

   public RegistrationDto updateRecordById(long id, RegistrationDto registrationDto);



    //Implement a Method to get Details of All Employee's objects
    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir);

   public RegistrationDto getRegistrationById(long id);
}
