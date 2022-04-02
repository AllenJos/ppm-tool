import logo from "./logo.svg";
import "./App.css";
import Header from "./components/Layout/Header";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AddProject from "./components/Project/AddProject";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Dashboard />}></Route>
          <Route path="/dashboard" element={<Dashboard />}></Route>
          <Route path="/addProject" element={<AddProject />}></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
