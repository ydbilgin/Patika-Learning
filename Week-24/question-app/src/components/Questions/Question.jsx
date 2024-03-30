import React, { useState, useEffect } from "react";
import "./Question.Style.css";
import Options from "../Options/Options";

const Question = ({
  questionNumber,
  question,
  options,
  answer,
  media,
  onAnswer,
  optionTimer,
}) => {
  const [selectedOption, setSelectedOption] = useState(null);
  const [showOptions, setShowOptions] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowOptions(true);
    }, optionTimer);

    return () => clearTimeout(timer);
  }, [optionTimer]);

  const handleOptionClick = (option) => {
    if (!showOptions) return;

    setSelectedOption(option);
    onAnswer(option === answer, option);
  };

  return (
    <div className="question-box">
      <div className="question-div">
        <div className="question-number-box">
          <div className="question-number-circle">{questionNumber}</div>
        </div>
        <h1 className="question">{question}</h1>
      </div>

      <Options
        options={options}
        selectedOption={selectedOption}
        handleOptionClick={handleOptionClick}
        showOptions={showOptions}
      />
    </div>
  );
};

export default Question;
