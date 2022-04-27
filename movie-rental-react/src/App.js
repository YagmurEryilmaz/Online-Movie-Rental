
import './App.css';
import Login from './Login';
import Signup from './Signup';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import Home from './Home';
import {BrowserRouter, Router, Route, Routes, Switch, Navigate, useHis} from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path = '/' element = {<Login/>}></Route>
        <Route exact path = '/signup' element = {<Signup/>}></Route>
        <Route exact path = '/home' element = {<Home/>}></Route>
      </Routes>
    </BrowserRouter>
    
    
  );
}

export default App;
