import './App.css';
import Login from './Login';
import Signup from './Signup';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import Home from './Home';
import Profile from './Profile';
import Rent from './Rent';
import stateProto from './redux/state';
import {connect} from "react-redux"
import EmployeeHomepage from './EmployeeHomePage';
import EmployeeProfilePage from './EmployeeProfilePage';
import {BrowserRouter, Router, Route, Routes, Switch, Navigate, useHis} from 'react-router-dom';

function App({loggedIn, role}) {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path = '/' element = {<Login/>}></Route>
        <Route exact path = '/signup' element = {<Signup/>}></Route>
        <Route exact path = '/home' element = {!loggedIn ? <Navigate to ="/"/> :(role === "customer") ? <Home/> : <EmployeeHomepage/>}></Route>
        <Route exact path = '/profile' element = {!loggedIn ? <Navigate to ="/"/> : (role ==="customer") ? <Profile/>:<EmployeeProfilePage/>}></Route>
        <Route exact path = '/rent' element = {!loggedIn ? <Navigate to ="/"/> : <Rent/>}></Route>
      </Routes>
    </BrowserRouter>
    
    
  );
}
const mapStateToProps = state =>{
    return{
      loggedIn: state.loggedIn,
      role: state.accountType
    };
}

export default connect(mapStateToProps)(App);
