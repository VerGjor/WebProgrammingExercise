export const getStudyProgramsFromApi = () => {
    return fetch('http://localhost:3306/study_programs/');
};

export const getProgramByName = (name) => {
    return fetch('http://localhost:3306/study_programs/' + name, {
        method: 'GET'
    });
};

export const createProgram = (program) => {
    return fetch('http://localhost:3306/study_programs/', {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: program

    });
};

export const deleteProgram = (id) => {
    return fetch('http://localhost:3306/study_programs/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
};