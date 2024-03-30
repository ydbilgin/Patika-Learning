import React from "react";
import "./Options.Style.css";

const Options = ({
  options,
  selectedOption,
  handleOptionClick,
  showOptions,
}) => {
  return (
    showOptions && (
      <ol className="options">
        {options.map((option, index) => (
          <li
            key={index}
            className={`option ${selectedOption === option ? "selected" : ""}`}
            onClick={() => handleOptionClick(option)}
          >
            {option}
          </li>
        ))}
      </ol>
    )
  );
};

export default Options;
