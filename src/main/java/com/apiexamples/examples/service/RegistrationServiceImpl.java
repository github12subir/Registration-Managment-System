package com.apiexamples.examples.service;

import com.apiexamples.examples.entity.Registration;
import com.apiexamples.examples.exception.ResourceNotFound;
import com.apiexamples.examples.payload.RegistrationDto;
import com.apiexamples.examples.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    //creating object of Repository using @Autowired Annotation to Apply Non-Static Method-Reference
    //
    //
    //
    @Autowired
    private RegistrationRepository registrationRepository;

    //creating this constructor for constructor base injection
    //public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
       // this.registrationRepository = registrationRepository;
    //}


    // Add the details of an Employee
    @Override
    public RegistrationDto addRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration saveEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(saveEntity);
        dto.setMessage("Registration Saved!!");
        return dto;
    }


    // Delete details of an Employee
    @Override
    public void deleteRecordById(long id) {
        registrationRepository.deleteById(id);
    }


    // Update details of an Employee By Using id
    @Override
    public RegistrationDto updateRecordById(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration = opReg.get();

        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        registration.setName(registrationDto.getName());
        registration.setSalary(registrationDto.getSalary());
        Registration saveEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(registration);
        dto.setMessage("Registration is updated!!");
        return dto;
    }



    //Show All Employee's Details
    @Override
    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Collect all Employee object references from database & store it into Entity Class
        //List<Registration> allReg = registrationRepository.findAll();


        //Using Ternary Operator set the sorting types(ASE OR DESC)
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        //set the page request type & store it into Pageable object
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
        //find all record using repository & store it into Page-Class, registration type
        Page<Registration> all = registrationRepository.findAll(pageable);
        //Convert Page object into Entity object
        List<Registration> allReg = all.getContent();
        //convert Entity object into Dto object
        List<RegistrationDto> allRegDto = allReg.stream().map(r -> mapToDto(r)).collect(Collectors.toList());



        //print all details on Console
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(all.isFirst());
        System.out.println(all.isLast());

        /*
        Convert the all object of Entity into Dto-Class using Lambda-Expression
        Called the mapToDto() method using Lambda-Expression under map()
        List<RegistrationDto> allRegDto = allReg.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        Convert the all object of Entity into Dto-Class using Method-Reference
        Called the mapToDto() method using Method-Reference under map()
        Here, Static Method-Reference used by using RegistrationServiceImpl Class
        List<RegistrationDto> allRegDto = allReg.stream().map(RegistrationServiceImpl::mapToDto).collect(Collectors.toList());
        Here, Non-Static Method Reference used by using reference-Variable(r) of RegistrationServiceImpl Class
         RegistrationServiceImpl r= new RegistrationServiceImpl();//Stored the obj address into r
        List<RegistrationDto> allRegDto = allReg.stream().map(r::mapToDto).collect(Collectors.toList());
        return the all Employees details of Dto-Class
        */

        return allRegDto;
    }


    //Show an Employee Details by id
    @Override
    public RegistrationDto getRegistrationById(long id) {
        //orElseThrow method get the exception message if record is not found
        //or if record is found then findAll method get the record & store it into registration
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Registration Not Found with id = "+id)
        );

        RegistrationDto dto = mapToDto(registration);
        return dto;
    }


    //Create a Method to Convert the object-Address of Dto-Class into  Entity-Class
    Registration mapToEntity(RegistrationDto dto){
        Registration entity= new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        entity.setSalary(dto.getSalary());
        return entity;
    }

    //Create a Method to Convert the object-Address of Entity-Class into Dto-Class
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto= new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        dto.setSalary(registration.getSalary());
        return dto;
    }
}
