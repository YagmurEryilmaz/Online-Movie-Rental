import { useState } from "react";
import "./changeAvatarModal.css";

const ChangeAvatarModal = (props) =>{
	const [avatar, setAvatar] = useState(props.def_avatar);
	const avatarsArr = props.avatars;
	const changePP = () => {
		props.changeAvatar(avatar);

	}
	const handleClick = (a) =>{
		setAvatar(a);
		
	}

	return(
		<div class="modal fade" data-bs-backdrop = "static" data-keyboard ="false"id="changeAvatar" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-lg modal-dialog-centered" data-backdrop="static" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
						<button type="button" className="close" data-bs-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" data-backdrop="static">
						<div className="h3">Choose an Avatar</div>
						<div className="h3 ">Avatar to be Changed: 
							<img src={avatar} className= " curAvt"alt="curAvatar" />
						</div>
						<div className="row">
						
							
							{avatarsArr.map((avatars) => {
								return(
									<div className="col-3 avatarButton">
										<a href = "#" classname = "avatarButton "onClick = {()=> handleClick(avatars)}>											
												<img className = "avatarImg" src={avatars} alt= "avatar" />											
										</a>
									</div>
									
								)
							})}
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" data-bs-dismiss = "modal" onClick = {() => {
							changePP()
						}}>Save changes</button>	
					</div>
				</div>
			</div>
		</div>
	)
}
export default ChangeAvatarModal;