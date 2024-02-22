import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link, Outlet } from 'react-router-dom';
import logo from '../images/logo.png';
import './Navbar.css';
import { useAuth } from './AuthContext';

const MyNavbar = () => {
    const { isLoggedIn } = useAuth();

  return (
    <>
      <Navbar className="navbar fixed-top customNavBar" expand="lg">
        <Navbar.Brand href="/">
          <img src={logo} width="30" height="30" className="d-inline-block align-top" alt="Logo" />
          {' Work In Progress'}
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            {isLoggedIn ? (
              <Nav.Link as={Link} to="/dashboard" className="customHover">&#9820; Dashboard</Nav.Link>
            ) : (
              <Nav.Link as={Link} to="/login" className="customHover">&#9814; Log In</Nav.Link>
            )}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <div className="mt-5">
        <Outlet />
      </div>
    </>
  );
};

export default MyNavbar;
