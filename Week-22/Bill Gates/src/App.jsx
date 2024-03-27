import "./App.css";
import Header from "./components/Header";
import Receipt from "./components/Receipts";
import Total from "./components/Total";
import { useState, createContext } from "react";
import Content from "./components/Content";

export const AppContext = createContext(null);

function App() {
  const [totalWorth, setTotalWorth] = useState(100000000000);

  return (
    <AppContext.Provider value={{ totalWorth, setTotalWorth }}>
      <Header />
      <Total />
      <Content />
      <Receipt />
    </AppContext.Provider>
  );
}

export default App;
