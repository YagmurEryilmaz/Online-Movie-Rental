import { useState } from "react";
import { connect } from "react-redux";
import axios from "axios";
const EditProfileModal = ({update_mail,email, name, uid, ...props}) =>{

	const [mailM,setMailM] = useState(email);
	const [bioM, setBioM] = useState(props.bio);
	const [isClicked, setIsClicked] = useState(false);
	const handleSubmit = () => {
		if(mailM == "" || bioM == ""){
			window.alert("Please fill all required fields");
		}
		else{
			var updateData = {
				email: mailM,
			}
			axios.patch(`http://127.0.0.1:8080/api/v1/customer/updateUserInfoByUId/${uid}/${mailM}`).then(
				(response) => {
					console.log(mailM);
					window.alert("Profile Updated");
					update_mail(mailM)

				}).catch((err) => console.log(err.response));
			}
		props.changeBio(bioM);
		setIsClicked(true)
	}



	return(
		<div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Edit Profile</h5>
						<button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						{isClicked ? <h3>Succesfully Changed</h3>: <></>}
						<form>
							<div class="form-group">
								<label for="exampleFormControlInput1">Email address:</label>
								<input onChange = {(e) => setMailM(e.target.value)} type="email" class="form-control" id="exampleFormControlInput1" placeholder={email}/>
  							</div>

							<div class="form-group">
								<label for="exampleFormControlTextarea1">Bio:</label>
								<textarea onChange = {(e) => {setBioM(e.target.value)}}class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="button" onClick = {()=>handleSubmit()}class="btn btn-primary"  >Save changes</button>
					</div>
				</div>
			</div>
		</div>
	)
}
const mapStateToProps = (state) => {
	return {
		email: state.email,
		name: state.name,
		uid: state.uid
	}
}
const mapDispatchToProps = (dispatch) => {
	return {
		update_mail: (mail) => {
			dispatch({
				type: "UPDATE_MAIL",
				payload: {email:mail}
			})
		}
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(EditProfileModal);