import React from "react";
import "./Home.Style.css";

function Home({ onStartTest, totalQuestions, timerEachQuestion, optionTimer }) {
  return (
    <div className="home-container">
      <h1>Teste Hoş Geldiniz!</h1>
      <p>
        Test <span className="home-content-background"> {totalQuestions} </span>{" "}
        sorudan oluşuyor ve her soru için süreniz
        <span className="home-content-background">
          {" "}
          {timerEachQuestion}
        </span>{" "}
        saniyedir.
      </p>
      <p>
        Seçenekler soru geldikten{" "}
        <span className="home-content-background"> {optionTimer / 1000} </span>{" "}
        saniye sonra gelecektir.
      </p>
      <p>Başarılar</p>
      <button id="start" onClick={onStartTest}>
        Teste Başla
      </button>
    </div>
  );
}

export default Home;
