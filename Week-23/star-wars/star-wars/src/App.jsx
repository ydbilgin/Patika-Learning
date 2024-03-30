import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [starships, setStarships] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [selectedStarship, setSelectedStarship] = useState(null);
  const [modalVisible, setModalVisible] = useState(false);
  const [nextPage, setNextPage] = useState(null);

  useEffect(() => {
    fetchStarships();
  }, []);

  const fetchStarships = async (url = "https://swapi.dev/api/starships/") => {
    try {
      const response = await axios.get(url);
      setStarships((prevStarships) => [
        ...prevStarships,
        ...response.data.results,
      ]);
      setNextPage(response.data.next);
    } catch (error) {
      console.error("Error fetching starships:", error);
    }
  };

  const handleSearch = () => {
    if (searchTerm.trim() === "") {
      setSearchResults([]);
      setSelectedStarship(null);
    } else {
      const results = starships.filter(
        (starship) =>
          starship.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
          (starship.model &&
            starship.model.toLowerCase().includes(searchTerm.toLowerCase()))
      );
      setSearchResults(results);
      setSelectedStarship(null);
    }
  };

  const handleStarshipClick = (starship) => {
    setSelectedStarship(starship);
    setModalVisible(true);
  };

  const handleCloseModal = () => {
    setModalVisible(false);
  };

  const handleLoadMore = () => {
    if (nextPage) {
      fetchStarships(nextPage);
    }
  };

  return (
    <div className="container">
      <div className="header">
        {/* <h1>Starships List</h1> */}
        <img
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Star_Wars_Logo.svg/1200px-Star_Wars_Logo.svg.png"
          style={{ height: "150px", width: "400px", marginBottom: "20px" }}
        />
        <div className="search-container">
          <p style={{ color: "yellow" }}>Name/Model </p>
          <input
            type="text"
            placeholder="Search starships..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
          <button onClick={handleSearch} className="search-button">
            Search
          </button>
        </div>
      </div>
      <ul className="starship-list">
        {searchResults.length > 0
          ? searchResults.map((starship, index) => (
              <li
                key={index}
                className="starship-item"
                onClick={() => handleStarshipClick(starship)}
              >
                <img
                  src="https://img1.cgtrader.com/items/695727/2b2d3a2bea/large/star-wars-falcon-ship-3d-model-fbx-blend.jpg"
                  className="starship-image"
                  alt={starship.name}
                />
                <br />
                <strong>Name:</strong> {starship.name}
                <br />
                <strong>Model:</strong> {starship.model}
                <br />
                <strong>Hyperdrive Rating:</strong> {starship.hyperdrive_rating}
                <br />
                <br />
              </li>
            ))
          : starships.map((starship, index) => (
              <li
                key={index}
                className="starship-item"
                onClick={() => handleStarshipClick(starship)}
              >
                <img
                  src="https://img1.cgtrader.com/items/695727/2b2d3a2bea/large/star-wars-falcon-ship-3d-model-fbx-blend.jpg"
                  className="starship-image"
                  alt={starship.name}
                />
                <br />
                <strong>Name:</strong> {starship.name}
                <br />
                <strong>Model:</strong> {starship.model}
                <br />
                <strong>Hyperdrive Rating:</strong> {starship.hyperdrive_rating}
                <br />
                <br />
              </li>
            ))}
      </ul>
      <div className="load-more-container">
        <button onClick={handleLoadMore} className="load-more-button">
          Load More Ships
        </button>
      </div>
      {selectedStarship && modalVisible && (
        <div className="modal-container">
          <div className="modal-content">
            <h2>{selectedStarship.name}</h2>
            <img
              src="https://img1.cgtrader.com/items/695727/2b2d3a2bea/large/star-wars-falcon-ship-3d-model-fbx-blend.jpg"
              className="starship-image"
              alt={selectedStarship.name}
            />
            <p>
              <strong>Model:</strong> {selectedStarship.model}
            </p>
            <p>
              <strong>Hyperdrive Rating:</strong>{" "}
              {selectedStarship.hyperdrive_rating}
            </p>
            <p>
              <strong>Passengers:</strong> {selectedStarship.passengers}
            </p>
            <p>
              <strong>Max atmosphering speed:</strong>{" "}
              {selectedStarship.max_atmosphering_speed}
            </p>
            <p>
              <strong>Manufacturer:</strong> {selectedStarship.manufacturer}
            </p>
            <p>
              <strong>Crew:</strong> {selectedStarship.crew}
            </p>
            <p>
              <strong>Cargo capacity:</strong> {selectedStarship.cargo_capacity}
            </p>
            <button onClick={handleCloseModal} className="close-button">
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
