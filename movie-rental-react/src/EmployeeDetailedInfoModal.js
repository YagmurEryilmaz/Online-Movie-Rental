import axios from "axios";
import {useState} from "react";
import {useEffect} from "react";
import {ReactDOM} from "react";
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import {connect} from "react-redux";

import WatchTrailerModal from "./WatchTrailerModal";


const EmployeeDetailedInfoModal = ({cart, uid, delete_movie,add_to_cart, ...props}) => {

	const subtitles = ["French", "Turkish", "German", "Arabic", "Dutch", "Spanish", "Chinese"]
	const [mov, setMov] = useState(props.movie);
	const [rating, setRating] = useState();
	const [ratingAvg, setRatingAvg] = useState();
	const [isRated, setIsRated] = useState(false);
	const [trailer, setTrailer] = useState("");
	const [friends, setFriends] = useState([]);
	const [customers, setCustomers] = useState([]);
	//const subtitles = mov.subtitleLang;
	//const [reqSubtitle, setReqSubtitle] = useState(subtitles[0])
	const [inputValue, setInputValue] = useState('');
	const [reqEmail, setReqEmail] = useState(friends[0]);
	const [inputValue1, setInputValue1] = useState('');
	const [price, setPrice] = useState(-1);
	const [trailerURL,setTrailerUrl] = useState("")
	const [subtitle,setSubtitle] =useState("")
	const [movieLang, setLang] = useState("")


	useEffect(() => {
		var movieId = props.movie.mid;


		axios.get("http://127.0.0.1:8080/api/v1/customer/getAllCustomers").then(
			(response) => {
				setCustomers(response.data);
				var emails = response.data.map((customer) => customer.email);
				setFriends(emails)
			}
		).catch((err) => {console.log(err)});
		axios.get(`http://127.0.0.1:8080/api/v1/rate/getAveragePoint/${movieId}`).then(
			(response) => {
				setRatingAvg(response.data);

			}
		).catch((err) => {console.log(err.response)})
		
	}, [isRated])
	var trailerLink = props.movie.trailerUrl
	if(props.movie.movieLang && props.movie.subtitleLang){
		var subtitlesArr = props.movie.subtitleLang.map((sub) => {
			return sub.s_lang
		})
		var movieLangArr = props.movie.movieLang.map((lang)=>{
			return lang.movieLang
		})

	}else{
		var subtitlesArr = []
		var movieLangArr = []
	}



	const deleteMovie = () => {
		if(window.confirm("Are you sure you want to delete this movie?")){
			axios.delete(`http://127.0.0.1:8080/api/v1/movie/deleteMovie/${props.movie.mid}`).then(
				(response) => {
					if(response){
						window.alert("Movie deleted")
						delete_movie(props.movie)

					}
				}
			).catch((err) => {console.log(err)})
		}
	}
	const editTrailer = () => {
		//axios post
		if(trailerURL !== ""){
			
			axios.patch(`http://127.0.0.1:8080/api/v1/movie/updateTrailer/${props.movie.mid}`,{trailerUrl:trailerURL}).then(
				(response) => {
					if(response){
						window.alert("Trailer updated")

					}
				}
			).catch((err) => {console.log(err)})

		}else{
			window.alert("Please enter a valid URL")
		}
	}
	const addLang = ()=> {
		if(movieLang !== ""){
			if(movieLangArr.indexOf(movieLang) === -1){
				var movLangObj = {
					movieLang: movieLang,
				}

				axios.post(`http://127.0.0.1:8080/api/v1/movieLang/addMovieLang/${props.movie.mid}`,movLangObj).then(
					(response) => {
						if(response){
							window.alert("Added")
						}
					}
				).catch((err) => {console.log(err)})
			}
			else{
				window.alert("Movie language already exists")

			}
		}
		else{
			window.alert("Please enter all fields")
		}
	}
	const addSubtitle = () =>{
		if(subtitle !== ""){
			if(subtitlesArr.indexOf(subtitle)!== -1){
				window.alert("Subtitle language already exists")
			}
			else{
				var subtObj = {
					s_lang: subtitle
				}
				axios.post(`http://127.0.0.1:8080/api/v1/subtitleLang/addSubtitleLang/${props.movie.mid}`,subtObj).then(
					(response) => {
						if(response){
							window.alert("Added")
						}
					}
				).catch((err) => {console.log(err)})

			}
		}else{
			window.alert("Please enter all fields")
		}
	}

		
	const editPrice = () => {
		if(price !== -1){
			axios.patch(`http://127.0.0.1:8080/api/v1/movie/updateMoviePrice/${props.movie.mid}/${price}`).then(
				(response) => {
					if(response){
						window.alert("Price Updated")
					}
				}
			).catch((err)=>{console.log(err)})

		}else{
			window.alert("Please enter a valid price")
		}
	}
	return (
		<>
			<div class="modal fade" id={`employeeDetailedInfoModal${props.movie.mid}`} tabindex="-1">
				<div class="modal-dialog modal-lg modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">{props.movie.title}</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div className="row">
								<div className="col-8">

									<div class="card">
										<div className="row">
											<div className="col-4 d-flex align-items-center">
												<img src={props.movie.posterUrl} alt={props.movie.title} />
											</div>
											<div class="col-8 overflow-auto card-body">
												<h5 class="card-title"></h5>
												<p class="card-text"><span className="fw-bold">Director:</span>{props.movie.directorName} </p>
												<p class="card-text"><span className="fw-bold">Prod. Year:</span>{props.movie.productionYear}</p>
												<p class="card-text"><span className="fw-bold">Price:</span>{props.movie.price} $</p>
												<p class="card-text"><span className="fw-bold">Genre:</span> {props.movie.genre}</p>
												<p class="card-text"><span className="fw-bold">Movie Languages:</span></p>
												<ul>
													{movieLangArr.map((lang) => {
														return (
															<li>{lang}</li>
														)
													})}
												</ul>
												<p class="card-text"><span className="fw-bold">Subtitle Languages:</span></p>
												<ul>
													{subtitlesArr.map((subt) => {
														return (
															<li>{subt}</li>
														)
													})}
												</ul>
											</div>

										</div>
									</div>

								</div>
								<div className="col-4 ">
									<div className="container mb-3">
										<Typography component="legend">Rating</Typography>
										<h3><span className="fs-4 fw-light">{ratingAvg}</span><span className="fs-4 fw-light">/5</span></h3>
										 <Rating
											name="read-only"
											value={ratingAvg}
											readOnly />
									</div>

									<h5>Edit Price</h5>
									<input onChange={(e) => setPrice(e.target.value)} className="my-2" type="number" placeholder={props.movie.price} />
									<button type="button" onClick={() => {editPrice()}} className="col-8 mb-3  btn btn-primary" >Edit Price </button>
									
									<h5>Edit Trailer URL</h5>
									<input onChange={(e) => setTrailerUrl(e.target.value)} className="my-2" type="text" placeholder={props.movie.trailerUrl} />
									<button type="button" onClick={()=>{editTrailer()}} className="col-8 mb-3  btn btn-primary" >Edit Trailer URL</button>

									<h5>Add Subtitle</h5>
									<input onChange={(e) => setSubtitle(e.target.value)}className="my-2" type="text" />
									<button type="button" onClick={()=>{addSubtitle()}} className="col-8 mb-3  btn btn-primary" >Add Subtitle</button>

									<h5>Add Movie Language</h5>
									<input onChange={(e) => setLang(e.target.value)}className="my-2" type="text" />
									<button type="button" onClick={() => {addLang()}} className="col-8 mb-3  btn btn-primary" >Add Language</button>

									
									<a href={trailerLink} target = "_blank" className="col-8 mb-3  btn btn-primary"> Watch Trailer </a>

									<button type="button" onClick={() => deleteMovie()} className="col-8 mb-3 btn btn-danger">Delete Movie</button>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							
						</div>
					</div>
				</div>
			</div>
			
		</>

	)
}
const mapStateToProps = (state) => {
	return {
		cart: state.cart,
		uid: state.uid,

	}
}
const mapDispatchToProps = (dispatch) => {
	return {
		add_to_cart: (mov) => {
			dispatch({type: "ADD_TO_CART", payload: {movie: mov}})
		},
		delete_movie: (mov) => {
			dispatch({type: "DELETE_MOVIE", payload: {movie: mov}})
		}
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(EmployeeDetailedInfoModal);

