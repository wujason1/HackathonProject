import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Alert } from 'react-bootstrap';
import bigLogo from '../images/bigLogo.png';

const Registration = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    });

    const [error, setError] = useState('');

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        if (!formData.firstName || !formData.lastName || !formData.email || !formData.password) {
            setError('All fields are required');
            return;
        }
        try {
            const response = await fetch('http://localhost:8080/registration', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });
            if (!response.ok) {
                setError('Email address already exists');
            } else {
                console.log('User registered successfully!');
                // Redirect to login page upon successful registration
                window.location.href = '/login';
            }
        } catch (error) {
            console.error('Registration failed:', error.message);
            setError('Registration failed. Please try again later.');
        }
    };

    return (
        <div>
            <div className="loginDiv">
                <img src={bigLogo} className="imgResponsive d-inline-block align-top" alt="bigLogo" />
                <h2>Sign Up</h2>
                <hr />

                {error && <Alert variant="danger">{error}</Alert>}

                <form onSubmit={handleSubmit}>
                    <div>
                        <input type="text" name="firstName" placeholder="First Name" value={formData.firstName} onChange={handleChange} />
                    </div>
                    <div>
                        <input type="text" name="lastName" placeholder="Last Name" value={formData.lastName} onChange={handleChange} />
                    </div>
                    <div>
                        <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
                    </div>
                    <div>
                        <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} />
                    </div>
                    <Button type="submit" variant="dark">Submit</Button>
                </form>

                <h4><Link to="/login">Back to Login</Link></h4>
            </div>
        </div>
    );
};

export default Registration;
