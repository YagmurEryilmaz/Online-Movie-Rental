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


const DetailedInfoModal = ({cart, uid, add_to_cart, ...props}) =>{

	
	const [mov,setMov] = useState(props.movie);
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
	
	useEffect(() => {
		var movieId = mov.mid;
		console.log("inside useEffect")
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
				console.log(response.data)
			}
			).catch((err)=>{console.log(err.response)})
		axios.get(`http://127.0.0.1:8080/api/v1/trailer/getTrailerByMovie/${movieId}`).then(
			(response) => {
				setTrailer(response.data);
				console.log(response.data)
			}
		).catch((err)=>{console.log(err.response)})

		},[isRated])

		
		var contains = cart.indexOf(mov);
	var trailerLink = mov.trailers[0].trailerUrl
	const handleClick = ()=>{
		var subt = {

			movieName: mov.title,
			requestedSubLang: reqSubtitle
		}
		axios.post("http://127.0.0.1:8080/api/v1/subtitleRequest/addSubtitleRequest",subt ).then(
			window.alert("Request Submitted")
		).catch((err) => console.log(err))
	}
	const sendGift = () =>{
		var receiver = customers.find(c => {return c.email === reqEmail});
		var date = new Date();
		date.setDate(date.getDate() + 7);
		var gift = {
			sender_id: uid,
			receiver_id: receiver.uid,
			m_id: mov.mid,
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
	}

	const handleRate = () => {
		
		var ratePost = {
			movie: mov.mid,
			customer: 1,
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
		<div class="modal fade" id= {`detailedInfoModal${props.movie.mid}`} tabindex="-1">
			<div class="modal-dialog modal-lg modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">{mov.title}</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div className="row">
							<div className="col-8">
								
									<div class="card">
										<div className="row">
											<div className="col-4 d-flex align-items-center">
												<img src= {mov.posterUrl} alt={mov.title} />
											</div>
											<div class="col-8 overflow-auto card-body">
												<h5 class="card-title"></h5>
												<p class="card-text"><span className="fw-bold">Director:</span>{mov.directorName} </p>
												<p class="card-text"><span className="fw-bold">Prod. Year:</span>{mov.productionYear}</p>
												<p class="card-text"><span className="fw-bold">Price:</span>{mov.price} $</p>
												<p class="card-text"><span className="fw-bold">Genre:</span> {mov.genre}</p>
												<p class="card-text"><span className="fw-bold">Movie Languages:</span></p>
												<ul>
													{mov.movieLang.map((lang) => {
														return(
															<li>{lang.movieLang}</li>
														)
													})}
												</ul>
											<p class="card-text"><span className="fw-bold">Subtitle Languages:</span></p>
											<ul>
												{mov.subtitleLang.map((subt)=>{
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
									{(contains === - 1) ? <div onClick = {() => add_to_cart(mov)} className="col-8 mb-3 btn btn-primary">Add To Cart</div> : <button type="button" className="col-8 mb-3 btn btn-secondary" disabled>Already In Cart</button> }

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

	}
}
const mapDispatchToProps = (dispatch) => {
	return {
		add_to_cart: (mov) => {
			dispatch({type: "ADD_TO_CART", payload: {movie:mov}})
		}
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(DetailedInfoModal);

