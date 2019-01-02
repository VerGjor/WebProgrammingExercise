import React from 'react';

import { getProgramByName, deleteProgram } from "../../repository/studyProgramApi";
import { getStudentsByProgram } from "../../repository/studentsApi";


class EditProgramDetails extends React.Component {


    constructor(props) {
        super(props)

        this.state = {
            id: this.props.program.id,
            name: this.props.program.name,
            showDelete: false,
            list: []
        }

    }

    async componentDidMount() {
        
        getStudentsByProgram(this.state.id).then(response => response.json())
            .then((data) => {
                console.log('data: ', data)
                if(data.status !== 404){
                    this.setState({
                        list: data.map((item) => {
                            return(
                                <li>{item.index} - {item.name}</li>
                            )
                        })
                    })
                }
                else{
                    this.setState({
                        list: 'No students have taken this class',
                        showDelete: true
                    })
                }
                
                
            });

    }


    removeProgram(s) {
        deleteProgram(this.state.id);
    }


    render() {

        return (
            <form className="float-right col-12">
                <div className="form-group">
                    <label>Name of Program:</label>
                    <input name="name" type="text" className="form-control" placeholder={this.state.name} />
                </div>

                {this.state.showDelete &&
                    <div className="form-group mx-auto text-center">
                        <button className="btn bg-dark text-light m-2" onClick={c => this.removeProgram(c)}>Delete</button>
                    </div>
                }

                <div className="form-group mx-auto">
                    <label>Students in the program:</label>
                    <ul>
                    {this.state.list}
                    </ul>
                </div>

            </form>
        );
    };
};

export default EditProgramDetails;
