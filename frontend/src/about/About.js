// import ReactGA from 'react-ga';
// ReactGA.initialize('G-ENGTWW7TKF'); // Replace with tracking ID, if go live ever
import React from 'react';
import { Link } from 'react-router-dom';
import TeamMember from './TeamMember';
import logo from '../images/logo.png';
import kiraAC from '../images/kiraAC.png';
import erynnAC from '../images/erynnAC.png';
import jasonAC from '../images/jasonAC.png';
import './About.css';

const About = () => {
  const teamMembers = [
    { name: 'Kira Sanford', position: 'UI/UX Design', image: kiraAC },
    { name: 'Erynn Ramos', position: 'Full Stack Developer', image: erynnAC },
    { name: 'Jason Wu', position: 'Backend Developer', image: jasonAC},
  ];

  return (
    <div className="background">
        <div className="two-column-row">
            <div className="column">
                <img src={logo} alt="logo" width="500" height="500"></img>
            </div>
            <div className="column">
                <h1>Gamify Your Job Application Process! </h1>
                    <Link to="/register"><button>Get Started</button></Link>
                    <Link to="/login"><button>I have an Account</button></Link>
            </div>
        </div>

        <div className="banner"></div>

        <div className="two-column-row">
            <div className="column">
                <img src={logo} alt="logo" width="500" height="500"></img>
            </div>
            <div className="column">
                <h1>In 2023, a reported 262,682 workers in tech lost their jobs. An increase from 164,969 in 2022. The number is expected to continue to rise in 2024, with the number at 7,520 between about 51 tech companies as of January 15. </h1>
            </div>
        </div>
        <div className="two-column-row">
            <div className="column">
                <h1>Motivation/biggest struggle </h1>
            </div>
            <div className="column">
                <img src={logo} alt="logo" width="500" height="500"></img>
            </div>
        </div>
        <div className="two-column-row">
            <div className="column">
                <img src={logo} alt="logo" width="500" height="500"></img>
            </div>
            <div className="column">
                <h1>Stay Motivated with Work In Progress</h1>
            </div>
        </div>

        <div className="banner">
            <h1>Get Rewarded! Whether You Land The Job Or Not</h1>
        </div>

        <h1>Future Plans</h1>
        <p>social media, personalized objectives, expand to other fields besides tech, mobile app</p>

        <h1>Meet the Team</h1>
        <div className="teamMembers">
            {teamMembers.map((member, index) => (
                <TeamMember key={index} name={member.name} position={member.position} image={member.image} />
            ))}
        </div>
    </div>
  );
};

export default About;