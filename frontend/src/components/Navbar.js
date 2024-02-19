import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link, Outlet } from 'react-router-dom';
import bigLogo from '../images/bigLogo.png';
import './Navbar.css';

const MyNavbar = () => {
  return (
    <>
      <Navbar className="navbar fixed-top customNavBar" expand="lg">
        <Navbar.Brand href="/" className="navbarBrand">
          <img src={bigLogo} width="130" height="30" className="d-inline-block align-top" alt="bigLogo" />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link as={Link} to="/login" className="customHover">&#9814; Log In</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <div className="mt-5">
        <Outlet />
      </div>
    </>
  );
}

export default MyNavbar;
