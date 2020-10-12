import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/style.css';
import 'bootstrap/dist/js/bootstrap.js';

function Footer() {
    return (
        <footer className="footer">
            <div className="container">
                    <div className="row">             
                        <div className="col-4 offset-1 col-sm-2">
                            <h5>Links</h5>
                            <ul className="list-unstyled">
                                <li><a href="#">Home</a></li>
                                <li><a href="#">About</a></li>
                                <li><a href="#">Menu</a></li>
                                <li><a href="#">Contact</a></li>
                            </ul>
                        </div>
                        <div className="col-7 col-sm-5">
                            <h5>Our Address</h5>
                            <ul className="list-unstyled">
                            <li>121, Clear Water Bay Road</li>
                            <li>Clear Water Bay, London</li>
                            <li>LONDON UK</li>
                            <li>Tel.: +222 3333 4444</li>
                            <li>Fax: +111 2222 3333</li>
                            <li>Email: <a href="mailto:bankapplication@bank.net">bankapplication@bank.net</a></li>
                            </ul>
                        </div>
                        <div className="col-12 col-sm-4 align-self-center">
                            <div className="text-center">
                                <a href="http://google.com/+">Google+</a>
                                <a href="http://www.facebook.com/profile.php?id=">Facebook</a>
                                <a href="http://www.linkedin.com/in/">LinkedIn</a>
                                <a href="http://twitter.com/">Twitter</a>
                                <a href="http://youtube.com/">YouTube</a>
                                <a href="mailto:">Mail</a>
                            </div>
                        </div>
                </div>
                <div className="row justify-content-center">             
                        <div className="col-auto">
                            <p>© Copyright 2020 Bank Application</p>
                        </div>
                </div>
            </div>
        </footer>
    )
}

export default Footer