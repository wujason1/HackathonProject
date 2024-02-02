import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../index.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', { email, password });
      console.log(response.data); // Assuming backend returns user data upon successful login
      // Redirect or set login state based on response
    } catch (error) {
      console.error('Login failed:', error);
      // Handle error, show error message to user, etc.
    }
  };

  return (
  <div>
    <div class="missionStatement">
        <h1>Work In Progress</h1>
        <h1> Gamify Your Job Application Process! </h1>
    </div>
    <div class="loginDiv">
        <form onSubmit={handleSubmit}>
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button type="submit">Login</button>
        </form>
    <h4>Don't have an account? <Link to="/registration">Create one!</Link></h4>
    </div>
  </div>
  );
};

export default Login;