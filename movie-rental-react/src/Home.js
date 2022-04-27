import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
const Home = ()=>{
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
				</div>
			</div>

			
			
			
		</div>
	)
}
export default Home;