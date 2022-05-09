import {useEffect, useState} from "react";
import {Link} from 'react-router-dom'
import Badge from '@mui/material/Badge';
import MailIcon from '@mui/icons-material/Mail';
import NotificationModal from "./NotificationModal";
import { IconButton } from "@mui/material";


import "./Navbar.css";
import axios from "axios";
const Navbar = ({name, surname, accountType}) =>{
	const [click, setClick] = useState(false)
	const [numOfFriendRequests, setNumOfFriendRequests] = useState(0)

	useEffect(() => {
		axios.get("http://127.0.0.1:8080/api/v1/customer/getNumOfReceivedRequests").then((response)=>{
			setNumOfFriendRequests(response.data)
		}).catch((error)=>{console.log(error)})

	}, []);

	return (
		<>
			<nav className="navbar navbar-expand-lg tickflix-bg container d-flex">

				<div className="row navbar-content-area">
					<div className="col-lg-7 col-md-12 d-flex offset-1 offset-md-0 justify-content-md-center justify-content-center align-items-center">
						<a className="navbar-brand" href="/home"><div className="tickflix-logo"></div></a>
						<span className="navbar-logo-text ml-md-4 ml-1">TickFlix</span>
						<span className="navbar-name-text ml-lg-4 ml-3 px-2 d-none d-sm-inline">Online Movie Rental</span>
					</div>
					<div className="col-lg-5 d-flex align-items-center">
						<div className="ml-auto d-flex align-items-center">
							<a href="#notifications" data-bs-toggle = "modal">

							<IconButton size = "large">

								<Badge badgeContent={numOfFriendRequests} color="primary">
									<MailIcon fontSize = "inherit" style ={{color: 'white'}} />
								</Badge>
							</IconButton>
							</a>
							<a href = "#" className="d-none d-lg-block cart-logo mx-4"></a>
							<a href="/profile" className= "d-none d-lg-block user-logo "></a>
							<div className="d-none d-lg-block mx-4">
								<div>
									<div className="username">Can Önal</div>
									<div className="credit text-light">50.72 $</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<button onClick={() => setClick(!click)} className="navbar-toggler btn btn-block toggler-w mr-auto bg-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span className="navbar-toggler-icon"></span>
				</button>
				{click &&
					<div className="collapse navbar-collapse" id="navbarSupportedContent">
						<Link to="/home" className="text-decoration-none text-white">
							<section className="sidebar-link mb-1 mt-1 hover-effect nav-item text-white"><span
								className="ml-lg-3 ml-2">Home</span></section>
						</Link>
						<Link to="/myProfile" className="text-decoration-none text-white">
							<section className="sidebar-link mb-1 hover-effect "><span
								className="ml-lg-3 ml-2 text-light">Profile</span></section>
						</Link>
						<Link to="/tests" className="text-decoration-none text-white">
							<section className="sidebar-link mb-1 hover-effect"><span
								className="ml-lg-3 ml-2 text-light">Tests</span></section>
						</Link>
						<Link to="/campus" className="text-decoration-none text-white">
							<section className="sidebar-link mb-1 hover-effect"><span
								className="ml-lg-3 ml-2 text-light">Campus</span></section>
						</Link>

						{
							!(accountType === "staff") ? <Link to="/courses" className="text-decoration-none text-white">
								<section className="sidebar-link mb-1 hover-effect"><span
									className="ml-lg-3 ml-2 text-light">Courses</span></section>
							</Link> : null}

						<Link to="/" className="text-decoration-none text-white">
							<section className="sidebar-link hover-effect logout-div"><span
								className="ml-lg-3 ml-2 text-light">Logout</span></section>
						</Link>
					</div>
				}
			</nav>
			<NotificationModal/>
		</>
	)
}
export default Navbar;