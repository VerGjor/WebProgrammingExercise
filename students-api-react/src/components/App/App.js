import React, { Component } from 'react';
import './App.css';

import StudentList from "../StudentList/StudentList";
import ProgramList from "../ProgramList/ProgramList";
//import {listStudents} from "../../repository/studentRepository";
import {getStudentsFromApi} from "../../repository/studentsApi";
import { getStudyProgramsFromApi } from "../../repository/studyProgramApi";


class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            info: [],
            trigger: 0,
            showStudents: true
        }
      }

    

    render() {
        console.log('students', this.state.info)   
        
     return (
         <div className="container">
             <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                 <span className="navbar-brand">Web programming</span>
                 <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                     <span className="navbar-toggler-icon"></span>
                 </button>
                 <div className="collapse navbar-collapse" id="navbarNav">
                     <ul className="navbar-nav">
                         <li className="nav-item">
                             <span className="nav-link" onClick={() => this.changeDisplayInfo(0)}>Students<span className="sr-only">(current)</span></span>
                         </li>
                         <li className="nav-item">
                             <span className="nav-link" onClick={() => this.changeDisplayInfo(1)}>Study programs</span>
                         </li>
                     </ul>
                 </div>
             </nav>
             <div className="row">
               {this.state.showStudents && <StudentList studentList={this.state.info}/>}
                 {!this.state.showStudents && <ProgramList programList={this.state.info} />}
             </div>
           </div>

        );
    }

    changeDisplayInfo(c){

        if(this.state.trigger !== c){
        
            this.setState({
                trigger: c,
                showStudents: !this.state.showStudents
            })

            this.loadData(c)
        }
    }

    componentDidMount(){
        this.loadData(this.state.trigger)
    }

    

    loadData(index) {

        if(index === 0){
            getStudentsFromApi()
                .then(response => response.json())
                .then((data) => {
                    console.log('data: ', data)
                    this.setState({
                        info: data
                    })
            });
        }
        else{
            getStudyProgramsFromApi().then(response => response.json())
                .then((data) => {
                    console.log('data: ', data)
                    this.setState({
                        info: data
                    })
                });
        }
        
    };

}

export default App;
