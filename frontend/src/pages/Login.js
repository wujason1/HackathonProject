import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Alert } from 'react-bootstrap'; // Import Alert component for showing error
import '../index.css';

const Login = ( ) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showError, setShowError] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/login?email=' + email + "&password=" + password, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
            });
            if (response.ok) {
                // Redirect to dashboard upon successful registration
                window.location.href = '/dashboard';
            } else {
                // error message
                setShowError(true);
            }
        } catch (error) {
            setShowError(true);
        }
    }

    return (
        <div>
            <div className="loginDiv">
                <h1>Work In Progress</h1>
                <h2> Gamify Your Job Application Process!</h2>
                <hr />
                {showError && ( // Conditionally render error message
                    <Alert variant="danger">
                        Incorrect email or password. Please try again.
                    </Alert>
                )}
                <form onSubmit={handleSubmit}>
                    <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                    <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                    <Button type="submit" variant="dark"> Login </Button>
                </form>
                <h4>Don't have an account? <Link to="/registration">Create one!</Link></h4>
            </div>
        </div>
    );
};

export default Login;
