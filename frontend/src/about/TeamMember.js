import React from 'react';

const TeamMember = ({ name, position, bio, image }) => {
  return (
    <div className="teamMember">
      <img src={image} alt={name} className="teamMemberPic" />
      <h2>{name}</h2>
      <h3>{position}</h3>
    </div>
  );
};

export default TeamMember;
