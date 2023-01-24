package com.cdac.firstspingproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.firstspingproject.db.Student;
import com.cdac.firstspingproject.services.StudentService;


@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	// router for the home page
	@GetMapping("/")
	String getStudents(Model model) {
		List<Student> list = studentService.getAllStudents();

		model.addAttribute("studentsList", list);

		return "getStudents"; // returns getStudent.html
	}

	// router for student form
	@GetMapping("/register")
	String sendStudentRegistrationPage(Student std, Model model) {
		model.addAttribute("student", std); // sending student object to the Thymeleaf view
		return "studentRegistration";
	}

	// router for the student form submit
	@PostMapping("/registerStudent")
	String registerStudent(Student std, Model model) {
		studentService.insertStudent(std);

		return "redirect:/"; // redirects to the '/' router
	}

	// router for deleting a student
	@GetMapping("/deleteStudent/{rollNo}")	// url ex: editStudent/1
	String deleteStudent(@PathVariable int rollNo) {	// @PathVariable int rollNo: will access the path variable "1"(rollNo)
		studentService.deleteStudent(rollNo);			

		return "redirect:/"; // redirects to the '/' router
	}

	// router for deleting a student
	@GetMapping("/edit{rollNo}")	// url ex: editStudent?rollNo=1
	String editStudent(@RequestParam int rollNo, Model model) {	// @RequestParam int rollNo: will access the query parameter "1"(rollNo)
		Student std = null;
		std = studentService.getStudentByRollNo(rollNo);
		model.addAttribute("student", std);
		
		return "editStudentForm"; // redirects to the '/' router
	}
	@PostMapping("/editStudent")
	String handleEdit(Student std, Model model) {
		studentService.updateStudent(std);
		
		return "redirect:/";
	}

}
