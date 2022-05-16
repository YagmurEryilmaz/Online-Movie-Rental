import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
const NotificationModal = ({uid, fetch_requests, gifts, accept_gift,fetch_gifts, role,friendRequests, numOfRequests, accept_request, email}) => {
	const [requests,setRequests] = useState([])

	const [subtReq, setSubtReq] = useState([])

	
	
	useEffect(()=> {
		if(role === "customer"){

			axios.get(`http://127.0.0.1:8080/api/v1/friendRequest/getFriendRequestsByReceiver/${uid}`).then(
				(response) =>{
					var pendingRequests = response.data.filter((request)=>{return request.friendReq_status === "pending"})
	
					console.log(pendingRequests)
	
					setRequests(pendingRequests);
					fetch_requests(pendingRequests);
					
				}
			).catch((err) => {console.log(err)})
			axios.get(`http://127.0.0.1:8080/api/v1/gift/getGiftsByReceiver/${uid}`).then(
				(response) =>{
	

					fetch_gifts(response.data)
					
				}
			)
		}else{
				axios.get(`http://127.0.0.1:8080/api/v1/movieRequest/getAllMovieRequests`).then((response) => {
					console.log(response.data)
					fetch_requests(response.data)

				}).catch((err) => {console.log(err)})

				axios.get(`http://127.0.0.1:8080/api/v1/subtitleRequest/getAllSubtitleRequests`).then(
					
				

					(response) => {

						fetch_gifts(response.data)
						setSubtReq(response.data)
					}).catch((err) => {console.log(err)})
			}
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
	const declineSubtRequest = (request) => {
		axios.delete(`http://127.0.0.1:8080/api/v1/subtitleRequest/deleteSubtitleRequest/${request.subtitleReqId}`).then(
			(response) => {
				if(response)
				{
					window.alert("Request Deleted")
					accept_gift(request)

				}
			}
		)
	}
	const deleteMovieRequest = (request) => {
		axios.delete(`http://127.0.0.1:8080/api/v1/movieRequest/deleteMovieRequest/${request.movieReqId}`).then(
			(response) => {
				if(response)
				{
					window.alert("Request Deleted")
					accept_request(request)
				}
			}
		).catch((err) => {console.log(err)})
	}
	const deleteGift = (gift) => {

		accept_gift(gift)
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
								{role === "customer" ? "Friend Requests" : "Movie Requests"}
							</div>
							{role === "customer" ? 
							<ul class="list-group list-group-flush">
								{friendRequests.map((request)=>{
									return(
											<li class="list-group-item">
												<span className="fw-bold">From: {request.sender.name}</span>
												<span className="fw-light mx-3">({request.sender.email})</span>
												<div className="float-end btn btn-danger" onClick={() => declineRequest(request)}>
													Decline
												</div>
												<div className="float-end me-3 btn btn-success" onClick = {()=>acceptRequest(request)}>
													Accept
												</div>
											</li>

									)
								})}	
							</ul>:
							<ul class="list-group list-group-flush">
								{friendRequests.map((request)=>{
									return(
											<li class="list-group-item">
											<span className="fw-bold">Movie Name: <span className = "fw-light">{request.movieName} </span></span>
											<span className="fw-bold">Director Name: <span className = "fw-light">{request.directorName}</span> </span>
											<span className="fw-bold">Prod Year: <span className = "fw-light">{request.movieProductionYear}</span> </span>
											<div className="float-end btn btn-danger" onClick={() => deleteMovieRequest(request)}>
												Delete
											</div>
												
											</li>

									)
								})}	
							</ul>}
						</div>
					</div>
					<div class="modal-body overflow auto">
						<div class="card" >
							<div class="card-header">
								{role === "customer" ? "Gifts": "Subtitle Requests"}
							</div>
							{role === "customer" ? 
							<ul class="list-group list-group-flush">
								{gifts.map((gift) => {
									console.log(gifts)
									return (
										<li class="list-group-item">
											<span className="fw-bold">{gift.senderCustomer.name} </span>
											 <span>has sent you the movie: </span>
											 <span className="fw-bold">{gift.movie.title}</span>
											<div className="float-end btn btn-danger" onClick={() => deleteGift(gift)}>
												Delete
											</div>	
										</li>

									)
								})}
							</ul>: 
							
								<ul class="list-group list-group-flush">
									{gifts.map((req) => {
										
										return (
											<li class="list-group-item">
												<span className="fw-bold">Movie Name</span>
												<span>{req.movieName}  </span>
												<span className="fw-bold">Requested Language: </span>	
												<span>{req.requestedSubLang}</span>
												<div className="float-end btn btn-danger" onClick={() => declineSubtRequest(req)}>
													Delete
												</div>
											</li>

										)
									})}
								</ul>
							}
							
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
		friendRequests: state.friendRequests,
		numOfRequests: state.numOfRequests,
		role: state.accountType,
		gifts: state.gifts,
	}
}
const mapDispatchToProps = dispatch => {
	return{
		fetch_requests: (requests) => dispatch({type: "FETCH_REQUESTS", payload: {requests:requests}}),
		accept_request: (request) => dispatch({type: "ACCEPT_REQUEST", payload: {request:request}}),
		fetch_gifts: (gifts) => dispatch({type: "FETCH_GIFTS", payload: {gifts:gifts}}),
		accept_gift: (gift) => dispatch({type: "ACCEPT_GIFT", payload: {gift:gift}}),
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(NotificationModal);