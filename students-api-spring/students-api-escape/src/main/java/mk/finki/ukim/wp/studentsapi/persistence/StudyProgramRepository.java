package mk.finki.ukim.wp.studentsapi.persistence;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;

public interface StudyProgramRepository {
    
    List<StudyProgram> listAllStudyPrograms();

    StudyProgram addNewStudyProgram(String programName);

    void deleteStudyProgram(long id);

    StudyProgram getProgramByName(String name);

}