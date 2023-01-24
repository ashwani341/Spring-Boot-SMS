package com.cdac.firstspingproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.firstspingproject.db.Student;
import com.cdac.firstspingproject.db.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;

	// insert
	public Student insertStudent(Student std) {
		studentRepo.save(std);

		return std;
	}

	// update
	public Student updateStudent(Student std) {
		studentRepo.save(std);

		return std;
	}

	// delete
	public Boolean deleteStudent(int rollNo) {
		Optional<Student> std = studentRepo.findById(rollNo);
		if (std.isPresent()) {
			studentRepo.delete(std.get());
			return true;
		}

		return false;
	}

	// getById
	public Student getStudentByRollNo(int rollNo) {
		Optional<Student> std = studentRepo.findById(rollNo);
		if (std.isPresent()) {
			return std.get();
		}

		return null;
	}

	// getAllStudents
	public List<Student> getAllStudents() {
		List<Student> list = null;
		list = studentRepo.findAll();

		return list;
	}

}
