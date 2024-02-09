// import ReactGA from 'react-ga';
// ReactGA.initialize('G-ENGTWW7TKF'); // Replace with tracking ID, if go live ever
import React from 'react';
import { Link } from 'react-router-dom';
import { Row, Col, Button, Container } from 'react-bootstrap';
import TeamMember from './TeamMember';
import logo from '../images/logo.png';
import layOff from '../images/layOff.png';
import motivation from '../images/motivation.png';
import stayMotivated from '../images/stayMotivated.png';
import socialMedia from '../images/socialMedia.png';
import personalObj from '../images/personalObj.png';
import expand from '../images/expand.png';
import mobileApp from '../images/mobileApp.png';
import kiraAC from '../images/kiraAC.png';
import erynnAC from '../images/erynnAC.png';
import jasonAC from '../images/jasonAC.png';
import './About.css';

const About = () => {
  const teamMembers = [
    { name: 'Kira Sanford', position: 'UI/UX Designer', image: kiraAC },
    { name: 'Erynn Ramos', position: 'Full Stack Developer', image: erynnAC },
    { name: 'Jason Wu', position: 'Backend Developer', image: jasonAC},
  ];

  return (
    <div className="background">
        <Row className="rowPadding justify-content-center align-items-center h-100">
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <img src={logo} alt="logo" width="800" height="800"></img>
                </div>
            </Col>
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <h1>Gamify Your Job Application Process! </h1>
                <Link to="/registration"><Button variant="dark">Get Started</Button></Link>
                <Link to="/login"><Button variant="light">I have an Account</Button></Link>
                </div>
            </Col>
        </Row>

        <div className="banner"> <h1>Get Rewarded! Whether You Land The Job Or Not</h1> </div>

        <Row className="rowPadding justify-content-center align-items-center h-100">
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <img src={layOff} alt="layOff"></img>
                </div>
            </Col>
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <h1>In 2023, a reported 262,682 workers in tech lost their jobs. An increase from 164,969 in 2022. The number is expected to continue to rise in 2024, with the number at 32,496 as of February 5. (<a href="https://www.nerdwallet.com/article/finance/tech-layoffs#:~:text=A%20total%20of%20262%2C682%20workers,in%202020%20and%202021%20combined.">source</a>)</h1>
                </div>
            </Col>
        </Row>

        <Row className="rowPadding justify-content-center align-items-center h-100">
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <h1>With a very saturated market it is extremely difficult for anyone to find a new tech job regardless of experience or education. One of the biggest struggles is staying motivated as time goes on with only rejection emails to show. </h1>
                </div>
            </Col>
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <img src={motivation} alt="motivation"></img>
                 </div>
            </Col>
        </Row>

        <Row className="rowPadding justify-content-center align-items-center h-100">
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <img src={stayMotivated} alt="stayMotivated"></img>
                </div>
            </Col>
            <Col xs={6} className="d-flex justify-content-center align-items-center">
                <div>
                <h1>Stay motivated with Work In Progress! We help you stick with your objectives and reward you for your progress.</h1>
                </div>
            </Col>
        </Row>

        <div className="banner"> <h1>Future Plans</h1> </div>
        <h3>As a hackathon project, we aren't able to put in everything we want, but if had more time, here are some things we would have like to do</h3>
            <Row className="rowPadding">
                <Col>
                    <img src={socialMedia} alt="socialMedia" width="150" height="150"></img>
                    <h3>Set up social media</h3>
                </Col>
                <Col>
                    <img src={personalObj} alt="personalObj" width="150" height="150"></img>
                    <h3>Personalized objectives</h3>
                </Col>
                <Col>
                    <img src={expand} alt="expand" width="150" height="150"></img>
                    <h3>Expand to other career fields</h3>
                </Col>
                <Col>
                    <img src={mobileApp} alt="mobileApp" width="150" height="150"></img>
                    <h3>Create a mobile app</h3>
                </Col>
            </Row>

        <h1>Meet the Team</h1>
        <div className="teamMembers">
            {teamMembers.map((member, index) => (
                <TeamMember key={index} name={member.name} position={member.position} image={member.image} />
            ))}
        </div>

        <footer className="footer text-light py-4">
            <Container className="text-center">
                <p>GitLab Innovation Pitch Competition</p>
             </Container>
        </footer>
    </div>
  );
};

export default About;