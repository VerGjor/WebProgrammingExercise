package mk.finki.ukim.wp.studentsapi.persistence.impl;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

@Profile("in-memory-db")
@Repository
public class StudentRepositoryImpl implements StudentRepository, StudyProgramRepository {

    private static List<Student> students;
    private static List<StudyProgram> study_programs;

    @PostConstruct
    public void init() {

        StudyProgram p1 = new StudyProgram();
        p1.name = "KNI";

        StudyProgram p2 = new StudyProgram();
        p2.id = 1L;
        p2.name = "PET";

        study_programs = Stream.of(p1, p2).collect(toList());

        Student student1 = new Student();
        student1.index = "151151";
        student1.name = "Veronika";
        student1.lastName = "Gjoreva";
        student1.studyProgram = p1;

        Student student2 = new Student();
        student2.index = "151095";
        student2.name = "Hristina";
        student2.lastName = "Krsteva";
        student2.studyProgram = p2;

        Student student3 = new Student();
        student3.index = "151133";
        student3.name = "Vanesa";
        student3.lastName = "Mihailova";
        student3.studyProgram = p2;

        students = Stream.of(student1, student2, student3).collect(toList());

    }

    @Override
    public List<Student> listAllStudents() {
        return students;
    }

    @Override
    public Student getStudentByIndex(String index) {

        Student student = new Student();

        for (Student s : students) {
            if (s.index.equals(index)) {
                student = s;
                break;
            }
        }

        return student;
    }

    @Override
    public Student addNewStudent(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student deleteStudent(String index) {
        Student student = getStudentByIndex(index);
        students.remove(students.indexOf(student));
        return student;
    }

    @Override
    public Student updateStudent(String index, String name, String lastName, String studyProgram) {

        Student student = getStudentByIndex(index);

        if (!student.name.equals(name))
            student.name = name;

        if (!student.lastName.equals(lastName))
            student.lastName = lastName;

        if (!student.studyProgram.name.equals(studyProgram)){
            StudyProgram p = getProgramByName(studyProgram);
            student.studyProgram = p;
        }

        return student;
    }

    @Override
    public List<Student> listStudentsByProgram(long program) {

        List<Student> list = new ArrayList<>();

        for (Student s : students) {
            if (s.studyProgram.id == program)
                list.add(s);
        }

        return list;
    }

    @Override
    public List<StudyProgram> listAllStudyPrograms() {
        return study_programs;
    }

    @Override
    public StudyProgram addNewStudyProgram(String name) {
        StudyProgram program = new StudyProgram();
        program.id = (long)study_programs.size();
        program.name = name;
        study_programs.add(program);
        return program;
    }

    @Override
    public void deleteStudyProgram(long id) {

        for (StudyProgram p : study_programs)
            if (p.id == id) {
                study_programs.remove(p);
                break;
            }
    }

    @Override
    public StudyProgram getProgramByName(String name) {

        StudyProgram program = new StudyProgram();

        for (StudyProgram p : study_programs)
            if (p.name.equals(name)) {
                program = p;
                break;
            }

        return program;
    }

}