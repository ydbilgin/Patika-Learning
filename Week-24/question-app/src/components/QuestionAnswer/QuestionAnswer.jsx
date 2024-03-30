import React from "react";
import Media from "../Media/Media";
import "../Media/Media.Style.css";
import "./QuestionAnswer.Style.css";

const QuestionAnswer = ({
  question,
  correctAnswer,
  userAnswer,
  media,
  selectedQuestion,
}) => {
  const getAnswerText = () => {
    if (userAnswer === null) {
      return "Boş bıraktınız";
    } else if (userAnswer === correctAnswer) {
      return "Cevabınız doğru";
    } else {
      return userAnswer || "";
    }
  };

  return (
    <div className="question-answer-container">
      <div className="question-media-box">
        {media && <Media media={media} />}
      </div>
      <div className="question-answer-top-container">
        <div className="question-text">
          <h2>Soru</h2>
          <p>{question}</p>
        </div>
        <div className="answers">
          <div className="answer correct-answer">
            <h3>Doğru Cevap :</h3>
            <p>{correctAnswer}</p>
          </div>
          <div className="answer user-answer">
            <h3>Senin Cevabın :</h3>
            <p>{userAnswer[selectedQuestion]}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default QuestionAnswer;
