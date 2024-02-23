import React, { useState, useEffect } from 'react';
import ChallengeBox from '../components/ChallengeBox.js'
import Sidenav from '../components/Sidenav';
import { FileEarmarkPerson, JournalCode, Award } from 'react-bootstrap-icons';
import { useAuth } from '../components/AuthContext';

const Dashboard = () => {
    const { isLoggedIn } = useAuth();
    const [shouldRedirect, setShouldRedirect] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setShouldRedirect(!isLoggedIn);
        }, 100);
        return () => clearTimeout(timer);
    }, [isLoggedIn]);

    // If not logged in, redirect to login page
    if (shouldRedirect) {
        window.location.href = '/login';
        return null;
    }

  return (
    <div className="container-fluid">
        <div className="row">
            <div className="col-md-3">
                <Sidenav />
            </div>

            <div className="col-md-9 d-flex justify-content-center align-items-center">
                <main role="main" className="px-4">
                    <div className="colorWhite">
                        <h1>Challenges</h1>

                    <div className='challengeDiv'>
                        <ChallengeBox
                        title={"Update Resume"}
                        description={"With an updated resume, you'll always be ready to submit your applications or know what to talk about during interviews." }
                        type={"file"}
                        icon={FileEarmarkPerson}
                        />
                    </div>
                    <div className='challengeDiv'>
                        <ChallengeBox
                        title={"Coding Challenge"}
                        description={"Most software roles have a coding challenge in the interview process. Practicing with Leetcode helps keep your mind sharp for these interviews."}
                        type={"url"}
                        icon={JournalCode}
                        />
                    </div>
                    <div className='challengeDiv'>
                        <ChallengeBox
                        title={"Earn a Certificate"}
                        description={"Review old skills or pick up a new one. Getting a certificate is a great way to show off your desire to learn more."}
                        type={"url"}
                        icon={Award}
                        />
                    </div>
                    </div>
                </main>
            </div>
        </div>
    </div>
  );
};

export default Dashboard;
