package mk.finki.ukim.wp.studentsapi.persistence.impl;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;

@Profile("jpa-study-program-db")
public interface JpaStudyProgramRepository extends Repository<StudyProgram, Long>, StudyProgramRepository {

    @Override
    @Query("select s from StudyProgram s")
    List<StudyProgram> listAllStudyPrograms();

    @Query("select s from StudyProgram s where s.id = :id")
    StudyProgram getStudyProgram(Long id);

    @Query("select s from StudyProgram s where s.name = :name")
    StudyProgram getProgramByName(String name);

    @Override
    default StudyProgram addNewStudyProgram(String programName){
        StudyProgram added = addProgram(programName);
        return added;
    }

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("insert into StudyProgram s set s.name =:name")
    StudyProgram addProgram(@Param("name") String name);


    @Override
    default void deleteStudyProgram(long id) {
        removeProgram(id);
    }
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from StudyProgram s where s.id =:id")
    void removeProgram(@Param("id") Long id);


}