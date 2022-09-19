package com.example.demoCrud.repository;

import com.example.demoCrud.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT id FROM student WHERE address_id = :id",nativeQuery = true)
    Integer findStudentByAddressId(Integer id);

    @Query(value = "SELECT address_id FROM student", nativeQuery = true)
    List<Integer> findAddressIdForAll();
}
