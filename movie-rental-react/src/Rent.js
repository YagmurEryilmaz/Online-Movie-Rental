import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import axios from "axios";
import "./Rent.css"
import { useState } from "react";
import { useEffect } from "react";
import DetailedInfoModal from "./DetailedInfoModal";
import { movie_data } from "./Data";
import { connect } from "react-redux";


const Rent = ({fetch_movies, allMovies, det_movie}) =>{
	const [all_movie_data, setMovies] = useState([]);
	const [filteredMovies, setFilteredMovies] = useState(all_movie_data);
	const [genre,setGenre] = useState([])



	const [filter,setFilter] = useState("");
	useEffect(() => {
		axios.get("http://127.0.0.1:8080/api/v1/movie/getAllMovies").then((response)=>{
			console.log(response.data)

			setFilteredMovies(response.data)
			setMovies(response.data);
		}).catch((error)=>{console.log(error)})
		axios.get("http://127.0.0.1:8080/api/v1/movie/getAllGenre").then((response)=>{
			setGenre(response.data)
		}).catch((error)=>{console.log(error)})

			
		

	}, []);

	const filterMovies = () => {
		if(filter !== "") {
			axios.get(`http://127.0.0.1:8080/api/v1/movie/search/${filter}`).then((response) => {
				fetch_movies(response.data)
				setFilteredMovies(response.data)
			}).catch((error) => {console.log(error)})
		} else {
			axios.get("http://127.0.0.1:8080/api/v1/movie/getAllMovies").then((response) => {
				fetch_movies(response.data);
				console.log(response.data)
				setFilteredMovies(response.data)
				setMovies(response.data);
			}).catch((error) => {console.log(error)})
		}

	}
	const handleCheckbox = (genreInp) =>{
		var theGenre = genreInp.toLocaleLowerCase();
		axios.get(`http://127.0.0.1:8080/api/v1/movie/getMovieByGenre/${theGenre}`).then((response)=>{
			if(response.data.length === 0){
				window.alert("No movies found with this genre")
			}else{
				fetch_movies(response.data)
				setFilteredMovies(response.data)
			}

		}).catch((error)=>{console.log(error)})
		
		/*setFilteredMovies(all_movie_data);
		console.log(filteredMovies);

		var theMoviesGenre = all_movie_data.filter((movi)=>{
			return(
				movi.genre.toLowerCase().includes(genre.toLowerCase())
				)
			})
			setFilteredMovies([theMoviesGenre]);
*/


	}
	const getAll = () => {
		axios.get("http://127.0.0.1:8080/api/v1/movie/getAllMovies").then((response) => {
			console.log(response.data)
			fetch_movies(response.data)
			setFilteredMovies(response.data)
			setMovies(response.data);
		}).catch((error) => {console.log(error)})
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
						{genre.map((g)=>{
							return(
								<div class="form-check col-3 ms-3">
									<input class="form-check-input" type="radio" name="flexRadioDefault" onClick={() =>  handleCheckbox(g)} id="flexRadioDefault1" />
									<label class="form-check-label" for="flexRadioDefault1">
										{g}
									</label>
								</div>
							)
						})}
						
						<div class="form-check col-3 ms-3">
							<input class="form-check-input" type="radio" name="flexRadioDefault" onClick = {() => {getAll();}}id="flexRadioDefault1" />
								<label class="form-check-label" for="flexRadioDefault1">
									All Genre
								</label>
						</div>
						
					</div>
					<div class="row moviesRent overflow-auto">
						{allMovies.map((movies) => {
							
							return(
								<div class="col-sm-6 mt-3">
									<div class="card">
										<div className="row">
											<div className="col-4">
												<img src={movies.posterUrl} alt={movies.title} />
											</div>
											<div class="col-8 card-body">
												<h5 class="card-title">{movies.title}</h5>
												<p class="card-text"><span className = "fw-bold">Director:</span> {movies.directorName}</p>
												<p class="card-text"><span className="fw-bold">Prod. Year:</span> {movies.productionYear}</p>
												<p class="card-text"><span className="fw-bold">Price:</span> {movies.price}$</p>
												<p class="card-text"><span className="fw-bold">Genre:</span> {movies.genre}</p>

												

											
												<a data-bs-toggle="modal" href={String(`#detailedInfoModal${movies.mid}`)} onClick={()=>det_movie(movies)} class="btn btn-primary">Detailed Information</a>
											</div>

										</div>
									</div>
												<DetailedInfoModal movie = {movies} />
								</div>
							)
						})}
						
						
						
					</div>
				</div>
			</div>
		</div>
	)
}
const mapStateToProps = (state) => {
	return {
		allMovies: state.allMovies,
	}
}
const mapDispatchToProps = (dispatch) => {
	return {
		fetch_movies: (movies) => {
			dispatch({
				type: "FETCH_MOVIES",
				payload: movies
			})
		},
		det_movie: (movie) => {
			dispatch({
				type: "DET_MOVIE",
				payload: movie
			})
		}
	}
	
}

export default connect(mapStateToProps,mapDispatchToProps)(Rent);	