import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
const DetailedInfoModal = (props) =>{

	const [mov,setMov] = useState(props.movie);

	

	return(
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
							<div className="col-4">
								<div className="container">
									<p ><span className="fw-bold">Rating</span></p>

								
								</div>
								<div className="btn btn-primary">Add To Cart</div>

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
	)
}
export default DetailedInfoModal;