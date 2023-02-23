import httpClient from './baseUrl';

function uploadPDF() {
    return httpClient.post("/pdftoword");
  }
  
  export default {
    uploadPDF
  };