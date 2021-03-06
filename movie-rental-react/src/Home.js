import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import iceAgeIcon from "./img/iceAge.png"
import SuggestModal from "./SuggestModal";
import {connect} from "react-redux";
import { useEffect } from "react";
import axios from "axios";

import "./Home.css";
import { movie_data } from "./Data";
import { useState } from "react";
const Home = ({uid})=>{
	const [movies, setMovies] = useState([]);
	const [prevMov, setPrevMov] = useState([]);
	useEffect (()=>{
		axios.get(`http://127.0.0.1:8080/api/v1/rent/current/${uid}`).then((response)=>{
			setMovies(response.data);
			console.log(response.data);
		}).catch((error)=>{console.log(error)})
		axios.get(`http://127.0.0.1:8080/api/v1/rent/previous/${uid}`).then((response)=>{
			setPrevMov(response.data);
			console.log(response.data);
		}).catch((err )=>{console.log(err)})

	},[])
	
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
					<div className="row d-flex justify-content-center align-items-center">
						<div className="col-6 mt-5 ms-3 currentMovies">
							<div className="card border-info">
								<div className="card-header border-info">
									Current Movies
								</div>
								<ul className="list-group border-info list-group-flush">
									{movies.map((movieObj)=> {
										return(
											<li class="list-group-item border-info">
												<div className="row">
													<div className="col-4">
														<img className="cardImg" src={movieObj.movie.posterUrl} alt={movieObj.movie.title} />

													</div>
													<div className="col-8">

														<div className = "fs-4 fw-bold">
															{movieObj.movie.title}
														</div>
														<div className="fs-5 fw-normal">
															Director: {movieObj.movie.directorName}
														</div>
														<div className="fs-5 fw-light">
															Prod. Year: {movieObj.movie.productionYear}
														</div>
														<div className="fs-5 fw-light">
															Expiration Date: {movieObj.expDate}
														</div>
													</div>
												</div>

											</li>
										)
										
									})}
								</ul>
							</div>
						</div>
						<div className="col-6 mt-5 currentMovies">
							<div className="card border-info">
								<div className="card-header border-info">
									Previously Rented Movies
								</div>
								<ul className="list-group border-info list-group-flush">
									{prevMov.map((movieObj) => {
										return (
											<li class="list-group-item border-info">
												<div className="row">
													<div className="col-4">
														<img className="cardImg" src={movieObj.movie.posterUrl} alt={movieObj.movie.title} />

													</div>
													<div className="col-8">

														<div className="fs-4 fw-bold">
															{movieObj.movie.title}
														</div>
														<div className="fs-5 fw-normal">
															Director: {movieObj.movie.directorName}
														</div>
														<div className="fs-5 fw-light">
															Prod. Year: {movieObj.movie.productionYear}
														</div>
														<div className="fs-5 fw-light">
															Expiration Date: {movieObj.expDate}
														</div>
														<button type="button" data-bs-toggle="modal" data-bs-target={String(`#suggestModal${movieObj.movie.mid}`)} className="btn btn-primary">
															Suggest
														</button>
														<SuggestModal mId = {movieObj.movie.mid}/>
													</div>
												</div>
												

											</li>
										)

									})}
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			
			
			
		</div>
	)
}
const mapStateToProps = (state) => {
	return {
		uid: state.uid,
	}
}

export default connect(mapStateToProps)(Home);