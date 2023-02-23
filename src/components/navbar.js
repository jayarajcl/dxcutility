import React from "react";

export const navbar = () => {
  return (
    <nav class="navbar fixed-top navbar-expand-md navbar-light bg-light mb-3">
      <div class="flex-row d-flex">
        {/* <button
          type="button"
          class="navbar-toggler mr-2 "
          data-toggle="offcanvas"
          title="Toggle responsive left sidebar"
        >
          <span class="navbar-toggler-icon"></span>
        </button> */}
        <a class="navbar-brand" href="/" title="">
          <img
            src={require("../images/dxc_logo_purple.png")}
            class=""
            alt="Dxc logo"
            width={"150vw"}
            height={"30vh"}
          ></img>
        </a>
      </div>
      {/* <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#collapsingNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button> */}
      <div class="navbar-collapse collapse" id="collapsingNavbar">
        <ul class="navbar-nav ml-auto">
          <small>            
            <li class="nav-item">
              <a class="nav-link" href="" data-toggle="collapse">
                Login
              </a>
            </li>
          </small>
          <small>
            <li class="nav-item">
              <a
                class="nav-link"
                href=""
                data-target="#myModal"
                data-toggle="modal"
              >
                Logout
              </a>
            </li>
          </small>

          {/* <li class="nav-item">
            <a class="nav-link waves-effect waves-light text-white">
              <i class="fab fa-google-plus-g"></i>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link waves-effect waves-light text-white">
              <i class="fas fa-envelope-open-text"></i>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link waves-effect waves-light text-white">
              <i class="fas fa-align-justify"></i>
            </a>
          </li> */}
        </ul>
      </div>
    </nav>
  );
};
export default navbar;
