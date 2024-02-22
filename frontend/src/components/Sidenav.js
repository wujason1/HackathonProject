import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Sidenav.css';
import { BiBullseye, BiTrophy} from 'react-icons/bi';
import { FaRegUserCircle } from 'react-icons/fa';
import { useAuth } from './AuthContext';

const SideNav = () => {
    const location = useLocation();
    const { logout } = useAuth(); // Get the logout function from the authentication context
    const isActive = (pathname) => location.pathname === pathname;

    const handleLogout = () => {
        logout();
    };


  return (
    <div className="sidebar">
        <div className="sideNavLinks">
            <Link to="/dashboard" className={`list-group-item list-group-item-action ${isActive('/dashboard') ? 'active' : ''}`} > <BiBullseye/> &nbsp; Challenges </Link>
            <Link to="/prizes" className={`list-group-item list-group-item-action ${isActive('/prizes') ? 'active' : ''}`} > <BiTrophy/> &nbsp; Prizes </Link>
        </div>
        <div className="logout-button">
            <Link to="/login" onClick={handleLogout} className={`list-group-item list-group-item-action ${isActive('/login') ? 'active' : ''}`}>
                <FaRegUserCircle/> &nbsp; Log Out
            </Link>
        </div>
    </div>
  );
};

export default SideNav;
