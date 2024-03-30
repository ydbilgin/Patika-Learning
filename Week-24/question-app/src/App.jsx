import React, { useState, useEffect } from "react";
import "./App.css";
import Home from "./components/Home/Home";
import Question from "./components/Questions/Question";
import QuestionInfoBox from "./components/QuestionInfoBox/QuestionInfo";
import Result from "./components/Results/Result";
import QuestionAnswer from "./components/QuestionAnswer/QuestionAnswer";
import { questions } from "./data";
import SkipQuestion from "./components/SkipQuestion/SkipQuestion";
import Circle from "./components/Circle/Circle";
import Media from "./components/Media/Media";

function App() {
  const timerEachQuestion = 30;
  const optionTimer = 10000; //ms
  const maxQuestionsPerPage = 5; // QuestionInfo.jsx maximum number of tiles per page
  const [totalQuestions, setTotalQuestions] = useState(questions.length);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [correctAnswers, setCorrectAnswers] = useState(0);
  const [wrongAnswers, setWrongAnswers] = useState(0);
  const [emptyAnswers, setEmptyAnswers] = useState(0);
  const [secondsLeft, setSecondsLeft] = useState(timerEachQuestion);
  const [testFinished, setTestFinished] = useState(false);
  const [questionNumber, setQuestionNumber] = useState(1);
  const [answerStatus, setAnswerStatus] = useState([]);
  const [userAnswers, setUserAnswers] = useState([]);
  const [pi, setPi] = useState(Math.PI);
  const [r, setR] = useState(80);
  const [circumference, setCircumference] = useState(2 * Math.PI * r);
  const [strokeDasharray, setStrokeDasharray] = useState(`${circumference}px`);
  const [strokeDashoffset, setStrokeDashoffset] = useState(circumference);
  const [lastQuestionStatus, setLastQuestionStatus] = useState(null);
  const [isTestStarted, setIsTestStarted] = useState(false);
  const [selectedQuestion, setSelectedQuestion] = useState(null);
  const [selectedQuestionIndex, setSelectedQuestionIndex] = useState(null);

  const [isQuestionAnswerOpen, setIsQuestionAnswerOpen] = useState(false);

  const handleStartTest = () => {
    setIsTestStarted(true);
  };

  useEffect(() => {
    const timer = setInterval(() => {
      if (secondsLeft > 0 && !testFinished) {
        setSecondsLeft((prevSeconds) => prevSeconds - 1);
        setStrokeDashoffset(
          (prevOffset) => prevOffset - circumference / timerEachQuestion
        );
      } else {
        if (currentQuestionIndex === questions.length - 1 && !testFinished) {
          setEmptyAnswers((prevEmpty) => prevEmpty + 1);
          const updatedUserAnswers = [...userAnswers];
          updatedUserAnswers[currentQuestionIndex] = "Boş Bıraktınız!";
          setUserAnswers(updatedUserAnswers);
        }
        if (currentQuestionIndex < questions.length - 1 && !testFinished) {
          setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
          setEmptyAnswers((prevEmpty) => prevEmpty + 1);
          const updatedUserAnswers = [...userAnswers];
          updatedUserAnswers[currentQuestionIndex] = "Boş Bıraktınız!";
          setUserAnswers(updatedUserAnswers);
          setQuestionNumber((prevNumber) => prevNumber + 1);
          setAnswerStatus((prevStatus) => [...prevStatus, "empty"]);
        } else {
          setTestFinished(true);
          clearInterval(timer);
          setLastQuestionStatus(answerStatus[questions.length - 1]);
        }

        setSecondsLeft(timerEachQuestion);
        setStrokeDashoffset(circumference);
      }
    }, 1000);

    return () => clearInterval(timer);
  }, [
    currentQuestionIndex,
    correctAnswers,
    wrongAnswers,
    emptyAnswers,
    secondsLeft,
    testFinished,
    answerStatus,
    circumference,
    timerEachQuestion,
  ]);
  const handleAnswer = (isCorrect, selectedOption) => {
    console.log("Selected Option:", selectedOption);

    const updatedUserAnswers = [...userAnswers];

    updatedUserAnswers[currentQuestionIndex] = selectedOption;
    console.log(selectedQuestion);

    setUserAnswers(updatedUserAnswers);
    console.log(updatedUserAnswers[currentQuestionIndex]);
    console.log(updatedUserAnswers);

    if (isCorrect) {
      setCorrectAnswers((prevCount) => prevCount + 1);
      setAnswerStatus((prevStatus) => [...prevStatus, "correct"]);
    } else {
      setWrongAnswers((prevCount) => prevCount + 1);
      setAnswerStatus((prevStatus) => [...prevStatus, "wrong"]);
    }

    if (currentQuestionIndex < questions.length - 1 && !testFinished) {
      setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
      setQuestionNumber((prevNumber) => prevNumber + 1);
    } else {
      setTestFinished(true);
    }

    setSecondsLeft(timerEachQuestion);
    setStrokeDashoffset(circumference);
    setSelectedQuestion(selectedQuestion);
  };

  const handleRetry = () => {
    setCurrentQuestionIndex(0);
    setCorrectAnswers(0);
    setWrongAnswers(0);
    setEmptyAnswers(0);
    setSecondsLeft(timerEachQuestion);
    setTestFinished(false);
    setStrokeDashoffset(circumference);
    setQuestionNumber(1);
    setAnswerStatus([]);
    setLastQuestionStatus(null);
    setIsTestStarted(false);
    setSelectedQuestion(null);
  };

  const handleSkipQuestion = () => {
    const updatedUserAnswers = [...userAnswers];
    updatedUserAnswers[currentQuestionIndex] = "Boş Bıraktınız!";
    console.log(updatedUserAnswers);

    setUserAnswers(updatedUserAnswers);

    if (currentQuestionIndex < questions.length - 1 && !testFinished) {
      setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
      setQuestionNumber((prevNumber) => prevNumber + 1);
      setEmptyAnswers((prevEmpty) => prevEmpty + 1);
      setAnswerStatus((prevStatus) => [...prevStatus, "empty"]);
      setSecondsLeft(timerEachQuestion);
      setStrokeDashoffset(circumference);
    } else {
      setEmptyAnswers((prevEmpty) => prevEmpty + 1);
      setTestFinished(true);
    }
  };

  const goToPreviousPage = () => {
    const newIndex = currentQuestionIndex - maxQuestionsPerPage;
    const newPageIndex = newIndex < 0 ? 0 : newIndex;
    setCurrentQuestionIndex(newPageIndex);
  };

  const goToNextPage = () => {
    const newIndex = currentQuestionIndex + maxQuestionsPerPage;
    const lastIndex = totalQuestions - 1;
    const newPageIndex = Math.min(newIndex, lastIndex);
    setCurrentQuestionIndex(newPageIndex);
  };

  const openQuestionAnswer = (questionIndex) => {
    setSelectedQuestion(questions[questionIndex]);
    setSelectedQuestionIndex(questionIndex);
    setIsQuestionAnswerOpen(true);
  };

  return (
    <div className="container">
      <header className="header"></header>
      {!isTestStarted && (
        <Home
          onStartTest={handleStartTest}
          totalQuestions={totalQuestions}
          timerEachQuestion={timerEachQuestion}
          optionTimer={optionTimer}
        />
      )}
      {isTestStarted && (
        <div className="app-container">
          <div className="left-container">
            {!testFinished && (
              <div className="top-container">
                <Circle
                  r={r}
                  circumference={2 * pi * r}
                  strokeDasharray={strokeDasharray}
                  strokeDashoffset={strokeDashoffset}
                  secondsLeft={secondsLeft}
                />
                <Media media={questions[currentQuestionIndex].media} />
              </div>
            )}

            {!testFinished ? (
              <Question
                key={currentQuestionIndex}
                questionNumber={currentQuestionIndex + 1}
                question={questions[currentQuestionIndex].question}
                options={questions[currentQuestionIndex].options}
                answer={questions[currentQuestionIndex].answer}
                media={questions[currentQuestionIndex].media}
                onAnswer={(isCorrect, selectedOption) =>
                  handleAnswer(isCorrect, selectedOption)
                }
                onSelect={() =>
                  setSelectedQuestion(questions[currentQuestionIndex])
                }
                optionTimer={optionTimer}
              />
            ) : (
              <div className="result-top-container">
                <Result
                  correctAnswers={correctAnswers}
                  wrongAnswers={wrongAnswers}
                  emptyAnswers={emptyAnswers}
                  handleRetry={handleRetry}
                />
                {selectedQuestion && (
                  <QuestionAnswer
                    question={selectedQuestion.question}
                    correctAnswer={selectedQuestion.answer}
                    userAnswer={userAnswers}
                    media={selectedQuestion.media}
                    selectedQuestion={selectedQuestionIndex}
                  />
                )}
              </div>
            )}
          </div>

          <QuestionInfoBox
            currentQuestionIndex={currentQuestionIndex}
            maxQuestionsPerPage={maxQuestionsPerPage}
            totalQuestions={totalQuestions}
            goToPreviousPage={goToPreviousPage}
            goToNextPage={goToNextPage}
            testFinished={testFinished}
            answerStatus={answerStatus}
            lastQuestionStatus={lastQuestionStatus}
            handleQuestionClick={openQuestionAnswer}
          />
        </div>
      )}
      <footer className="footer">
        {!testFinished && (
          <div className="skip-question-container">
            <SkipQuestion
              onSkipQuestion={handleSkipQuestion}
              isTestStarted={isTestStarted}
              testFinished={testFinished}
            />
          </div>
        )}
      </footer>
    </div>
  );
}

export default App;
