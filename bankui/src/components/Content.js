import React from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import '../css/style.css';
import SideNav from './SideNav';
import Login from './Login';

function Content() {

    return (
        <div className="container">
            <div className="row row-content align-items-center">
                <div className="col-12 col-sm-9">
                    <Login />
                </div>
                <div className="col-12 col-sm  align-items-center">
                    <SideNav />
                </div>   
            </div>
        </div>

    )

}

export default Content
