import { connect } from "react-redux"
const CartModal = ({cart, remove_from_cart}) => {
	const cartTotal = cart.reduce((acc, curr) => acc + curr.price, 0);
	return(
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
						<button type="button" className="btn btn-primary">Checkout</button>
					</div>
				</div>
			</div>
		</div>

	)


}
const mapStateToProps = state => {
	return {
	cart: state.cart
  };
}
const mapDispatchToProps = dispatch => {
	return {
		remove_from_cart: (movie) => dispatch({type: "REMOVE_FROM_CART", payload:{movie: movie}})
	}
}
export default connect(mapStateToProps, mapDispatchToProps)(CartModal);
