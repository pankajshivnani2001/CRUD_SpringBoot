package com.example.demoCrud.repository;

import com.example.demoCrud.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query(value = "SELECT * FROM address WHERE id NOT IN :usedAddressIds", nativeQuery = true)
    List<Address> findAllNotInList(List<Integer> usedAddressIds);
}
