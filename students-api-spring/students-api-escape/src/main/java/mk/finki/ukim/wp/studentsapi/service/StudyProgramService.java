package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;

public interface StudyProgramService {

    List<StudyProgram> getStudyPrograms();

    void deleteStudyProgram(long id);

    StudyProgram createStudyProgram(String program);

    StudyProgram getProgramByName(String name);

}
