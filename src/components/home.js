import React, { useState } from "react";
import '../css/home.css';
import Navbar from './navbar';
import Sidebar from "./sidebar";
//import Carousel from 'react-bootstrap/Carousel';

const home = () => {

  return (
      <section>
        <Navbar/>
        <Sidebar/>
          
      </section>
  );
};

export default home;
