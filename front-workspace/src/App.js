// Modules
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Toaster } from "react-hot-toast";

// Container
import IndexContainer from "./container/IndexContainer";
import LoginContainer from "./container/member/LoginContainer";
import RegisterContainer from "./container/member/RegisterContainer";
import AttractionContainer from "./container/attraction/AttractionContainer";

// Styles
import "./resources/css/common/Global.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginContainer />} />
        <Route path="/register" element={<RegisterContainer />} />
        <Route path="/" element={<IndexContainer />} />
        <Route path="/attraction" element={<AttractionContainer />} />
      </Routes>
      <div><Toaster/></div>
    </BrowserRouter>
  );
}

export default App;
