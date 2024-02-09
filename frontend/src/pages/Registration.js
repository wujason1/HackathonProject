import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

const Registration = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  });

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/registration', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      });
      if (response.ok) {
        console.log('User registered successfully!');
        // Redirect to login page upon successful registration
        window.location.href = '/login';
      } else {
        console.error('Registration failed:', response.statusText);
      }
    } catch (error) {
      console.error('Registration failed:', error.message);
    }
  };

  return (
    <div>
      <div className="loginDiv">
        <h1>Work In Progress</h1>
        <h2>Sign Up</h2>
        <hr />
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
          <Button type="submit" variant="dark"> Submit </Button>
        </form>
        <h4><Link to="/login">Back to Login</Link></h4>
      </div>
    </div>
  );
};

export default Registration;
