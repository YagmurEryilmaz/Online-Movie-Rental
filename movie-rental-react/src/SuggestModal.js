import axios from "axios";
import { useState } from "react";
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';

const SuggestModal = (props) => {

	const [movieId, setMovieId] = useState(props.mId);

	const [message,setMessage] = useState("");
	const [value,setValue] = useState("");
	const friends = ["jcanonal@gmail.com", "canonalbjk@gmail.com", "cekoley@gmail.com", "yagmurery123@hotmail.com", "elifcen@gmail.com"]
	const handleSubmit = () => {
		if(value == ""){
			window.alert("Please enter a valid email")
		}
		else{
			window.alert("Suggestion Sent")
			console.log(value, message)
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
						<h5>Your message (optional)</h5>
						<input type="text" onChange={(e) => setMessage(e.target.value)} class="form-control" id="exampleFormControlInput1" placeholder="Send your message"></input>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button onClick={() => handleSubmit()} type="submit" className="btn btn-primary">Submit</button>
					</div>
				</div>
			</div>
		</div>
	)
}
export default SuggestModal;