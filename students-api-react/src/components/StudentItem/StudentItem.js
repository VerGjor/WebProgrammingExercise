import React from 'react';

class StudentItem extends React.Component{

    render(){
        return(
          <div className="col-md-12">
            {this.props.student.name} {this.props.student.lastName}
          </div>);
    };
};

export default StudentItem;