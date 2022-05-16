import {useState, useEffect} from "react";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import ChangeAvatarModal from "./ChangeAvatarModal";
import EditProfileModal from "./EditProfileModal";
import AddFriendModal from "./AddFriendModal";
import DetailedInfoModal from "./DetailedInfoModal";
import {Link} from "react-router-dom";
import {Navigate} from "react-router";
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
import {user_data} from "./Data";
import axios from "axios";
import {movie_suggestions} from "./Data";
import {connect} from "react-redux";
import Chip from '@mui/material/Chip';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';


const EmployeeProfilePage = ({name, mail, uid, birthday}) => {
	const subtitles = ["French", "Turkish", "German", "Arabic", "Dutch", "Spanish", "Chinese"]
	const [profilePhoto, setProfilePhoto] = useState(defaultAvatar);


	const [bio, setBio] = useState(user_data[0].bio);
	const [genre, setGenre] = useState("");
	const [price, setPrice] = useState(0);
	const [posterUrl, setPosterUrl] = useState("");
	const [trailerUrl, setTrailerUrl] = useState("");
	const [movieName, setMovieName] = useState("");
	const [prodYear, setProdYear] = useState(0);
	const [dirName, setDirName] = useState("");
	const [subtitleArr, setSubtitleArr] = useState([]);
	const [movieLang, setMovieLang] = useState([]);
	const [friendSuggestions, setFriendSuggestions] = useState([]);


	useEffect(() => {
		axios.get(`http://127.0.0.1:8080/api/v1/suggestion/getSuggestionsByReceiver/${uid}`).then((response) => {
			console.log(response.data)
			setFriendSuggestions(response.data)
		}).catch((error) => {console.log(error)})
	}, [])

	var trailers = 
		{
			trailerId: 7,
			trailerUrl: "trailerUrl",
		}
	
	var trailerSet = new Array()
	trailerSet.push(trailers);
	console.log(trailerSet)

	const changePP = (avatar) => {
		setProfilePhoto(avatar);
	}
	const avatars = [
		avatar1, avatar2, avatar3, avatar4, avatar5, avatar6, avatar6, avatar7, avatar8, avatar9
	]
	const handleSubmit = () => {
		if(movieName == "" || prodYear < 1900 || dirName == "" || genre == "" || price == 0 || movieLang.length == 0 || subtitleArr.length == 0) {
			window.alert("Please fill all required fields correctly!");
		}
		else {
			var date = new Date();
			
			date.setDate(date.getDate());
			
			var trailers =
			{
				trailerId: 7,
				trailerUrl: trailerUrl,
			}

			var trailerSet = new Set()
			trailerSet.add(trailers);
			console.log(trailerSet)

			
			var subtSet = new Set(subtitleArr);
			var langSet = new Set(movieLang);
			var movieInfo = {
				title: movieName,
				directorName: dirName,
				genre: genre,
				productionYear: prodYear,
				price: price,
				posterUrl: posterUrl,
				additionDate: date,
				trailerUrl: trailerUrl,
				mlang: movieLang,
				slang: subtitleArr,
				

			}
			axios.post("http://127.0.0.1:8080/api/v1/movie/addMovieToSystem", movieInfo).then(
				(response) => {
					if(response)
						window.alert("movie added")
				}

			).catch((err) => {console.log(err)})

			console.log(movieInfo);

		}

	}

	return (
		<div className='container'>
			<div className="row">
				<div className="d-none d-lg-block col-md-2 px-0">
					<Sidebar />
				</div>
				<div className="col-md-10">
					<div className='row  align-items-between'>
						<Navbar />
					</div>
					<div className="row overflow-auto">
						<div className="col-6 mt-5 overflow-auto ">
							<div className="card border border-info ">
								<div className=" d-flex justify-content-center">
									<img src={profilePhoto} className=" card-img-top" alt="..." />
								</div>
								<div className="card-body">
									<h5 className="card-title">{name}</h5>
									<p className="card-text">{bio}</p>
								</div>
								<ul className="list-group list-group-flush">
									<li className="list-group-item"><span className="fw-bold">Email: </span>{mail}</li>
									<li className="list-group-item"><span className="fw-bold">Birthday: </span>{birthday}</li>

								</ul>
								<div className="card-body">

									<a data-bs-toggle="modal" href="#editProfileModal" className="card-link">Edit Profile  </a>
									<a data-bs-toggle="modal" href="#changeAvatar" className="card-link">Change Avatar </a>
								</div>
								<ChangeAvatarModal avatars={avatars} changeAvatar={changePP} def_avatar={profilePhoto} />
								<EditProfileModal bio={bio} changeBio={setBio} />

							</div>
							
						</div>
						<div className="col-6 overflow-auto">
							<div className="row overflow-auto createMovieRequest mt-5 ms-2 ">
								<form className="border border-2 overflow-auto rounded-2 border-info">
									<div className="fs-3">
										Add Movie To Database
									</div>
									<div class="mb-3 mt-2">
										<label for="exampleFormControlInput1" class="form-label">Movie Name</label>
										<input type="text" onChange={(e) => setMovieName(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Director Name</label>
										<input type="text" onChange={(e) => setDirName(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Production Year</label>
										<input type="number" min = "1900" max = "2022" step = "1"  onChange={(e) => setProdYear(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Genre</label>
										<input type="text" onChange={(e) => setGenre(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Poster Url</label>
										<input type="text" onChange={(e) => setPosterUrl(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Trailer Url</label>
										<input type="text" onChange={(e) => setTrailerUrl(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Price</label>
										<input type="number" onChange={(e) => setPrice(e.target.value)} class="form-control" id="exampleFormControlInput1" required />
									</div>
									<div className="mb-3">
										<Autocomplete
											multiple
											id="tags-filled"
											options={subtitles}
											defaultValue={[subtitles[0]]}
											freeSolo
											renderTags={((value, getTagProps) =>
												value.map((option, index) => (
													setSubtitleArr(value),
													<Chip variant="outlined" label={option} {...getTagProps({index})} />
												)
												))
											}
											renderInput={(params) => (
												<TextField
													
													{...params}
													variant="filled"
													label="Subtitles"
													placeholder="Subtitles"
												/>
											)}
										/>
									</div>
									<div className="mb-3">
										<Autocomplete
											multiple
											id="tags-filled"
											options={subtitles}
											defaultValue={[subtitles[0]]}
											freeSolo
											renderTags={((value, getTagProps) =>
												value.map((option, index) => (
													setMovieLang(value),

													<Chip variant="outlined" label= {option} {...getTagProps({index})} />
												)
												))
											}
											renderInput={(params) => (
												<TextField
													
													{...params}
													variant="filled"
													label="Movie Languages"
													placeholder="Movie Languages"
												/>
											)}
										/>
									</div>
									<button type="button" onClick={() => handleSubmit()} className="btn btn-info mb-3">
										Add Movie
									</button>




								</form>

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
const mapStateToProps = (state) => {
	return {
		name: state.name,
		mail: state.email,
		birthday: state.birthday,
		uid: state.uid
	}
}
export default connect(mapStateToProps)(EmployeeProfilePage);