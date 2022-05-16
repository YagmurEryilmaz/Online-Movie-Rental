import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';

import {connect} from "react-redux";
const SuggestModal = ({uid, ...props}) => {

	const [movieId, setMovieId] = useState(props.mId);

	const [message,setMessage] = useState("");
	const [value,setValue] = useState("");
	const [friends,setFriends] = useState([]);
	const [customers, setCustomers] = useState([]);
	useEffect(() => {
		axios.get("http://127.0.0.1:8080/api/v1/customer/getAllCustomers").then(
			(response) => {
				setCustomers(response.data);
				var emails = response.data.map((customer) => customer.email);
				setFriends(emails)
			}
		).catch((err) => {console.log(err.response)});
	}, [])

	const handleSubmit = () => {
		if(value == ""){
			window.alert("Please enter a valid email")
		}
		else{
			var receiver = customers.find(c => {return c.email === value});
			var suggestion = {
				senderId: uid,
				receiverId: receiver.uid,
				movieId: movieId
			}
			axios.post("http://127.0.0.1:8080/api/v1/suggestion/addSuggestion",suggestion).then(
				(response) => {
					if(response)
					{
						window.alert("Suggestion submitted")
					}
				}
			).catch((err) => {console.log(err.response)})

			console.log(suggestion)
		}
		


	}
	return(
		<div class="modal" id = {`suggestModal${props.mId}`} tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Suggest This Movie</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<h5>Friend Email:</h5>
						<Autocomplete
							disablePortal
							value = {value}
							onChange={(event,newValue)=>{
								setValue(newValue);
							}}
							id="combo-box-demo"
							options={friends}
							sx={{height: 90}}
							renderInput={(params) => <TextField {...params} label="Friend Email" />}
						/>
						
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button onClick={() => handleSubmit()} data-bs-dismiss="modal" type="submit" className="btn btn-primary">Submit</button>
					</div>
				</div>
			</div>
		</div>
	)
}
const mapStateToProps = (state) => {
	return {
		uid: state.uid
	}
}
export default connect(mapStateToProps)(SuggestModal);