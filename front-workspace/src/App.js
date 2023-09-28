// Modules
import { BrowserRouter, Routes, Route } from "react-router-dom";

// Container
import IndexContainer from "./container/IndexContainer";
import LoginContainer from "./container/member/LoginContainer";
import RegisterContainer from "./container/member/RegisterContainer";

// Styles
import "./resources/css/common/Global.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginContainer />} />
        <Route path="/register" element={<RegisterContainer />} />
        <Route path="/" element={<IndexContainer />} />
        <Route path="/trip" element={<IndexContainer />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
