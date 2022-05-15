
import Navbar from "./Navbar"
import Sidebar from "./Sidebar"
import {useState, useEffect} from "react";
import axios from "axios";
const EmployeeHomepage = () => {
	const [all_movie_data, setMovies] = useState([]);
	const [filteredMovies, setFilteredMovies] = useState(all_movie_data);
	const [filter, setFilter] = useState("");
	useEffect(() => {
		axios.get("http://127.0.0.1:8080/api/v1/movie/getAllMovies").then((response) => {
			console.log(response.data)
			setFilteredMovies(response.data)
			setMovies(response.data);
		}).catch((error) => {console.log(error)})

	}, []);
	const filterMovies = () => {
		var theMoviesDir = all_movie_data.filter((movie) => {

			return (
				movie.directorName.toLowerCase().includes(filter.toLowerCase())
			)
		})
		setFilteredMovies(theMoviesDir);
		var theMoviesTitle = all_movie_data.filter((mov) => {
			return (
				mov.title.toLowerCase().includes(filter.toLowerCase())
			)
		})
		setFilteredMovies(filteredMovies => [...filteredMovies, ...theMoviesTitle])
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
					<div className="row mt-3">
						<div className="input-group mb-3">
							<input type="text" onChange={
								(e) => {
									setFilter(e.target.value)
								}
							} className="form-control" placeholder="Filter by Title or Director Name" aria-label="Filter by Title" aria-describedby="searchBox" />
							<div className="input-group-append">
								<button onClick={() => {
									filterMovies()
								}} className="btn btn-outline-secondary" type="button">Search</button>
							</div>
						</div>
					</div>
					<div className="row moviesRent overflow-auto">
						{filteredMovies.map((movies) => {
							return(
								<div class="col-sm-6 mt-3">
									<div class="card">
										<div className="row">
											<div className="col-4">
												<img src={movies.posterUrl} alt={movies.title} />
											</div>
											<div class="col-8 card-body">
												<h5 class="card-title">{movies.title}</h5>
												<p class="card-text"><span className="fw-bold">Director:</span> {movies.directorName}</p>
												<p class="card-text"><span className="fw-bold">Prod. Year:</span> {movies.productionYear}</p>
												<p class="card-text"><span className="fw-bold">Price:</span> {movies.price}$</p>
												<p class="card-text"><span className="fw-bold">Genre:</span> {movies.genre}</p>




												<a data-bs-toggle="modal" href={String(`#detailedInfoModal${movies.mid}`)} class="btn btn-primary">Detailed Information</a>

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
export default EmployeeHomepage;