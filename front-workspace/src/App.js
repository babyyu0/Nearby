import "./resources/css/Global.css";

// Modules
import { BrowserRouter, Routes, Route } from "react-router-dom";

// Container
import IndexContainer from "./container/IndexContainer";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<IndexContainer />} />
        <Route path="/trip" element={<IndexContainer />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
