import React, { useState } from 'react';
import axios from 'axios';

const Registration = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      await axios.post('/registration', formData); // Assuming your backend is running on the same host
      console.log('User registered successfully!');
      // Redirect or show a success message
    } catch (error) {
      console.error('Registration failed:', error);
      // Handle error, show error message, etc.
    }
  };

  return (
  <div>
    <div class="companyName">
        <h1>Work In Progress</h1>
    </div>
    <div class="loginDiv">
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <input type="email" name="email" placeholder="Set Email"  value={formData.email} onChange={handleChange} />
        </div>
        <div>
          <input type="password" name="password" placeholder="Set Password" value={formData.password} onChange={handleChange} />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
    </div>
  );
};

export default Registration;
