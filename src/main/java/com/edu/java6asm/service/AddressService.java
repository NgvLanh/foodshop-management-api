package com.edu.java6asm.service;

import com.edu.java6asm.model.Address;
import com.edu.java6asm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    public List<Address> getAddressByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}

