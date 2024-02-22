import { createRoot } from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import About from "./about/About";
import Login from "./pages/Login";
import Registration from "./pages/Registration";
import Dashboard from "./pages/Dashboard";
import NoPage from "./pages/NoPage";
import Prizes from "./pages/Prizes"
import MyNavbar from "./components/Navbar";
import { AuthProvider } from './components/AuthContext';

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <MyNavbar />
        <Routes>
          <Route path="/" element={<About />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registration" element={<Registration />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/prizes" element={<Prizes />} />
          <Route path="*" element={<NoPage />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

const root = createRoot(document.getElementById('root'));
root.render(<App />);