import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './components/home';
import PdfToWord from './mainComponents/pdfToWord';
import FileComparison from './mainComponents/fileComparison';
import Compress from './mainComponents/compress';
import PdfToExcel from './mainComponents/pdfToExcel';

function App() {

  return (
    <>
      <BrowserRouter>
        <div class="container-fluid" id="main">
          <div class="row row-offcanvas row-offcanvas-left">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/pdftoword" element={<PdfToWord/>}/>
              <Route path="/fileCompare" element={<FileComparison/>}/>
              <Route path="/compress" element={<Compress/>}/>
              <Route path="/pdftoexcel" element={<PdfToExcel/>}/>
            </Routes>
          </div>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
