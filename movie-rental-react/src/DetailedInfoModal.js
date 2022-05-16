import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { ReactDOM } from "react";
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import { connect } from "react-redux";

import WatchTrailerModal from "./WatchTrailerModal";


const DetailedInfoModal = ({cart, uid, detMovie,balance, update_balance, add_to_cart, ...props}) =>{

	
	const [mov,setMov] = useState({});
	const [rating, setRating] = useState();
	const [ratingAvg, setRatingAvg] = useState();
	const [isRated,setIsRated] = useState(false);
	const [trailer, setTrailer] = useState("");
	const [friends, setFriends] = useState([]);
	const [customers, setCustomers] = useState([]);
	const subtitles = ["French", "Turkish", "German", "Arabic", "Dutch","Spanish", "Chinese"]
	const [reqSubtitle, setReqSubtitle] = useState(subtitles[0])
	const [inputValue, setInputValue] = useState('');
	const [reqEmail, setReqEmail] = useState(friends[0]);
	const [inputValue1, setInputValue1] = useState('');
	const [subtitlesMov, setSubtitlesMov] = useState([]);
	
	useEffect(() => {
		console.log(props.movie.mid)
		axios.get(`http://127.0.0.1:8080/api/v1/movie/getMovieById/${props.movie.mid}`).then(
			(response) => {
				console.log(response.data)
				setMov(response.data);
			}
		).catch((err) => {console.log(err.response)});
		console.log()
		var movieId = props.movie.mid;

		axios.get(`http://127.0.0.1:8080/api/v1/friendRequest/getFriendEmails/${uid}`).then(
			(response) => {
				setFriends(response.data);
			}
		).catch((err) => {console.log(err.response)});
		console.log(movieId)
		console.log("inside useEffect")
		axios.get("http://127.0.0.1:8080/api/v1/customer/getAllCustomers").then(
			(response) => {
				setCustomers(response.data);
				
			}
		).catch((err) => {console.log(err)});
		axios.get(`http://127.0.0.1:8080/api/v1/rate/getAveragePoint/${movieId}`).then(
			(response) => {
				console.log(response.data)
				setRatingAvg(response.data);
			}
			).catch((err)=>{console.log(err.response)})
		


		},[isRated])

		
	var contains = cart.indexOf(props.movie);
	
	var trailerLink = props.movie.trailerUrl
	const handleClick = ()=>{
		var subt = {

			movieName: props.movie.title,
			requestedSubLang: reqSubtitle
		}
		axios.post("http://127.0.0.1:8080/api/v1/subtitleRequest/addSubtitleRequest",subt ).then(
			window.alert("Request Submitted")
		).catch((err) => console.log(err))
	}
	const sendGift = () =>{
		if(props.movie.price > balance){
			window.alert("You don't have enough balance to gift this movie")
		}
		else{

			var receiver = customers.find(c => {return c.email === reqEmail});
			var newBalance = balance - props.movie.price;
			var date = new Date();
			date.setDate(date.getDate() + 7);
			var gift = {
				sender_id: uid,
				receiver_id: receiver.uid,
				m_id: props.movie.mid,
				expDate: date
			}
			console.log(gift);
			axios.post("http://127.0.0.1:8080/api/v1/gift/createGift",gift ).then(
				(response) => {
					if(response){
						window.alert("Gift Sent")
					}
				}
			).catch((err) => console.log(err))
			axios.patch(`http://127.0.0.1:8080/api/v1/customer/updateBalance/${uid}/${newBalance}`).then(
				(response) => {
					if(response) {
						console.log("balance update")
						update_balance(newBalance)
					}
				}
			).catch((err) => console.log(err))
		}
	}

	const handleRate = () => {
		
		var ratePost = {
			movie: props.movie.mid,
			customer: uid,
			point: rating
		}
		axios.post("http://127.0.0.1:8080/api/v1/rate/rateMovie",ratePost).then(
			(response) =>{
				if(response.data == "rate"){
					window.alert("Rating Submitted")
				}
			}
		).catch((err) => {window.alert((err.response.data.message))})

	}

	return(
		<>
			<div class="modal fade" id={`detailedInfoModal${props.movie.mid}`} tabindex="-1">
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
													{props.movie.movieLang.map((lang) => {
														return(
															<li>{lang.movieLang}</li>
														)
													})}
												</ul>
											<p class="card-text"><span className="fw-bold">Subtitle Languages:</span></p>
											<ul>
													{props.movie.subtitleLang.map((subt)=>{
													return(
													<li>{subt.s_lang}</li>
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
									<h3><span className ="fs-4 fw-light">{ratingAvg}</span><span className = "fs-4 fw-light">/5</span></h3>
									{!isRated ? <Rating
										name="simple-controlled"
										value={ratingAvg}
										onChange={(event, newValue) => {
											var val = newValue;
											setRating(val);
										}}
									/> : <Rating
										name="read-only"
										value={ratingAvg}
											readOnly />}
									
									

								</div>
									{(contains === - 1) ? <div onClick={() => add_to_cart(props.movie)} className="col-8 mb-3 btn btn-primary">Add To Cart</div> : <button type="button" className="col-8 mb-3 btn btn-secondary" disabled>Already In Cart</button> }

									<a href={trailerLink} target="_blank" className="col-8 mb-3  btn btn-primary"> Watch Trailer </a>
								

									

									<Autocomplete
										value={reqEmail}
										onChange={(event, newValue) => {
											setReqEmail(newValue);
										}}
										inputValue={inputValue1}
										onInputChange={(event, newInputValue) => {
											setInputValue1(newInputValue);
										}}
										id="controllable-states-demo"
										options={friends}
										sx={{width: 170}}
										renderInput={(params) => <TextField {...params} label="Send Gift" />}
									/>
									<Button variant="contained" className = "mb-3"onClick = {()=>{sendGift()}} endIcon={<SendIcon />}>
										Send
									</Button>

									<Autocomplete 
										value={reqSubtitle}
										onChange={(event, newValue) => {
											setReqSubtitle(newValue);
										}}
										inputValue={inputValue}
										onInputChange={(event, newInputValue) => {
											setInputValue(newInputValue);
										}}
										id="controllable-states-demo"
										options={subtitles}
										sx={{width: 170}}
										renderInput={(params) => <TextField {...params} label="Subtitle Request" />}
									/>
									<Button variant="contained" onClick = {()=>{handleClick()}} endIcon={<SendIcon />}>
										Send
									</Button>


							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" onClick={() => {
								setIsRated(true)
								handleRate()
								}}
								class="btn btn-primary">Save changes</button>
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
		detMovie: state.detMovie,
		balance: state.balance,

	}
}
const mapDispatchToProps = (dispatch) => {
	return {
		add_to_cart: (detMovie) => {
			dispatch({type: "ADD_TO_CART", payload: {movie: detMovie
}})
		},
		update_balance: (balance) => dispatch({type: "UPDATE_BALANCE", payload: {balance: balance}})
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(DetailedInfoModal);

