import React from 'react';

import { getProgramByName, getStudyProgramsFromApi} from "../../repository/studyProgramApi";
import {stopActivity, deleteStudent, getStudentsByIndex} from "../../repository/studentsApi";


class EditStudentDetails extends React.Component{


    constructor (props) {
        super(props)

        this.state = {
            name: '',
            lastName: '',
            index: '',
            studyProgram: ''
        }

    }

    async componentDidMount(){
        var index = this.props.studentID
        console.log('index', index)
        getStudentsByIndex(index).then(response => response.json())
            .then((data) => {
                console.log('data: ', data)
                this.setState({
                    name: data.name,
                    lastName: data.lastName,
                    index: data.index,
                    studyProgram: data.studyProgram.name
                })

            });

    }

    changeName = (n) => {
        this.setState({name: n.target.value});
    }

    changeLastName(c){
        this.setState({lastName: c.target.value});
    }

    changeStudentID(c){
        this.setState({index: c.target.value});
    }

    changeStudentDegree(c) {
        this.setState({ studyProgram: c.target.value });
    }

    getInfo(){
        this.getPrograms();
    }

    changeInfo(s){

        s.preventDefault();

        let item = {
            name: this.state.name,
            lastName: this.state.lastName,
            index: this.state.index,
            studyProgram: this.state.studyProgram
        }

        stopActivity(item)
        this.props.changeInfo(item);
    }

    removeStudent(s){
        deleteStudent(this.props.studentID);
    }


    render(){

        return(
            <form className="float-right col-12">
                <div className="form-group">
                    <label>First name:</label>
                    <input name="name" type="text" className="form-control" placeholder={this.state.name} onChange={n => this.changeName(n)}/>
                </div>
                <div className="form-group">
                    <label>Last name:</label>
                    <input name="lastName" type="text" className="form-control" placeholder={this.state.lastName} onChange={this.changeLastName.bind(this)}/>
                </div>
                <div className="form-group">
                    <label>Student ID:</label>
                    <input name="index" type="text" className="form-control" placeholder={this.state.index} onChange={this.changeStudentID.bind(this)}/>
                </div>
                <div className="form-group">
                    <label>Studies:</label>
                    <input name="studyProgram" type="text" className="form-control" placeholder={this.state.studyProgram} onChange={this.changeStudentDegree.bind(this)} />
                </div>

                <div className="form-group mx-auto text-center">
                    <button className="btn bg-dark text-light m-2" onClick={c => this.changeInfo(c)}>Submit</button>
                    <button className="btn bg-dark text-light m-2" onClick={c => this.removeStudent(c)}>Delete</button>
                </div>

                <div className="form-group mx-auto text-center">
                    
                </div>

            </form>
          );
    };
};

export default EditStudentDetails;
