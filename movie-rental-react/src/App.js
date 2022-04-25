
import './App.css';
import Login from './Login';
import Signup from './Signup';
import {BrowserRouter, Router, Route, Routes, Switch, Navigate, useHis} from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path = '/' element = {<Login/>}></Route>
        <Route path = '/signup' element = {<Signup/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
