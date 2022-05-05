import axios from "axios";
import { useState } from "react";

const SuggestModal = (props) => {

	const [movieId, setMovieId] = useState(props.mId);
	const [mail, setMail] = useState("");
	const [message,setMessage] = useState("");
	const handleSubmit = () => {
		window.alert("Suggestion Sent")


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
						<h5>Email of Recipient:</h5>
						<input type="email" onChange = {(e) => setMail(e.target.value)} class="form-control" id="exampleFormControlInput1" placeholder="name@example.com" required/>
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