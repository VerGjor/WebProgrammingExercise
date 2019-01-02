import React from 'react';

import { getStudyProgramsFromApi, createProgram } from "../../repository/studyProgramApi";

import ProgramItem from "../ProgramItem/ProgramItem";
import AddProgram from "../AddProgram/AddProgram";
import EditProgramDetails from "../EditProgramDetails/EditProgramDetails";

class ProgramList extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            isHidden: false,
            id: 0,
            listOfStudents: [],
            list: [],
            addStudentButton: false
        }

        this.changeInfo = this.changeInfo.bind(this)
        this.toggleHidden = this.toggleHidden.bind(this)
    }

    componentWillReceiveProps(nextProps) {
        if (this.props.programList !== nextProps.programList)
            this.setState({
                listOfStudents: nextProps.programList,
                list: nextProps.programList.map((item, index) => {
                    return (
                        <tr key={index} onClick={this.toggleHidden.bind(this, index)}>
                            <td>
                                <span className="pl-4 pr-4 btn">
                                    <ProgramItem program={item}
                                    />
                                </span>
                            </td>
                        </tr>
                    );
                })
            })
    }

    showAddStudentButton() {
        this.setState({
            addStudentButton: !this.state.addStudentButton
        })
    }


    onNewStudent = (program) => {

        console.log('name', program)
        createProgram(program)
        getStudyProgramsFromApi()
            .then(response => response.json())
            .then((data) => {
                console.log('programs', data)
                this.setState({
                    addStudentButton: false,
                    listOfStudents: data,
                    list: data.map((item, index) => {
                        return (
                            <tr key={item.index} onClick={this.toggleHidden.bind(this, index)}>
                                <td>
                                    <span className="pl-4 pr-4 btn">
                                        <ProgramItem program={item}
                                        />
                                    </span>
                                </td>
                            </tr>
                        );
                    })

                })
            });
    };

    toggleHidden(t) {
        this.setState({
            isHidden: !this.state.isHidden,
            id: t
        })
    }


    changeInfo = (customStudent) => {

        this.state.listOfStudents[this.state.id] = customStudent;


        this.setState({

            list: this.state.listOfStudents.map((item, index) => {
                return (
                    <tr key={item.index} onClick={this.toggleHidden.bind(this, index)}>
                        <td>
                            <span className="pl-4 pr-4 btn">
                                <ProgramItem program={item} />
                            </span>
                        </td>
                    </tr>
                )
            }),
            isHidden: false
        })

    };


    render() {

        return (

            <div className="container mt-4 d-inline">

                <h2 className="mx-auto text-center">Study Programs information</h2>
                <h3 className="mx-auto text-center">Web Programming
        </h3>
                <div className="form-group mx-auto text-center">
                    <button className="btn bg-dark text-light" onClick={this.showAddStudentButton.bind(this)}>Add Program</button>
                </div>
                <hr />
                <table className="table float-left col-3 mt-2 text-center table-bordered table-hover">
                    <thead>
                        <tr className="bg-dark text-light">
                            <th>Programs</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.list}
                    </tbody>
                </table>

                <div className="col-5 float-right mt-2">
                    {this.state.isHidden &&
                        <EditProgramDetails key={this.state.id} program={this.state.listOfStudents[this.state.id]} />}

                    {this.state.addStudentButton && <AddProgram student='' onNewStudent={this.onNewStudent} />}
                </div>

            </div>
        );
    };


};

export default ProgramList;