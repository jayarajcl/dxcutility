import React from "react";
import { useState } from "react";
import Navbar from "../components/navbar";
import Sidebar from "../components/sidebar";
//import pdfToWordService from "../services/pdfToWordService";

const FileComparison = () => {

  //Upload pdf file
  const [files, setFiles] = useState("");
  //for displaying response message
  const [fileUploadResponse, setFileUploadResponse] = useState(null);
  //base end point url
  const FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:8080/fileCompare";

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
      method: "POST",
      body: formData,
    };
    fetch(FILE_UPLOAD_BASE_ENDPOINT, requestOptions)
      .then(async (response) => {
        const isJson = response.headers
          .get("content-type")
          ?.includes("application/json");
        const data = isJson && (await response.json());
        console.log("file", response);

        // check for error response
        if (!response.ok) {
          // get error message
          const error = (data && data.message) || response.status;
          setFileUploadResponse(data.message);
          return Promise.reject(error);
        }

        console.log("Data msg", data.message);
        setFileUploadResponse(data.message);
      })
      .catch((error) => {
        console.error("Error while uploading file!", error);
      });
  };

  return (
    <>
      <Navbar />
      <Sidebar/>
      <div class="col main pt-5 mt-3">
        {/* <i class="fa-solid fa-plus fa-2x d-inline mr-2"></i> */}
        <small>
          <h1 class="d-inline">Text File Comparison</h1>
          <div class="content-container">
            <div class="container-fluid">
              <div class="card shadow-sm mb-5 mt-3">
                <div className="card-body">
                  <form onSubmit={fileSubmitHandler}>
                    <div class="form-group mt-3">
                      <label>Upload Text Files</label>
                      <input
                        type="file"
                        accept="text/plain"
                        class="form-control shadow-sm"
                        placeholder="Upload Excel Sheet"
                        multiple
                        onChange={uploadFileHandler}
                      ></input>
                    </div>
                    <button type="submit" class="btn btn-success mt-3">
                      Upload Files
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

export default FileComparison;
