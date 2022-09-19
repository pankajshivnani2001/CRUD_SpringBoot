package com.example.demoCrud.service;

import com.example.demoCrud.domain.Address;
import com.example.demoCrud.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository ar;

    public void save(Address address) {
        ar.save(address);
    }

    public List<Address> getAllAddresses() {
        return (List<Address>) ar.findAll();
    }

    public Optional<Address> getAddressWithId(Integer id) {
        return ar.findById(id);
    }

    public void deleteAddress(Integer id) {
        ar.deleteById(id);
    }

    public List<Address> findAllUnusedAddresses(List<Integer> usedAddresses) {
        return ar.findAllNotInList(usedAddresses);
    }


}
