export const getStudentsFromApi = () => {
    return fetch('http://localhost:3306/students/');
};

export const getStudentsByProgram = (id) => {
    return fetch('http://localhost:3306/students/by_study_program/' + id);
};

export const getStudentsByIndex = (index) => {
    return fetch('http://localhost:3306/students/' + index);
};


export const createStudent = (student) => {
    return fetch('http://localhost:3306/students/', {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            index: student.index,
            name: student.name,
            lastName: student.lastName,
            studyProgram: student.studyProgram
        })

    });
};

export const startActivity = (student) => {
    return fetch('http://localhost:3306/students/' + student.index, {
        method: 'PUT'
    });
};


export const stopActivity = (student) => {
    return fetch('http://localhost:3306/students/'+student.index+'?name='+student.name+'&lastName='+student.lastName+'&studyProgram='+student.studyProgram, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    });
};

export const deleteStudent = (index) => {
    return fetch('http://localhost:3306/students/' + index, {
        method: 'DELETE', 
        headers: {
            'Content-Type': 'application/json'
        }
    });
};