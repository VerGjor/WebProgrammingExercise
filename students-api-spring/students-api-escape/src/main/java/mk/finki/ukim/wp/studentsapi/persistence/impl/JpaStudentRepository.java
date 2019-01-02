package mk.finki.ukim.wp.studentsapi.persistence.impl;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;

@Profile("jpa-student-db")
public interface JpaStudentRepository extends Repository<Student, String>, StudentRepository{

    @Override
    @Query("select s from Student s where index= :index")
    Student getStudentByIndex(@Param("index") String index);

    @Override
    @Query("select s from Student s")
    List<Student> listAllStudents();

    @Override
    @Query("select s from Student s where studyProgram.id= :id")
    List<Student> listStudentsByProgram(@Param("id") long id);

    @Override
    default Student addNewStudent(Student student){
        Student added = addStudent(student);
        return added;
    }

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("insert into Student s set s.index =:student.index and s.name =:student.name and s.lastName =:student.lastName and s.studyProgram = :student.studyProgram")
    Student addStudent(@Param("student") Student student);


    @Override
    default Student deleteStudent(String index) {
        Student removed = removeStudent(index);
        return removed;
    }

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from Student s where s.index =:index")
    Student removeStudent(@Param("index") String index);

    @Override
    default Student updateStudent(String index, String name, String lastName, String studyProgram){
        Student updated = updateStudentInfo(index, name, lastName, studyProgram); 
        return updated;
    }

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Student s set s.name = :name and s.lastName = :lastName and s.studyProgram = :studyProgram where s.index =:index")
    Student updateStudentInfo(String index, String name, String lastName, String studyProgram);

}