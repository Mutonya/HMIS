package com.mutonya.hmisapplication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("api/v1/")
@RestController
public class TestController {

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/doctor/sensitive-data")
    public ResponseEntity<?> getSensitiveData() {
        return ResponseEntity.ok("You have access");
    }
}
