import axios from "axios";
import { connect } from "react-redux"
import {useState} from "react";
const CartModal = ({cart, remove_from_cart,uid,balance,update_balance, email, empty_cart}) => {

	const [option, setOption] = useState("balance")
	const cartTotal = cart.reduce((acc, curr) => acc + curr.price, 0);
	const checkout = () => {
		if(cartTotal > balance && option == "balance"){
			window.alert("Insufficient Funds")
		}
		else if(option == "balance" && cartTotal < balance){
			var newBalance = balance - cartTotal;
			var date = new Date();
			date.setDate(date.getDate() + 7);
			for (let index = 0; index < cart.length; index++) {
				var paymentObj = {
					movie: cart[index].mid,
					customerEmail: email,
					payStatus: "paid",
					expDate: date,
					payType: option
	
				}
				console.log(paymentObj)
	
				axios.post("http://127.0.0.1:8080/api/v1/payment/pay", paymentObj).then(
					(response) => {
						if(response.data == "rented"){
							window.alert("Movie rented")
						}
					}
				).catch((err) => console.log(err.response))
				
				
			}
			axios.patch(`http://127.0.0.1:8080/api/v1/customer/updateBalance/${uid}/${newBalance}`).then(
				(response) => {
					if(response){
						console.log("balance update")
						update_balance(newBalance)
						empty_cart();
					}
				}
			).catch((err) => console.log(err))
		}else{
			var date = new Date();
			date.setDate(date.getDate() + 7);
			for(let index = 0;index < cart.length;index++) {
				var paymentObj = {
					movie: cart[index].mid,
					customerEmail: email,
					payStatus: "card",
					expDate: date,
					payType: option

				}
				console.log(paymentObj)

				axios.post("http://127.0.0.1:8080/api/v1/payment/pay", paymentObj).then(
					(response) => {
						if(response.data == "rented") {
							window.alert("Movie rented")
							empty_cart();
						}
					}
				).catch((err) => console.log(err.response))
			}
		}

		
		
		
	}
	return(
		<>
		<div className="modal fade" id="cartModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div className="modal-dialog modal-lg" role="document">
				<div className="modal-content">
					<div className="modal-header">
						<h5 className="modal-title" id="exampleModalLabel">Cart</h5>
						<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div className="modal-body">
						<div className="card">
							<div className="card-header">
								{(cart.length === 0 ? "Your Cart Is Empty" : "Cart")}
							</div>
							<ul className="list-group list-group-flush">
								{cart.map((movie)=>{
									return(
										<li className="list-group-item">
											<span className="fw-bold">Movie Name:</span> <span>{movie.title}</span>
											<span className="mx-3 fw-light">Price: {movie.price}$</span>
											
											<div onClick = {() => remove_from_cart(movie)} className="float-end btn btn-danger">Remove</div>
										</li>
									)
								}
								)}
							</ul>
						</div>
						<div className="float-end"> <span className="fw-bold">Total:</span>  {cartTotal}$</div>
					</div>
					<div className="modal-footer">
						<button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="button" className="btn btn-primary" data-bs-target="#checkout" data-bs-toggle="modal" >Checkout</button>
					</div>
				</div>
			</div>
		</div>
			<div class="modal fade" id="checkout" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Payment</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div className="row">
								<div className="col-4 border border-info border-2">
									
									<div className="row mt-3">
										
										<h5>Your Cart Total:</h5>
										<h5>{cartTotal}$</h5>
									</div>
									<div className="row mt-5">
										<h5>Your Account Balance: {balance}$ </h5>
									</div>
								</div>

								<div className="col-6 ms-5">
									<form className="">
										
										<div className="row ">
											<div className="col-12">
												<div class="mb-3">
													<label for="exampleInputEmail1" class="form-label">Card Number</label>
													<input type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
												</div>
											</div>
										</div>
										<div className="row ">
											<div className="col-12">
												<div class="mb-3">
													<label for="exampleInputEmail1" class="form-label">Card Name</label>
													<input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
												</div>
											</div>
										</div>
										<div className="row float-end">
											<div className="col-6">
												<div class="mb-3">
													<label for="exampleInputEmail1" class="form-label">CVC</label>
													<input type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
												</div>
											</div>
											<div className="col-6">
												<div class="mb-3">
													<label for="exampleInputEmail1" class="form-label">Expiration Date</label>
													<input type="month" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
												</div>
											</div>
										</div>
										<div className="row float-end">
											<div className="col-6">
												<div class="form-check ms-3">
													<input class="form-check-input" type="radio" name="flexRadioDefault" onClick={() => setOption("balance")} id="flexRadioDefault1" checked/>
													<label class="form-check-label" for="flexRadioDefault1">
														Pay With Balance
													</label>
												</div>
											</div>
											<div className="col-6">
												<div class="form-check ms-3">
													<input class="form-check-input" type="radio" name="flexRadioDefault" onClick={() => setOption("card")} id="flexRadioDefault1" />
													<label class="form-check-label " for="flexRadioDefault1">
														Pay With Credit Card
													</label>
												</div>
											</div>
										</div>
										
									</form>
								</div>
									
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target= "#cartModal">Back to Cart</button>
							<button type="button" class="btn btn-success" onClick = {() => checkout()}>Pay</button>
						</div>
					</div>
				</div>
			</div>
		</>

	)


}
const mapStateToProps = state => {
	return {
	cart: state.cart,
	uid: state.uid,
	balance: state.balance,
	email: state.email
  };
}
const mapDispatchToProps = dispatch => {
	return {
		remove_from_cart: (movie) => dispatch({type: "REMOVE_FROM_CART", payload:{movie: movie}}),
		empty_cart: () => dispatch({type: "EMPTY_CART"}),
		update_balance: (balance) => dispatch({type: "UPDATE_BALANCE", payload:{balance: balance}})
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(CartModal);
