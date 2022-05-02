import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import axios from "axios";
import "./Rent.css"
import { useState } from "react";
import { useEffect } from "react";
import { movie_data } from "./Data";

var all_movie_data = []

const Rent = () =>{
	const [moviesSet, setMoviesSet] = useState(false);
	useEffect(() => {
		axios.get("http://127.0.0.1:8080/api/v1/auth/getAllMovies").then((response)=>{
			console.log(response.data)
			all_movie_data = response.data
		}).catch((error)=>{console.log(error)})

	}, []);
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
					<div class="row moviesRent overflow-auto">
						{all_movie_data.map((movies) => {
							return(
								<div class="col-sm-6 mt-3">
									<div class="card">
										<div className="row">
											<div className="col-4">
												<img src={movies.img_url} alt={movies.title} />
											</div>
											<div class="col-8 card-body">
												<h5 class="card-title">{movies.title}</h5>
												<p class="card-text"><span className = "fw-bold">Director:</span> {movies.director}</p>
												<p class="card-text"><span className="fw-bold">Prod. Year:</span> {movies.prod_year}</p>
												<p class="card-text"><span className="fw-bold">Price:</span> {movies.price}$</p>

											
												<a data-bs-toggle = "modal" href="#" class="btn btn-primary">Detailed Information</a>
											</div>

										</div>
									</div>
								</div>
							)
						})}
						
						
						
					</div>
				</div>
			</div>
		</div>
	)
}
export default Rent;