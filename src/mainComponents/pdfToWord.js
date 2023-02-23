import React from "react";
import { useState } from "react";
import Navbar from "../components/navbar";
import Sidebar from "../components/sidebar";
//import pdfToWordService from "../services/pdfToWordService";

const PdfToWord = () => {

  //Upload pdf file
  const [files, setFiles] = useState("");
  //for displaying response message
  const [fileUploadResponse, setFileUploadResponse] = useState(null);
  //base end point url
  const FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:9191/pdftoword";

  const uploadFileHandler = (event) => {
    setFiles(event.target.files);
  };

  const fileSubmitHandler = (event) => {
    event.preventDefault();
    setFileUploadResponse(null);

    const formData = new FormData();
    formData.append(`file`, files[0]);
    console.log(files);
    const requestOptions = {
      mode:"no-cors",
      method: "POST",
      body: formData,
    };
    fetch(FILE_UPLOAD_BASE_ENDPOINT+"converter", requestOptions)
      .then(async (response) => {
        console.log("success");
        console.log(JSON.stringify(response))
      })
      .catch((error) => {
        console.error("Error while downloading file!", error);
      });
  };

  return (
    <>
      <Navbar />
      <Sidebar/>
      <div class="col main pt-5 mt-3">
        {/* <i class="fa-solid fa-plus fa-2x d-inline mr-2"></i> */}
        <small>
          <h1 class="d-inline">PDF to Word Converter</h1>
          <div class="content-container">
            <div class="container-fluid">
              <div class="card shadow-sm mb-5 mt-3">
                <div className="card-body">
                  <form onSubmit={fileSubmitHandler}>
                    <div class="form-group mt-3">
                      <label>Upload PDF File</label>
                      <input
                        type="file"
                        accept=".pdf"
                        class="form-control shadow-sm"
                        placeholder="Upload PDF File"
                        multiple
                        onChange={uploadFileHandler}
                      ></input>
                    </div>
                    <button type="submit" class="btn btn-success mt-3">
                      Upload
                    </button>
                    {fileUploadResponse != null && (
                      <p style={{ color: "green" }}>{fileUploadResponse}</p>
                    )}
                  </form>
                </div>
              </div>
            </div>
          </div>
        </small>
      </div>
    </>
  );
};

export default PdfToWord;
