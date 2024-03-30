import React from "react";

function Navbar() {
  return (
    <body>
      <div className="navbar-div-react">
        <header className="navbar" id="navbar">
          <div className="container">
            <div className="navbar-div">
              <div className="navbar-logo">
                <img className="logo" src="images/logo.png" alt="top-logo" />
              </div>
              <div className="menu">
                <a href>Home</a>
                <a href="#classes">Classes</a>
                <a href="#trainers">Trainer</a>
                <a href="#review">Review</a>
                <a href="#contact-us">Contact</a>
                <a className="join-us" href>
                  JOIN US
                </a>
              </div>
              <div className="mobile-menu-icon">
                <div className="bar" />
                <div className="bar" />
                <div className="bar" />
              </div>
            </div>
          </div>
        </header>
        <section className="header-content">
          <div className="container">
            <div className="powerfull-content">
              <h2 className="powerfull">POWERFULL</h2>
              <div className="title-h1">
                <h1>
                  Group <br />
                  Practice <br />
                  With Trainer
                </h1>
              </div>
              <div className="desc-box">
                <h2>
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint
                  odio autem totam officiis sequi iusto dignissimos numquam
                  distinctio, reiciendis sapiente suscipit et. Autem a excepturi
                  ipsa tenetur reiciendis totam provident aperiam fugit saepe
                  ratione! Nemo cumque voluptatum labore hic, rem quam beatae
                  laboriosam voluptatibus facilis harum earum doloremque natus
                  culpa!
                </h2>
              </div>
              <div className="header-buttons">
                <a href="#sign-up" className="sign-up">
                  Sing Up
                </a>
                <a href="#details" className="details">
                  Details
                </a>
              </div>
            </div>
          </div>
        </section>
      </div>
    </body>
  );
}

export default Navbar;
