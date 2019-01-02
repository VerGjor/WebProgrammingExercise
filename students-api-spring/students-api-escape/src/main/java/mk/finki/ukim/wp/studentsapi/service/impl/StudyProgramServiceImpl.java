package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;

import org.springframework.stereotype.Service;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;

import java.util.List;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository programRepository;

    public StudyProgramServiceImpl(StudyProgramRepository repository) {
        this.programRepository = repository;
    }

    @Override
    public List<StudyProgram> getStudyPrograms() {
        return programRepository.listAllStudyPrograms();
    }

    @Override
    public void deleteStudyProgram(long id) {
        programRepository.deleteStudyProgram(id);
    }

    @Override
    public StudyProgram createStudyProgram(String program) {
        programRepository.addNewStudyProgram(program);
        return programRepository.listAllStudyPrograms().get(getStudyPrograms().size()-1);
    }

    @Override
    public StudyProgram getProgramByName(String name) {
       
        List<StudyProgram> list = getStudyPrograms();

        boolean flag = false;

        for (StudyProgram s : list) {
            if (s.name.equals(name)) {
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new StudyProgramNotFoundException();

        return programRepository.getProgramByName(name);
    }



}
