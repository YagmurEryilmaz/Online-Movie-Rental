import { useState, useEffect } from "react";
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import axios from "axios";
import {connect} from "react-redux";


const AddFriendModal = ({uid, mail}) => {
	const [value, setValue] = useState("");
	const [friends,setFriends] = useState([]);
	const [customers, setCustomers] = useState([]);
	const handleClick= () =>{
		if(value.length>0){
			var receiver = customers.find(c =>{return  c.email === value});
			var id = receiver.uid
			var friendInfo = {
				sender_id : uid,
				sender_email: mail,
				receiver_email : value,
				receiver_id: id

				
			}
			console.log(friendInfo);
			axios.post("http://127.0.0.1:8080/api/v1/friendRequest/createFriendRequest",friendInfo).then(
				(response) => {
					if(response)
					{
						window.alert("Request Submitted")
					}
				}
			
			).catch((err) => {console.log(err)})
			
			}
}
	useEffect(()=>{
		axios.get("http://127.0.0.1:8080/api/v1/customer/getAllCustomers").then(
			(response) => {
				setCustomers(response.data);
				var emails = response.data.map((customer)=> customer.email);
				setFriends(emails)
			}
		).catch((err)=>{console.log(err)});
	},[])
	return(
		<div class="modal fade" id="addFriend" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Add Friend</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<h5>Please enter the email of the user:</h5>
						<Autocomplete
							disablePortal
							value={value}
							onChange={(event, newValue) => {
								setValue(newValue);
							}}
							id="combo-box-demo"
							options={friends}
							sx={{height: 90}}
							renderInput={(params) => <TextField {...params} label="User Email" />}
						/>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="button" onClick={()=>{handleClick()}} class="btn btn-primary">Send Request</button>
					</div>
				</div>
			</div>
		</div>
	)
}
const mapStateToProps = (state) => {
	return {
		uid: state.uid,
		mail: state.email
	}
}
export default connect(mapStateToProps)(AddFriendModal);