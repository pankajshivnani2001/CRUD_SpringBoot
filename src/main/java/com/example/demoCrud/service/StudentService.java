package com.example.demoCrud.service;

import com.example.demoCrud.domain.Student;
import com.example.demoCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository sr;

    public void save(Student s) {
        sr.save(s);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) sr.findAll();
    }

    public boolean existsById(Integer id) {
        return sr.existsById(id);
    }

    public  boolean existsByAddressId(Integer addressId) {
        Integer id = sr.findStudentByAddressId(addressId);

        return id == null ? false : true;
    }

    public Optional<Student> getById(Integer id) {
        return sr.findById(id);
    }

    public void deleteById(Integer id) {
        sr.deleteById(id);
    }

    public List<Integer> findAllUsedAddresses() {
        return sr.findAddressIdForAll();
    }
}
