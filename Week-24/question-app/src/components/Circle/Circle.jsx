import React from "react";
import "./Circle.Style.css";

const Circle = ({
  r,
  circumference,
  strokeDasharray,
  strokeDashoffset,
  secondsLeft,
}) => {
  return (
    <div className="circle">
      <svg width="200" height="200">
        <circle
          className="timer-circle"
          cx="100"
          cy="100"
          r={r}
          fill="none"
          stroke="black"
          strokeWidth="10"
        ></circle>
        <circle
          className="timer-bar"
          cx="100"
          cy="100"
          r={r}
          fill="none"
          stroke="red"
          strokeWidth="10"
          strokeDasharray={strokeDasharray}
          style={{ strokeDashoffset: strokeDashoffset }}
        ></circle>
        <text x="100" y="100" textAnchor="middle" className="timer-text">
          {secondsLeft < 10 ? secondsLeft : secondsLeft.toString()}
        </text>
      </svg>
    </div>
  );
};

export default Circle;
