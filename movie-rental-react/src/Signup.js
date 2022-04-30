import {useState} from "react";
import { Link } from "react-router-dom";
import "./css/Login.css";
import axios from 'axios'

const Signup = () => {
	const [name, setName] = useState("");
	const [mail,setMail] = useState("");
	const [password, setPassword] = useState("");
	const [birthday, setBirthday] = useState("");
	const [user, setUser] = useState("customer");

	const validateEmail = (mail) => {
		return String(mail)
			.toLowerCase()
			.match(
				/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
			);
	};
	const signup = (e) => {
		e.preventDefault();
		if(validateEmail(mail) && password != "" && name != "" && birthday != ""){
			window.alert("Success");
			console.log(name, mail, password, birthday, user);
			var registerInfo={
				email:mail,
				role:user,
				password:password,
				name:name,
				birthday:birthday

			}
			axios.post("http://127.0.0.1:8080/api/v1/auth/signup",
				registerInfo
			).then((response) => {
				
			}).catch(error => {console.log(error); window.alert("database error")});

		}
		else if(!validateEmail(mail)){
			window.alert("Enter a valid email");
		}
		else if(password == "") {
			window.alert("Enter a password")
		}
		else if(birthday == ""){
			window.alert("Enter a birthday")
		}
		else if(name == ""){
			window.alert("Enter your name")
		}

	}
	return(
		<div className="mainCont  d-flex justify-content-center align-items-center">
			<div className=" row loginInfo d-flex align-items-center justify-content-center">
				<form>
					<div class="form-group row d-flex align-items-center justify-content-center py-4 inputText">
						<label for="inputNameSurname" class="col-sm-3 col-form-label labelText">Name Surname:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="inputNameSurname" placeholder="Name Surname" onChange = {(e) => {setName(e.target.value)}}/>
    					</div>
					</div>
					<div class="form-group row d-flex align-items-center justify-content-center py-4 inputText">
						<label for="inputEmail2" class="col-sm-3 col-form-label labelText">Email:</label>
						<div class="col-sm-7">
							<input type="email" class="form-control" id="inputEmail2" placeholder="Email" onChange = {(e) => {setMail(e.target.value)}}/>
						</div>
					</div>
					<div class="form-group row d-flex align-items-center justify-content-center py-4 inputText">
						<label for="inputPass" class="col-sm-3 col-form-label labelText">Password:</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="inputPass" placeholder="Password" onChange = {(e) => setPassword(e.target.value)}/>
						</div>
					</div>
					<div class="form-group row d-flex align-items-center justify-content-center py-4 inputText">
						<label for="inputBd" class="col-sm-3 col-form-label labelText">Birthday:</label>
						<div class="col-sm-7">
							<input type="date" class="form-control" id="inputBd" placeholder="Birthday" onChange = {(e) => setBirthday(e.target.value)}/>
						</div>
					</div>
					<div class="form-group row d-flex align-items-center justify-content-center py-2 inputText">
						<label for="inputUserType" class="col-sm-3 col-form-label labelText">User Type</label>
						<div class="col-sm-7">
							<input type="radio" class="form-check-input" id="inputCustomer" name="userType" value="customer" onClick={() => setUser("customer")} defaultChecked/>
							<label class="form-check-label px-2" for="inputCustomer">
								Customer
							</label>
							<input type="radio" class="form-check-input" id="inputAdmin" name = "userType" value = "admin" onClick = {() => setUser("admin")}/>
							<label class="form-check-label px-2 " for="inputAdmin">
								Admin
							</label>
						</div>
					</div>
					<div className="row d-flex align-items-center justify-content-center py-4 text-underline text-right signupText">
						<Link className="row py-2 d-flex align-items-center justify-content-center text-underline text-right signupText" to='/'>Back to Login</Link>
					</div>
					<div className= "row d-flex align-items-center justify-content-center">
						<div className="col-12 text-right d-flex align-items-center justify-content-center">
							<button type="submit" className="btn btn-outline-warning btn-lg" onClick = {(e) => signup(e)} >Sign up</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	)
}
export default Signup;