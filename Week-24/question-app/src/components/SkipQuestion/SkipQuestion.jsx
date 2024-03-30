import React from "react";
import "./SkipQuestion.Style.css";

function SkipQuestion({ onSkipQuestion, isTestStarted, testFinished }) {
  if (isTestStarted && !testFinished) {
    return (
      <button className="skip-question-button" onClick={onSkipQuestion}>
        Soruyu Geç
      </button>
    );
  } else {
    return null;
  }
}

export default SkipQuestion;
