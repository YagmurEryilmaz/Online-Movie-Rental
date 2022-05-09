import { useState } from "react";
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import axios from "axios";

const AddFriendModal = () => {
	const [value, setValue] = useState("");
	const friends = ["jcanonal@gmail.com", "canonalbjk@gmail.com", "cekoley@gmail.com", "yagmurery123@hotmail.com", "elifcen@gmail.com"]
	const handleClick= () =>{
		window.alert("Request Submitted");
	}
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
export default AddFriendModal;