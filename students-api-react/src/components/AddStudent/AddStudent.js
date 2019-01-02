import React from 'react';


import { getProgramByName, getStudyProgramsFromApi } from "../../repository/studyProgramApi";


class AddStudent extends React.Component {


    constructor(props) {
        super(props)

        this.state = {
            name: '',
            lastName: '',
            index: '',
            studyProgram: '',
            studyPrograms: []
        }

    }

    componentDidMount(){

        getStudyProgramsFromApi()
            .then(response => response.json())
            .then((data) => {
                console.log('data: ', data)
                this.setState({
                    studyPrograms: data.map((item, id) => {
                        return (
                            <option key={id} > {item.name}</option>
                        )
                    })
                })
            });
    }

    

    changeName = (n) => {
        this.setState({ name: n.target.value });
    }

    changeLastName(c) {
        this.setState({ lastName: c.target.value });
    }

    changeStudentID(c) {
        this.setState({ index: c.target.value });
    }

    changeStudentDegree(c) {

        getProgramByName(c.target.value).then(response => response.json())
            .then((data) => {
                console.log('data_name', data)
                this.setState({ studyProgram: data });
            })
    }

    submitInfo(student) {

        student.preventDefault();

        let item = {
            name: this.state.name,
            lastName: this.state.lastName,
            index: this.state.index,
            studyProgram: this.state.studyProgram
        }
        
        console.log('item', item)
        this.props.onNewStudent(item);
    }

    render() {

        return (
            <form className="float-right col-12">
                <div className="form-group">
                    <label>First name:</label>
                    <input name="name" type="text" className="form-control" onChange={n => this.changeName(n)} />
                </div>
                <div className="form-group">
                    <label>Last name:</label>
                    <input name="lastName" type="text" className="form-control" onChange={this.changeLastName.bind(this)} />
                </div>
                <div className="form-group">
                    <label>Student ID:</label>
                    <input name="index" type="text" className="form-control" onChange={this.changeStudentID.bind(this)} />
                </div>
                <div className="form-group">
                    <label>Studies:</label>
                    <select className="form-control" onChange={this.changeStudentDegree.bind(this)}>
                        <option defaultValue> -- select a program -- </option>
                        {this.state.studyPrograms}
                    </select>
                </div>

                <div className="form-group mx-auto text-center">
                    <button className="btn bg-dark text-light m-2" onClick={c => this.submitInfo(c)}>Submit</button>
                </div>

            </form>
        );
    };
};

export default AddStudent;
