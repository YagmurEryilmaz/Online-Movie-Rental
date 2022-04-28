import { useState } from "react";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import ChangeAvatarModal from "./ChangeAvatarModal";
import defaultAvatar from "./img/avatars/default_avatar.png";
import avatar1 from "./img/avatars/image_part_001.png";
import avatar2 from "./img/avatars/image_part_002.png"
import avatar3 from "./img/avatars/image_part_003.png"
import avatar4 from "./img/avatars/image_part_004.png"
import avatar5 from "./img/avatars/image_part_005.png"
import avatar6 from "./img/avatars/image_part_006.png"
import avatar7 from "./img/avatars/image_part_007.png"
import avatar8 from "./img/avatars/image_part_008.png"
import avatar9 from "./img/avatars/image_part_009.png"
import "./Profile.css"
import { user_data } from "./Data";

const Profile = () => {
	const [profilePhoto, setProfilePhoto] = useState(defaultAvatar);
	const changePP = (avatar) =>{
		setProfilePhoto(avatar);
	}
	const avatars = [
		avatar1, avatar2, avatar3, avatar4, avatar5 , avatar6, avatar6, avatar7, avatar8, avatar9
	]
	
	return(
		<div className='container'>
			<div className="row">
				<div className="d-none d-lg-block col-md-2 px-0">
					<Sidebar />
				</div>
				<div className="col-md-10">
					<div className='row  align-items-between'>
						<Navbar />
					</div>
					<div className="row">
						<div className="col-6 mt-5">
							<div class="card ">
								<div className=" d-flex justify-content-center">
									<img src= {profilePhoto} className=" card-img-top" alt="..."/>
								</div>
									<div class="card-body">
										<h5 class="card-title">{user_data[0].name}</h5>
										<p class="card-text">Lorem ipsum dolor sit, amet consectetur adipisicing elit.</p>
									</div>
									<ul class="list-group list-group-flush">
									<li class="list-group-item"><span className="fw-bold">Email: </span>{user_data[0].email}</li>
									<li class="list-group-item"><span className = "fw-bold">Birthday: </span>{user_data[0].birthday}</li>

									</ul>
									<div class="card-body">

										<a data-bs-toggle="modal" href="#changeAvatar" class="card-link">Edit Profile  </a>

										<a data-bs-toggle= "modal" href="#changeAvatar" class="card-link">Change Avatar <ChangeAvatarModal/></a>
									</div>
							</div>
						</div>
						<div className="col-6">
							<div className="row">

							</div>
							<div className="row">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	)

}
export default Profile;