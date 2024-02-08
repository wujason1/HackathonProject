import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import '../index.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

    const handleSubmit = async(e) => {
        e.preventDefault();
        try{
            const response = await fetch('http://localhost:8080/login?email=' + email + "&password=" + password, {
                method: 'POST',
                headers: {
                'Accept' : 'application/json',
                'Content-Type':'application/json',
                },
            });
          if (response.ok) {
//            console.log('User logged in successfully!');
            // Redirect to about page upon successful registration
            window.location.href = '/';
          } else {
            console.error('Login failed:', response.statusText);
            // add pop up of incorrect login credentials?
          }
        } catch (error) {
            console.error('Login failed:', error.message);
        }
    }

  return (
  <div>
    <div class="loginDiv">
        <h1>Work In Progress</h1>
        <h1> Gamify Your Job Application Process!</h1>
        <hr />
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