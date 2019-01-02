package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.exceptions.DuplicateStudentException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.InvalidFormatException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.MissingParamException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentByIndex(String index) throws StudentNotFoundException;

    Student addNewStudent(Student student) throws DuplicateStudentException, StudyProgramNotFoundException,
            MissingParamException, InvalidFormatException;

    void deleteStudent(String index) throws StudentNotFoundException;

    Student updateStudent(String index, String name, String lastName, String studyProgram) throws StudentNotFoundException, StudyProgramNotFoundException;

    List<Student> getStudentsByProgram(long program) throws StudyProgramNotFoundException;

}
