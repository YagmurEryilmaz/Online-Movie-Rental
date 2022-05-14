import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
const NotificationModal = ({uid, fetch_requests, friendRequests, accept_request, email}) => {
	const [requests,setRequests] = useState([])
	
	
	useEffect(()=> {
		axios.get(`http://127.0.0.1:8080/api/v1/friendRequest/getFriendRequestsByReceiver/${uid}`).then(
			(response) =>{
				console.log(response.data)
				setRequests(response.data);
				fetch_requests(response.data);
				
			}
		).catch((err) => {console.log(err)})
			
	},[])
	const acceptRequest = (request) => {
		var reqInfo = {
			sender_email: request.sender.email,
			receiver_id: request.receiver.uid,
		}
		axios.post("http://127.0.0.1:8080/api/v1/friendRequest/accept",reqInfo).then(
			(response) => {
				if(response)
				{
					window.alert("Request Accepted")

					accept_request(request)
				}
			}
		)
	}
	const declineRequest = (request) => {
		var reqInfo = {
			sender_email: request.sender.email,
			receiver_id: request.receiver.uid,
		}
		axios.post("http://127.0.0.1:8080/api/v1/friendRequest/reject", reqInfo).then(
			(response) => {
				if(response)
				{
					window.alert("Request Declined")
					accept_request(request)
				}
			}
		)
	}
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
								{friendRequests.map((request)=>{
									return(
										

											<li class="list-group-item">


												<span className="fw-bold">From: {request.sender.name}</span>
												<span className="fw-light mx-3">({request.sender.email})</span>

											


											<div className="float-end btn btn-danger" onClick={() => declineRequest(request)}>Decline</div>
													<div className="float-end me-3 btn btn-success" onClick = {()=>acceptRequest(request)}>Accept</div>



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
		email: state.email,
		friendRequests: state.friendRequests
	}
}
const mapDispatchToProps = dispatch => {
	return{
		fetch_requests: (requests) => dispatch({type: "FETCH_REQUESTS", payload: {requests:requests}}),
		accept_request: (request) => dispatch({type: "ACCEPT_REQUEST", payload: {request:request}})
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(NotificationModal);