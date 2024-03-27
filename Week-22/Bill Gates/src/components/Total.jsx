import React from "react";
import { useContext } from "react";
import { AppContext } from "../App";

const TotalWorth = () => {
  const { totalWorth } = useContext(AppContext);

  function formatNumberWithCommas(x) {
    return new Intl.NumberFormat().format(x);
  }

  return (
    <div className="total-worth-container">
      <h2 className="total-worth">${formatNumberWithCommas(totalWorth)}</h2>
    </div>
  );
};

export default TotalWorth;
