const reducer = (state,action) =>{
	if(action.type === "LOGIN"){
		var theName = action.payload.name;
		var theAccType = action.payload.role
		var theEmail = action.payload.email;
		return{
			...state,
			...action.payload,
			name: theName,
			accountType: theAccType,
			loggedIn :true,
			email: theEmail

		}

		
	}
	if(action.type === "LOGOUT"){
		console.log("logout")
		return{
			...state,
			name: "",
			loggedIn: false,
			balance: 0,
			rentedMovies: [],
			accountType: "",
			cart: [],

		}
	}
	if(action.type == "ADD_TO_CART"){
		var theNewCart = []
		if(!action.payload.movie?.length){
			theNewCart = [...state?.cart, action.payload.movie]
		}
		return{
			...state,
			cart: theNewCart
		}
	}
	if(action.type == "REMOVE_FROM_CART"){
		var theNewCart = []
		if(!action.payload.movie?.length){
			theNewCart = state?.cart.filter(movie => movie !== action.payload.movie)
		}
		return{
			...state,
			cart: theNewCart
		}
	}
	if(action.type =="UPDATE_MAIL"){
		console.log(action.payload);
		return{
			...state,
			email: action.payload.email
		}
	}

	return state;
}
export default reducer;