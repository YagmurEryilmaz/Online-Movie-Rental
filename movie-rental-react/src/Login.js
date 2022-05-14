import { useState } from "react";
import "./css/Login.css";
import logo from "./img/tickflixLogo.png"
import { Link, Navigate } from "react-router-dom";
import axios from "axios";
import { connect } from "react-redux";

const Login = ({login_account, loggedIn}) => {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");


	


	const validateEmail = (email) => {
		return String(email)
			.toLowerCase()
			.match(
				/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
			);
	};
	const login = (e) =>{
		e.preventDefault();
		if (validateEmail(email) && password != ""){





			axios.post("http://127.0.0.1:8080/api/v1/auth/signin",
				 {
					email: email,
				password:password
			}).then((response)=>{
				console.log(response.data)
					if(response.data!=null){
						if(response.data.success == true){
							console.log(email, password);
							login_account(response.data)

							window.alert("Logged In")

						}
					}

				}).catch((error => {console.log(error); window.alert("DB Err")}))
			
		}
		else if(!validateEmail(email)){
			window.alert("Enter a valid email");
		}
		else if(password == ""){
			window.alert("Enter a password")
		}
	}
	return(
		<div className = "mainCont  d-flex justify-content-center align-items-center">
			<div className=" row loginInfo d-flex align-items-center">
				
				<div className=" col-12  col-lg-6 d-flex justify-content-center align-items-center">
						<img src={logo} className="logoCont"></img>
				</div>
					
				<div className="col-12 col-md-6  align-center ">
					
					<div className="row-6 pb-4 d-block">
							<p className="loginTexts text-center">Login</p>
						</div>
						<div className="row-6 d-block">
							<form >
								<div className="form-group row py-4 inputText">
								<label for="inputEmail" className="col-sm-3 col-form-label labelText">Email:</label>
									<div className= "col-sm-9">
									<input type="email" className="form-control" id="inputEmail" placeholder="Email" onChange={(e) => setEmail(e.target.value)}/>
									</div>
								</div>
							<div className="form-group row py-4 inputText">
									<label for="inputPass" className="col-sm-5 col-form-label labelText">Password:</label>
									<div className="col-sm-7">
										<input type="password" className="form-control" id="inputPass" placeholder="Password" onChange = {(e) => setPassword(e.target.value)}/>
									</div>
								</div>
							<div className="row py-4 text-underline text-right signupText">
								<Link className="row py-4 text-underline text-right signupText" to='/signup'>Don't have an account?</Link>
							</div>
							<Link to = '/home'>
								<button type="submit" className="btn btn-outline-warning btn-lg" onClick = {(e) => login(e)}>Sign in</button>
							</Link>
							{loggedIn ? (<Navigate to="/home" />) : (null)}

							</form>
							
						</div>
					

				</div>
				

			</div>
			
		</div>
	)
}
const mapDispatchToProps = (dispatch) => {
	return {
		login_account: (payload) => dispatch({type: "LOGIN", payload: {...payload}}),
	}
}
const mapStateToProps = (state) => {
	return {loggedIn: state.loggedIn}
}
export default connect(mapStateToProps, mapDispatchToProps)(Login);