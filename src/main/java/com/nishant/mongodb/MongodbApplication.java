package com.nishant.mongodb;

import com.nishant.mongodb.data.Address;
import com.nishant.mongodb.data.Name;
import com.nishant.mongodb.data.Student;
import com.nishant.mongodb.repository.StudentRepository;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MongodbApplication implements CommandLineRunner {

	@Autowired
	StudentRepository  studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("-------------CREATE STUDENT-------------------------------\n");

		createStudent();

		System.out.println("\n----------------SHOW ALL STUDENTS---------------------------\n");

		showAllStudents();

		System.out.println("\n--------------GET STUDENT BY NAME-----------------------------------\n");

		getStudentByFirstName("Raj");

		System.out.println("\n-----------GET STUDENT BY ROLL NUMBER---------------------------------\n");

		getStudentByRollNumber(2);

		System.out.println("\n-----------UPDATE STUDENT ADDRESS ----------------\n");

		updateStudentAddress(1);

		System.out.println("\n----------DELETE A STUDENT ----------------------------------\n");

		deleteStudent(1);

	}

	private void deleteStudent(int rollNumber) {
		Student student = studentRepository.findStudentByRollNumber(rollNumber);
		studentRepository.delete(student);
		System.out.println(new StringFormattedMessage("student with roll number %s deleted",rollNumber));
		System.out.println("Latest students list");
		studentRepository.findAll().forEach(s-> System.out.println(s.toString()));
	}

	private void updateStudentAddress(int rollNumber) {
		Student student = studentRepository.findStudentByRollNumber(rollNumber);
		Address address = new Address("AddressLineNew","add","LA","1234");
		student.setAddress(address);
		Student savedData = studentRepository.save(student);
		System.out.println("Student data is updated");
		System.out.println(savedData.toString());
	}

	private void getStudentByRollNumber(int rollNumber) {
		System.out.println("Getting student by rollNumber"+rollNumber);
		Student student = studentRepository.findStudentByRollNumber(rollNumber);
		System.out.println(student.toString());
	}

	private void getStudentByFirstName(String name) {
		System.out.println("Getting student by name: " + name);
		var students = studentRepository.findStudentByFirstName(name);
		students.forEach(student -> System.out.println(student.toString()));
	}

	private void createStudent() {
		Name name1 = new Name("Sheldon", "Cooper");
		Address address1 = new Address("AddressLine one", "AddressLine two", "Tx", "00000");
		Student student1 = new Student();
		student1.setName(name1);
		student1.setAddress(address1);
		student1.setRollNumber(1);
		student1.setHobbies(List.of("Movies", "Cricket"));
		studentRepository.save(student1);

		Name name2 = new Name("Raj", "Koothrappalli");
		Address address2 = new Address("AddressLine one", "AddressLine two", "LA", "00000");
		Student student2 = new Student();
		student2.setName(name2);
		student2.setAddress(address2);
		student2.setRollNumber(2);
		student2.setHobbies(List.of("Movies"));
		studentRepository.save(student2);
	}

	private void showAllStudents() {
		studentRepository.findAll().forEach(student-> System.out.println(student.toString()));
	}

}
