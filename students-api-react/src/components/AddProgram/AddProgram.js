import React from 'react';



class AddProgram extends React.Component {


    constructor(props) {
        super(props)

        this.state = {
            name: '',
            foundName: '',
        }

    }


    changeName = (n) => {

        let value = n.target.value
        /*getProgramByName(value).then(response => response.json())
            .then((data) => {
                console.log('data_name', data)
                this.setState({ foundName: data });
            })

        if (this.state.foundName !== '' && this.state.foundName !== value){
            this.setState({ name: value});
        }*/

        this.setState({ 
            name: value 
        });
        console.log('name', this.state.name)
    }

    submitInfo(student) {

        student.preventDefault();

        this.props.onNewStudent(this.state.name);
    }

    render() {

        return (
            <form className="float-right col-12">
                <div className="form-group">
                    <label>Name of program:</label>
                    <input name="name" type="text" className="form-control" onChange={n => this.changeName(n)} />
                </div>
                
                <div className="form-group mx-auto text-center">
                    <button className="btn bg-dark text-light m-2" onClick={c => this.submitInfo(c)}>Submit</button>
                </div>

            </form>
        );
    };
};

export default AddProgram;
