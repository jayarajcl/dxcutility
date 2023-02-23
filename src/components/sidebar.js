import React from "react";
const Sidebar = () => {
  return (
    <div
      class="sidebar-offcanvas pl-0"
      id="sidebar"
      role="navigation"
      style={{ backgroundColor: "#e9ecef",width:"18vw",height:"100vh" }}
    >
      <ul class="nav flex-column sticky-top pl-0 pt-5 p-3 mt-3 ">
        <li class="nav-item mb-2 mt-3">
          <a class="nav-link text-secondary" href="/">
            <h5>DXC Utility</h5>
          </a>
        </li>
        <li class="nav-item mb-2 ">
          <a class="nav-link text-secondary" href="/compress">
            <i class="far fa-solid fa-compress font-weight-bold"></i>{" "}
            <span className="ml-3">Compress</span>
          </a>
        </li>
        <li class="nav-item mb-2">
          <a
            class="nav-link text-secondary"
            href="/pdftoword"
            data-toggle="collapse"
            data-target="#submenu1"
          >
            <i class="far fa-solid fa-file-pdf font-weight-bold"></i>{" "}
            <i class="far fa-solid fa-file-word font-weight-bold"></i>
            {" "}
            <span className="ml-1"> PDF to Word</span>
          </a>
        </li>
        <li class="nav-item mb-2">
          <a
            class="nav-link text-secondary"
            href="/pdftoexcel"
          >
            <i class="far fa-solid fa-file-pdf font-weight-bold"></i>{" "}
            <i class="far fa-solid fa-file-excel font-weight-bold"></i>
            {" "}
            <span className="ml-1"> PDF to Excel</span>
          </a>
        </li>
        <li class="nav-item mb-2">
          <a class="nav-link text-secondary" href="/filecompare">
            <i class="fa-solid fa-code-compare font-weight-bold"></i>
            <span className="ml-3">Compare Files</span>
          </a>
        </li>
        <li class="nav-item mb-2">
          <a class="nav-link text-secondary" href="#">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span className="ml-3">Logout</span>
          </a>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
