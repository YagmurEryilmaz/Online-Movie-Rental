import { useEffect, useState } from "react";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import ChangeAvatarModal from "./ChangeAvatarModal";
import EditProfileModal from "./EditProfileModal";
import defaultAvatar from "./img/avatars/default_avatar.png";
import avatar1 from "./img/avatars/image_part_001.png";
import avatar2 from "./img/avatars/image_part_002.png"
import avatar3 from "./img/avatars/image_part_003.png"
import avatar4 from "./img/avatars/image_part_004.png"
import avatar5 from "./img/avatars/image_part_005.png"
import avatar6 from "./img/avatars/image_part_006.png"
import avatar7 from "./img/avatars/image_part_007.png"
import avatar8 from "./img/avatars/image_part_008.png"
import avatar9 from "./img/avatars/image_part_009.png"
import "./Profile.css"
import { user_data } from "./Data";
import { movie_suggestions } from "./Data";

import axios from "axios";

const Profile = () => {
	const [profilePhoto, setProfilePhoto] = useState(defaultAvatar);
	const [name,setName] = useState(user_data[0].name);
	const [mail,setMail] = useState(user_data[0].email);
	const [bio,setBio] = useState(user_data[0].bio);
	const birthday = user_data[0].birthday;
	const[movieName, setMovieName] = useState("");
	const [prodYear, setProdYear] = useState("");
	const[dirName, setDirName] = useState("");
	const[friendSuggestions, setFriendSuggestions] = useState([]);
	

	useEffect(()=>{
		axios.get("http://127.0.0.1:8080/api/v1/suggestion/getSuggestionsByReceiver").then(
			(response) => {
				setFriendSuggestions(response.data);
			}
		).catch((err) => console.log(err.request))
	})
	
	const changePP = (avatar) =>{
		setProfilePhoto(avatar);
	}
	const avatars = [
		avatar1, avatar2, avatar3, avatar4, avatar5 , avatar6, avatar6, avatar7, avatar8, avatar9
	]
	const handleSubmit= ()=>{
		if(movieName == "" || prodYear == "" || dirName == ""){
			window.alert("Please fill all required fields");
		}
		else{

			window.alert("Movie Request Sent");
			console.log(movieName, prodYear, dirName);
		}

	}
	
	return(
		<div className='container'>
			<div className="row">
				<div className="d-none d-lg-block col-md-2 px-0">
					<Sidebar />
				</div>
				<div className="col-md-10">
					<div className='row  align-items-between'>
						<Navbar />
					</div>
					<div className="row align-items-center ">
						<div className="col-6 mt-5">
							<div className="card border border-info ">
								<div className=" d-flex justify-content-center">
									<img src= {profilePhoto} className=" card-img-top" alt="..."/>
								</div>
									<div className="card-body">
										<h5 className="card-title">{name}</h5>
										<p className="card-text">{bio}</p>
									</div>
									<ul className="list-group list-group-flush">
									<li className="list-group-item"><span className="fw-bold">Email: </span>{mail}</li>
									<li className="list-group-item"><span className = "fw-bold">Birthday: </span>{birthday}</li>

									</ul>
									<div className="card-body">

										<a data-bs-toggle="modal" href="#editProfileModal" className="card-link">Edit Profile  </a>
										<a data-bs-toggle= "modal" href="#changeAvatar" className="card-link">Change Avatar </a>
									</div>
									<ChangeAvatarModal avatars={avatars} changeAvatar={changePP} def_avatar={profilePhoto} />
									<EditProfileModal mail = {mail} bio = {bio} changeMail = {setMail} changeBio = {setBio} />
									
							</div>
						</div>
						<div className="col-6">
							<div className="row createMovieRequest mt-5 ms-2 ">
								<form className = "border border-2 rounded-2 border-info">
									<div className="fs-3">
										Create Movie Request
									</div>
									<div class="mb-3 mt-2">
										<label for="exampleFormControlInput1" class="form-label">Movie Name</label>
										<input type="text" onChange = {(e)=> setMovieName(e.target.value)}  class="form-control" id="exampleFormControlInput1" required/>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Director Name</label>
										<input type="text" onChange = {(e)=> setDirName(e.target.value)}  class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Production Year</label>
										<input type="text"  onChange = {(e)=> setProdYear(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<button type="submit" onClick = {() => handleSubmit()} className="btn btn-info mb-3">
										Send Request
									</button>


							
										
								</form>

							</div>
							<div className="row mt-3 ms-2">
								<div class="card border border-info suggestionCard">
									<div class="card-header border border-info bg-light">
										Suggested Movies
									</div>
									{friendSuggestions.map((suggestion) => {
										return(
											<div class="card-body border border-info ">
												<h5 class="card-title">Movie Name: {suggestion.title}</h5>
												<p class="card-text">From: {suggestion.name}({suggestion.email})</p>
												<a href="#" class="btn btn-primary">Go to Movie</a>
											</div>
										)
									})}
								</div>
							</div>
						</div>
					</div>
					<div className="row">

					</div>
				</div>
			</div>
		</div>
	)

}
export default Profile;