package com.example.demoCrud;

import com.example.demoCrud.domain.Address;
import com.example.demoCrud.domain.Student;
import com.example.demoCrud.service.AddressService;
import com.example.demoCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoCrudApplication {

	StudentService ss;
	AddressService as;

	@Autowired
	DemoCrudApplication(StudentService ss, AddressService as) {
		this.ss = ss;
		this.as = as;
	}


	@PostConstruct
	public void addInMemoryData() {
		Address a1 = new Address("Charles Corner", "New York", 300110);
		Address a2 = new Address("YT Street", "Kingston", 123342);
		Address a3 = new Address("Sams", "Masachusettes", 213124);
        Address a4 = new Address("Sixth Street, 302", "London", 32412);

		as.save(a1);
		as.save(a2);
		as.save(a3);
        as.save(a4);

		Student s1 = new Student("ABC", "XYZ", 3.6, a1);
		Student s2 = new Student("MNO", "PQR", 4.2, a2);
		Student s3 = new Student("TUV", "WAV", 4.6, a3);

		ss.save(s1);
		ss.save(s2);
		ss.save(s3);
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoCrudApplication.class, args);
	}

}
