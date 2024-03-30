import React from "react";

function Classes() {
  return (
    <section className="classes white-background" id="classes">
      <div className="container">
        <h1 className="sections-title">OUR CLASSES</h1>
        <div className="yellow-line" />
        <div className="classes-header">
          <p className="solo-mid-content ">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo libero,
            possimus quasi exercitationem ad delectus. Quaerat ducimus minima
            impedit modi!
          </p>
        </div>
        <div className="classes-buttons-container">
          <div className="classes-buttons classes-buttons-left">
            <button
              className="classes-button active"
              onclick="showContent('yoga')"
            >
              Yoga
            </button>
          </div>
          <div className="classes-buttons classes-buttons-right">
            <button className="classes-button" onclick="showContent('group')">
              Group
            </button>
          </div>
          <div className="classes-buttons classes-buttons-left">
            <button className="classes-button" onclick="showContent('solo')">
              Solo
            </button>
          </div>
          <div className="classes-buttons classes-buttons-right">
            <button
              className="classes-button"
              onclick="showContent('stretching')"
            >
              Stretching
            </button>
          </div>
        </div>
        <div className="classes-content">
          <div className="content-container" id="yogaContent">
            <div className="text-box">
              <h2>Why are your Yoga?</h2>
              <h3>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. In
                magnam quae sequi beatae explicabo numquam unde sapiente at
                minus vitae.
              </h3>
              <h2>When comes Yoga Your Time.</h2>
              <h3> Saturday-Sunday 8:00am - 10:00am</h3>
              <h3> Monday-Tuesday:10:00am - 12:00pm </h3>
              <h3> Wednesday-Friday: 3:00pm - 6:00pm</h3>
            </div>
            <div className="image-box">
              <img
                className="classes-pics"
                id="yogaPic"
                src="images/yoga.jpg"
                alt="Yoga"
              />
            </div>
          </div>
          <div
            className="content-container"
            id="groupContent"
            style={{ display: "none" }}
          >
            <div className="text-box">
              <h2>Why are your Group?</h2>
              <h3>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. In
                magnam quae sequi beatae explicabo numquam unde sapiente at
                minus vitae.
              </h3>
              <h2>When comes Group Your Time.</h2>
              <h3> Saturday-Sunday 8:00am - 10:00am</h3>
              <h3> Monday-Tuesday:10:00am - 12:00pm </h3>
              <h3> Wednesday-Friday: 3:00pm - 6:00pm</h3>
            </div>
            <div className="image-box">
              <img
                className="classes-pics"
                id="groupPic"
                src="images/group.webp"
                alt="Group"
              />
            </div>
          </div>
          <div
            className="content-container"
            id="soloContent"
            style={{ display: "none" }}
          >
            <div className="text-box">
              <h2>Why are your Solo?</h2>
              <h3>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. In
                magnam quae sequi beatae explicabo numquam unde sapiente at
                minus vitae.
              </h3>
              <h2>When comes Solo Your Time.</h2>
              <h3> Saturday-Sunday 8:00am - 10:00am</h3>
              <h3> Monday-Tuesday:10:00am - 12:00pm </h3>
              <h3> Wednesday-Friday: 3:00pm - 6:00pm</h3>
            </div>
            <div className="image-box">
              <img
                className="classes-pics"
                id="soloPic"
                src="images/solo.jpg"
                alt="Solo"
              />
            </div>
          </div>
          <div
            className="content-container"
            id="stretchingContent"
            style={{ display: "none" }}
          >
            <div className="text-box">
              <h2>Why are your Stretching?</h2>
              <h3>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. In
                magnam quae sequi beatae explicabo numquam unde sapiente at
                minus vitae.
              </h3>
              <h2>When comes Stretching Your Time.</h2>
              <h3> Saturday-Sunday 8:00am - 10:00am</h3>
              <h3> Monday-Tuesday:10:00am - 12:00pm </h3>
              <h3> Wednesday-Friday: 3:00pm - 6:00pm</h3>
            </div>
            <div className="image-box">
              <img
                className="classes-pics"
                id="stretchingPic"
                src="images/stret.webp"
                alt="Stretching"
              />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Classes;
