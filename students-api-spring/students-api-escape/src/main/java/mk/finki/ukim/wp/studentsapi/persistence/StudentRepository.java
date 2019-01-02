package mk.finki.ukim.wp.studentsapi.persistence;

import mk.finki.ukim.wp.studentsapi.model.*;
import java.util.List;

public interface StudentRepository {
    List<Student> listAllStudents();
    Student getStudentByIndex(String index);
    List<Student> listStudentsByProgram(long program);
    Student addNewStudent(Student student);
    Student deleteStudent(String index);
    Student updateStudent(String index, String name, String lastName, String studyProgram);
}