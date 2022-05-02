import React from 'react'
import "./css/Sidebar.css"
import HomeIcon from "./img/House.png"
import PersonIcon from "./img/user-logo.png"
import RentIcon from "./img/rentIcon.png"
import LogoutIcon from "./img/logout.png"

import {Link} from 'react-router-dom'

const Sidebar = () => {
	return (
		<div className="sidebar">
			<aside className="d-flex flex-column">
				<div className="blankSpace">

				</div>
				<Link to="/home" className="text-decoration-none text-white ">
					<section className="sidebar-link mb-1 mt-1 hover-effect"> <img src={HomeIcon} className="ms-lg-2 sidebar-icon " /><span className="ms-lg-2 ms-1">Home</span></section>
				</Link>
				<Link to="/profile" className="text-decoration-none text-white">
					<section className="sidebar-link mb-1 hover-effect"><img src={PersonIcon} className="sidebar-icon ms-lg-2" /><span className="ms-lg-2 ms-1">Profile</span></section>
				</Link>
				<Link to="/rent" className="text-decoration-none text-white">
					<section className="sidebar-link mb-1 hover-effect"><img src={RentIcon} className="ms-lg-2 sidebar-icon" /><span className="ms-lg-2 ms-1">Rent</span></section>
				</Link>
				<Link to="/" className="text-decoration-none text-white bottomBlank">
					<section className="sidebar-link mb-1 hover-effect"><img src={LogoutIcon} className="ms-lg-2 sidebar-icon" /><span className="ms-lg-2 ms-1">Logout</span></section>
				</Link>
				
				
			</aside>
		</div>
	)
}
export default Sidebar;