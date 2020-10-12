import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/style.css';
import 'bootstrap/dist/js/bootstrap.js';

function Header() {
    return (
        <header className="jumbotron">
                <div className="container">
                    <div className="row row-header">
                        <div className="col-12 col-sm-6">
                            <h1>Bank App</h1>
                            <p>Send and get money all over the world!</p>
                        </div>
                        <div className="col-12 col-sm">
                        </div>
                    </div>
                </div>
        </header>
    )
}

export default Header