import React from 'react';

class ProgramItem extends React.Component {

    render() {
        return (
            <div className="col-md-12">
                {this.props.program.name}
            </div>);
    };
};

export default ProgramItem;