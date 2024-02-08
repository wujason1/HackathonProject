import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import '../index.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const userLogin = {
      email: email,
      password: password
  };

//  const handleSubmit = async (e) => {
//    e.preventDefault();
//    try {
//      const response = await axios.post('http://localhost:8080/login', { email, password });
//      console.log(response.data); // Assuming backend returns user data upon successful login
//      // Redirect or set login state based on response
//    } catch (error) {
//      console.error('Login failed:', error);
//      // Handle error, show error message to user, etc.
//    }
//  };

//    function handleSubmit(e) {
//        e.preventDefault();
////        axios.post('http://localhost:8080/login', userLogin);
//    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        console.log(userLogin)
        // something about data being sent as request body and not request parameters
        try{
            let response = await fetch('http://localhost:8080/login/' + email + "/" + password, {
//            let response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                'Accept' : 'application/json',
                'Content-Type':'application/json',
                },
//                body: JSON.stringify(userLogin),
            });
            const responseJson = await response.json();
            console.log(responseJson);
        } catch (e) {}
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