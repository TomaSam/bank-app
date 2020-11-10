import React from 'react';
import { Navbar, NavbarBrand } from 'reactstrap';

function Header() {
    return (
        <>
            <Navbar dark expand="md">
                <div className="container">
					<NavbarBrand className="mr-auto" href="/">
                        <h2>Bank App</h2>
					</NavbarBrand>   
                </div>
            </Navbar>
        </>
    )
}

export default Header