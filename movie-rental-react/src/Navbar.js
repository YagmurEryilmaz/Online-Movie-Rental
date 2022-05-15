import {useEffect, useState} from "react";
import {Link} from 'react-router-dom'
import Badge from '@mui/material/Badge';
import MailIcon from '@mui/icons-material/Mail';
import NotificationModal from "./NotificationModal";

import { IconButton } from "@mui/material";
import { connect } from "react-redux";
import CartModal from "./CartModal";

import "./Navbar.css";
import axios from "axios";

const Navbar = ({name, balance, role }) =>{
	console.log()
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
						<Link to="/home">
							<a className="navbar-brand" ><div className="tickflix-logo"></div></a>
						</Link>

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
							{
								(role === "customer") ? <a href = "#cartModal" data-bs-toggle = "modal"className="d-none d-lg-block cart-logo mx-4"></a>:(null)
							}
							<Link to = "/profile">
								<a className= "d-none d-lg-block user-logo "></a>
							</Link>
							<div className="d-none d-lg-block mx-4">
								<div>
									<div className="username">{name}</div>
									<div className="credit text-light">{balance} $</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<CartModal/>
				<NotificationModal/>
			</nav>

		</>
	)
}
const mapStateToProps = state =>
{

	return {
		name:state.name,
		balance:state.balance,
		role: state.accountType,
	}
}

export default connect(mapStateToProps)(Navbar);