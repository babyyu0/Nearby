import "./resources/css/Global.css";

// Modules
import { BrowserRouter, Routes, Route } from "react-router-dom";

// Container
import IndexContainer from "./container/IndexContainer";
import LoginContainer from "./container/user/LoginContainer";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<IndexContainer />} />
        <Route path="/trip" element={<IndexContainer />} />
        <Route path="/login" element={<LoginContainer />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
