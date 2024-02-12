import React from 'react';
import ChallengeBox from '../components/ChallengeBox.js';
//import SideNav from './components/SideNav.js'
//import './Challenges.css';

const Dashboard = () => {
        return(
            <div>
                <h1>Challenges</h1>
                <div className='challengeDiv'>
                <ChallengeBox
                    title={"Update Resume"}
                    description={"Something about why updating resume is necessary and maybe some resources to help " +
                                    "user out."}
                    type={"file"}
                />
                </div>
                <div className='challengeDiv'>
                <ChallengeBox
                    title={"Coding Challenge"}
                    description={"Something about why practicing coding challenges is crucial. Maybe throw up a " +
                                    "statistic or something."}
                    type={"url"}
                />
                </div>
                <div className='challengeDiv'>
                <ChallengeBox
                    title={"Earn a LinkedIn Certificate"}
                    description={"Talk about how this time can be used to review previous knowledge or pick up a new " +
                                    "skill."}
                    type={"url"}
                />
                </div>
            </div>
        );
}

export default Dashboard;
