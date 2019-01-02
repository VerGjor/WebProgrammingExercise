package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.exceptions.DuplicateStudentException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.InvalidFormatException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.MissingParamException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;


import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.listAllStudents();
    }

    @Override
    public Student getStudentByIndex(String index) {

        List<Student> list = getAllStudents();
        boolean flag = false;

        for(Student s : list){
            if(s.index.equals(index)){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new StudentNotFoundException();
        return studentRepository.getStudentByIndex(index);
    }

    @Override
    public Student addNewStudent(Student student) throws DuplicateStudentException {
       
        if(student.index.equals("") || student.name.equals("") || student.lastName.equals("") || student.studyProgram == null)
            throw new MissingParamException();
        
        if(student.index.length() != 6)
            throw new InvalidFormatException();

        List<Student> list = getAllStudents();
        boolean flag = false;

        for (Student s : list) {
            if (s.index.equals(student.index)) {
                flag = true;
                break;
            }
        }

        if (flag)
            throw new DuplicateStudentException();

        return studentRepository.addNewStudent(student);
    }

    @Override
    public void deleteStudent(String index) {
        if (!getAllStudents().contains(getStudentByIndex(index)))
            throw new StudentNotFoundException();
        studentRepository.deleteStudent(index);
    }

    @Override
    public List<Student> getStudentsByProgram(long program){
        
        List<Student> list = getAllStudents();
        boolean flag = false;

        for (Student s : list) {
            if (s.studyProgram.id == program) {
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new StudyProgramNotFoundException();
        return studentRepository.listStudentsByProgram(program);
	}

    @Override
    public Student updateStudent(String index, String name, String lastName, String studyProgramName) {

        List<Student> list = getAllStudents();
        boolean flagProgramFound = false;
        boolean flagStudentFound = false;

        for (Student s : list) {
            if (s.index.equals(index)) {
                flagStudentFound = true;
                break;
            }
        }

        for (Student s : list) {
            if (s.studyProgram.name.equals(studyProgramName)) {
                flagProgramFound= true;
                break;
            }
        }
        if (!flagStudentFound)
            throw new StudentNotFoundException();
        
        if (!flagProgramFound)
            throw new StudyProgramNotFoundException();

        return studentRepository.updateStudent(index, name, lastName, studyProgramName);
    }
    
}
