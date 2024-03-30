import React from "react";
import "./Result.Style.css";

const Result = ({
  correctAnswers,
  wrongAnswers,
  emptyAnswers,
  handleRetry,
}) => {
  return (
    <div className="result-container">
      <h2>Test Sonuçları</h2>
      <p>Doğru Cevaplar: {correctAnswers}</p>
      <p>Yanlış Cevaplar: {wrongAnswers}</p>
      <p>Boş bırakılan soru: {emptyAnswers}</p>
      <button className="retry-button" onClick={handleRetry}>
        Tekrar Dene
      </button>
    </div>
  );
};

export default Result;
