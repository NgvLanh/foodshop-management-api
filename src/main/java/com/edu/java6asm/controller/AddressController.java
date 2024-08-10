package com.edu.java6asm.controller;

import com.edu.java6asm.model.Address;
import com.edu.java6asm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody Address address) {
        var createdAddress = addressService.createAddress(address);
        return ResponseEntity.ok(createdAddress);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable Long userId) {
        var Addresses = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(Addresses);
    }
}
