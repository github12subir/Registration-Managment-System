package com.apiexamples.examples.controller;

import com.apiexamples.examples.payload.RegistrationDto;
import com.apiexamples.examples.service.RegistrationService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    //Creating Object using Constructor Base-Injection
    private RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    //Create Registration
    //http://localhost:8080/api/v1/registration
        @PostMapping
        public ResponseEntity<?> getRegistration(
                @Valid @RequestBody RegistrationDto registrationDto,
                BindingResult result
                ){
            if (result.hasErrors()){
                return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            RegistrationDto regDto = registrationService.addRegistration(registrationDto);
            return new ResponseEntity<>(regDto, HttpStatus.CREATED);
        }



    //Delete Registration
    //http://localhost:8080/api/v1/registration?id=1,2,3 etc
    @DeleteMapping
    public ResponseEntity<String>deleteRecord(@RequestParam long id){
        registrationService.deleteRecordById(id);
        return new ResponseEntity<>("Record is Deleted!!",HttpStatus.OK);
    }


    //Edit Registration
    //http://localhost:8080/api/v1/registration?id=1,2,3 etc
    @PutMapping
    public ResponseEntity<RegistrationDto>updateRecord(
            @RequestParam long id,@RequestBody RegistrationDto registrationDto){
        RegistrationDto dto=registrationService.updateRecordById(id,registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Show All the Registration
    //http://localhost:8080/api/v1/registration
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(
         @RequestParam(name="pageNo",defaultValue ="0",required = false) int pageNo,
         @RequestParam(name="pageSize",defaultValue ="5",required = false) int pageSize,
         @RequestParam(name="sortBy",defaultValue ="name",required = false) String sortBy,
         @RequestParam(name="sortDir",defaultValue ="ASC",required = false) String sortDir
    ){
        List<RegistrationDto> allRegDtos = registrationService.getAllRegistration(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allRegDtos,HttpStatus.OK);
    }


    //Show a Registration By id
    @GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @RequestParam long id){

        RegistrationDto byId = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

}
