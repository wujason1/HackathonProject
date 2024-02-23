import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { BiBullseye, BiTrophy } from 'react-icons/bi';
import { FaRegUserCircle } from 'react-icons/fa';
import { useAuth } from './AuthContext';
import { Nav } from 'react-bootstrap';
import './Sidenav.css';

const SideNav = () => {
    const location = useLocation();
    const { logout } = useAuth();
    const isActive = (pathname) => location.pathname === pathname;

    const handleLogout = () => {
        logout();
    };

    return (
        <div className="sidebar">
            <Nav className="flex-column sideNavLinks">
                <Nav.Link as={Link} to="/dashboard" className={`nav-link ${isActive('/dashboard') ? 'active' : ''} sideNavDiv`}><BiBullseye /> Challenges</Nav.Link>
                <Nav.Link as={Link} to="/prizes" className={`nav-link ${isActive('/prizes') ? 'active' : ''} sideNavDiv`}><BiTrophy /> Prizes</Nav.Link>
                <Nav.Link onClick={handleLogout} className="nav-link sideNavDiv"><FaRegUserCircle /> Log Out</Nav.Link>
            </Nav>
        </div>
    );
};

export default SideNav;