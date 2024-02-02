import React, { useState } from 'react';
import axios from 'axios';

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
      await axios.post('http://localhost:8080/registration', formData);
      console.log('User registered successfully!');
      // Redirect or show a success message
    } catch (error) {
      console.error('Registration failed:', error);
      // Handle error, show error message, etc.
    }
  };

  return (
  <div>
    <div class="loginDiv">
        <h1>Work In Progress</h1>
        <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <input type="text" name="firstName" placeholder="First Name"  value={formData.firstName} onChange={handleChange} />
        </div>
        <div>
          <input type="text" name="lastName" placeholder="Last Name" value={formData.LastName} onChange={handleChange} />
        </div>
        <div>
          <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
        </div>
        <div>
          <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
    </div>
  );
};

export default Registration;
