// QuestionInfoBox.jsx

import React, { useState, useEffect } from "react";
import "./QuestionInfo.Style.css";

const QuestionInfoBox = ({
  currentQuestionIndex,
  maxQuestionsPerPage,
  totalQuestions,
  testFinished,
  answerStatus,
  lastQuestionStatus,
  handleQuestionClick,
}) => {
  const [currentPageIndex, setCurrentPageIndex] = useState(0);

  const totalPages = Math.ceil(totalQuestions / maxQuestionsPerPage);

  useEffect(() => {
    const newIndex = Math.floor(currentQuestionIndex / maxQuestionsPerPage);
    setCurrentPageIndex(newIndex);
  }, [currentQuestionIndex, maxQuestionsPerPage]);

  const goToPreviousPage = () => {
    setCurrentPageIndex((prevIndex) => Math.max(prevIndex - 1, 0));
  };

  const goToNextPage = () => {
    setCurrentPageIndex((prevIndex) => Math.min(prevIndex + 1, totalPages - 1));
  };

  return (
    <div className="question-info-top-box">
      <div className="question-info-box">
        {Array.from(Array(maxQuestionsPerPage).keys()).map((index) => {
          const questionIndex = currentPageIndex * maxQuestionsPerPage + index;
          if (questionIndex < totalQuestions) {
            let colorClass = "";
            if (questionIndex === currentQuestionIndex) {
              colorClass = "yellow";
            } else if (testFinished) {
              const userAnswer = answerStatus[questionIndex];
              colorClass =
                userAnswer === "correct"
                  ? "green"
                  : userAnswer === "wrong"
                  ? "red"
                  : userAnswer === "empty"
                  ? "yellow"
                  : "turquoise";
            } else {
              colorClass =
                questionIndex < currentQuestionIndex ? "blue" : "turquoise";
            }
            if (
              questionIndex === totalQuestions - 1 &&
              lastQuestionStatus !== null
            ) {
              colorClass =
                lastQuestionStatus === "correct"
                  ? "green"
                  : lastQuestionStatus === "wrong"
                  ? "red"
                  : "yellow";
            }
            return (
              <div
                key={index}
                className={`single-question-box ${colorClass}`}
                onClick={() => handleQuestionClick(questionIndex)}
              >
                {questionIndex + 1}
              </div>
            );
          }
          return null;
        })}
      </div>
      <div className="question-info-ba">
        <div className="question-info-before">
          <button
            className="question-box-before"
            onClick={goToPreviousPage}
            disabled={currentPageIndex === 0}
          >
            {"<"}
          </button>
        </div>
        <div className="question-info-after">
          <button
            className="question-box-after"
            onClick={goToNextPage}
            disabled={currentPageIndex === totalPages - 1}
          >
            {">"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default QuestionInfoBox;
