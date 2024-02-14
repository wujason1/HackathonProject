import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Sidenav.css';
import { BiBullseye, BiTrophy} from 'react-icons/bi';
import { FaRegUserCircle } from 'react-icons/fa';

const SideNav = () => {
  const location = useLocation();
  const isActive = (pathname) => location.pathname === pathname;

  return (
    <div className="sidebar">
        <div className="list-group">
            <Link to="/dashboard" className={`list-group-item list-group-item-action ${isActive('/dashboard') ? 'active' : ''}`} > <BiBullseye/> &nbsp; Challenges </Link>
            <Link to="/prizes" className={`list-group-item list-group-item-action ${isActive('/prizes') ? 'active' : ''}`} > <BiTrophy/> &nbsp; Prizes </Link>
        </div>
        <div className="logout-button">
            <Link to="/login" className={`list-group-item list-group-item-action ${isActive('/prizes') ? 'active' : ''}`} > <FaRegUserCircle/> &nbsp; Log Out </Link>
        </div>
    </div>
  );
};

export default SideNav;

