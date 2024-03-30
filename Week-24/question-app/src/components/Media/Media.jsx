import React from "react";
import "./Media.Style.css";

const Media = ({ media }) => {
  return (
    <div className="question-media">
      <img className="question-img" src={`src/assets/images/${media}`} alt="" />
    </div>
  );
};

export default Media;
