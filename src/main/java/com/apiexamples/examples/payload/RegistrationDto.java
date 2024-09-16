package com.apiexamples.examples.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private long id;
    @Size(min=3,max = 50, message = "Should be more than 3 and less than 50")
    private String name;
    @Email(message = "Invalid Email Format")
    private String email;
    @Size(min = 10, max = 15, message = "Number should be At least 10 digits")
    private String mobile;
    private float salary;
    private String message;
}
