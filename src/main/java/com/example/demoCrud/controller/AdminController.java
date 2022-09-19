package com.example.demoCrud.controller;

import com.example.demoCrud.domain.Address;
import com.example.demoCrud.domain.Student;
import com.example.demoCrud.repository.StudentRepository;
import com.example.demoCrud.service.AddressService;
import com.example.demoCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StudentService ss;

    @Autowired
    AddressService as;

    @RequestMapping("/students")
    public String readStudents(Model model) {
        List<Student> students = ss.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }



    @RequestMapping("/address")
    public String readAddress(Model model) {
        List<Address> addresses = as.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "address";
    }

    @RequestMapping("/address/{id}")
    public String readAddressWithId(@PathVariable(value = "id") Integer id, Model model) {
        Address address = as.getAddressWithId(id).get();
        model.addAttribute("address", address);
        return "address";
    }

    @RequestMapping("/address/add")
    public String addAddress(Model model) {
        model.addAttribute("address", new Address());
        return "addressForm";
    }

    @RequestMapping(value="/address/saveAddress", method = RequestMethod.POST)
    public String saveAddress(Address address, Model model) {
        as.save(address);
        model.addAttribute("updatedMsg", "Address Updated Successfully!");
        List<Address> addresses = as.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "address";
    }

    @RequestMapping("address/update/{id}")
    public String updateAddress(@PathVariable Integer id, Model model) {
        model.addAttribute("address", as.getAddressWithId(id));
        return "addressForm";
    }

    @RequestMapping("address/delete/{id}")
    public String deleteAddress(@PathVariable Integer id, Model model) {
        if(ss.existsByAddressId(id))
            model.addAttribute("deletedFailMsg", "Address Cannot be deleted");
        else {
            as.deleteAddress(id);
            model.addAttribute("deletedSuccessMsg", "Address Deleted Successfully");
        }
        List<Address> addresses = as.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "address";
    }

    @RequestMapping(value = "student/add")
    public String addStudent(Model model) {
        Student newStudent = new Student();

        List<Integer> usedAddresses = ss.findAllUsedAddresses();
        List<Address> unusedAddress = as.findAllUnusedAddresses(usedAddresses);

        model.addAttribute("student", newStudent);
        model.addAttribute("addresses", unusedAddress);

        return "studentForm";
    }

    @RequestMapping(value = "student/addStudent", method = RequestMethod.POST)
    public String createStudent(Student newStudent) {
        ss.save(newStudent);
        return "redirect:/admin/students";
    }

    @RequestMapping(value = "student/update/{id}")
    public String updateStudent(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("student", ss.getById(id).get());

        List<Integer> usedAddresses = ss.findAllUsedAddresses();
        List<Address> unusedAddress = as.findAllUnusedAddresses(usedAddresses);
        unusedAddress.add(ss.getById(id).get().getAddress());
        model.addAttribute("addresses", unusedAddress);
        return "studentForm";
    }

    @RequestMapping(value = "student/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Integer id, RedirectAttributes attrs) {
        ss.deleteById(id);
        // attrs.addAttribute("deleteSuccessMsg", "Student Deleted Successfully!");
        return "redirect:/admin/students";
    }
}
