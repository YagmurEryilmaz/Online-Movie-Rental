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
import WatchTrailerModal from "./WatchTrailerModal";


const DetailedInfoModal = (props) =>{

	const [mov,setMov] = useState(props.movie);
	const [rating, setRating] = useState(2);
	const [isRated,setIsRated] = useState(false);
	const subtitles = ["French", "Turkish", "German", "Arabic", "Dutch","Spanish", "Chinese"]
	const [reqSubtitle, setReqSubtitle] = useState(subtitles[0])
	const [inputValue, setInputValue] = useState('');

	const handleClick = ()=>{
		var subt = {

			movieName: mov.title,
			requestedSubLang: reqSubtitle
		}
		axios.post("http://127.0.0.1:8080/api/v1/subtitleRequest/addSubtitleRequest",subt ).then(
			window.alert("Request Submitted")
		).catch((err) => console.log(err))
	}

	const handleRate = () => {
		window.alert("Rating Submitted");
		console.log(rating)
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
													<li>{subt}</li>
												})}
											</ul>
											</div>

										</div>
									</div>
								
							</div>
							<div className="col-4 ">
								<div className="container mb-3">
									<Typography component="legend">Rating</Typography>
									{!isRated ? <Rating
										name="simple-controlled"
										value={rating}
										onChange={(event, newValue) => {
											setRating(newValue);
											setIsRated(true);
											handleRate();
										}}
									/> : <Rating
										name="read-only"
										value={rating}
											readOnly />}
									
									

								</div>
								<div className="col-8 mb-3 btn btn-primary">Add To Cart</div>
								<button type="button" data-bs-target="#watchTrailer" className="col-8 mb-3  btn btn-primary" data-bs-toggle = "modal" >Watch Trailer</button>
								

									

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
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="watchTrailer" tabindex="-1">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">{props.movie.title} Trailer</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
									<div class="modal-body d-flex justify-content-center">
										<iframe width="720" height="315"
											src="https://www.youtube.com/embed/tgbNymZ7vqY?autoplay=1&mute=1">

										</iframe>

									</div>
								<div class="modal-footer">
							<a data-bs-toggle="modal" href={String(`#detailedInfoModal${props.movie.mid}`)} class="btn btn-primary">Back to Detailed Info</a>
					</div>
				</div>
			</div>
		</div>
		</>
		
	)
}
export default DetailedInfoModal;

