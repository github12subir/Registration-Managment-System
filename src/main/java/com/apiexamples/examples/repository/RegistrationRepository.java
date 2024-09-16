package com.apiexamples.examples.repository;

import com.apiexamples.examples.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
}