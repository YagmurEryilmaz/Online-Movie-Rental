import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
const NotificationModal = ({uid}) => {
	const [requests,setRequests] = useState([])
	useEffect(()=> {
		axios.get(`http://127.0.0.1:8080/api/v1/friendRequest/getFriendRequestsByReceiver/${uid}`).then(
			(response) =>{
				setRequests(response.data);
			}
		).catch((err) => {console.log(err)})
			
	})
	return(
		<div class="modal fade" id="notifications" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Notifications</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body overflow auto">
						<div class="card" >
							<div class="card-header">
								Friend Requests
							</div>
							<ul class="list-group list-group-flush">
								{requests.map((request)=>{
									return(
										

											<li class="list-group-item">

												<span className="fw-bold">From: {request.sender_email}</span>


													<div className="float-end btn btn-danger">Decline</div>
													<div className="float-end me-3 btn btn-success">Accept</div>



											</li>

									)
								})

								}
								
							</ul>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

					</div>
				</div>
			</div>
		</div>
	)
}
const mapStateToProps = state => {
	return{
		uid: state.uid,
	}
}
export default connect(mapStateToProps)(NotificationModal);