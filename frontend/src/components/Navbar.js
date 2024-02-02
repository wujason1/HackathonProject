import React from 'react';
import './Navbar.css';
import { Outlet, Link } from "react-router-dom";
import logo from '../images/logo.png';

const Navbar = () => {
  return (
    <>
    <nav className="navbar">
      <ul className="nav-menu">
        <li className="navItem">
          <a href="/"><img src={logo} alt="logo"   width="20" height="20"></img>Work In Progress</a>
        </li>
        <li className="navItem">
          <Link to="/login">&#9814; Log In</Link>
        </li>
      </ul>
    </nav>
    <Outlet />
    </>
  );
}

export default Navbar;
