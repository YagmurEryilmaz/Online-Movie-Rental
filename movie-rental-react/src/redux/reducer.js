const reducer = (state,action) =>{
	if(action.type === "LOGIN"){
		var theName = action.payload.name;
		var theAccType = action.payload.role
		var theEmail = action.payload.email;
		var theBalance = action.payload.balance;
		return{
			...state,
			...action.payload,
			name: theName,
			accountType: theAccType,
			loggedIn :true,
			email: theEmail,
			uid: action.payload.uid


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
			email: "",
			friendRequests: [],
			uid: -1,
			numOfRequests: 0,
			allMovies: []

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
	if(action.type == "FETCH_REQUESTS"){
		var theNewRequests = []

		return{
			...state,
			friendRequests: action.payload.requests,
			numOfRequests: action.payload.requests.length + state.gifts.length
		}

	}
	if(action.type == "FETCH_GIFTS"){
		var theNewGifts = []
		

		return{
			...state,
			gifts: action.payload.gifts,
			numOfRequests: action.payload.gifts.length + state.friendRequests.length
		}

	}
	if(action.type == "ACCEPT_REQUEST"){
		var theNewRequests = []
		var theNewNumOfRequests = state.numOfRequests - 1
		if(action.payload){
			theNewRequests = state?.friendRequests.filter(request => request !== action.payload.request)
		}
		return{
			...state,
			friendRequests: theNewRequests,
			numOfRequests: theNewNumOfRequests
		}

		
	}
	if(action.type =="ACCEPT_GIFT"){
		var theNewGifts = []
		var theNewNumOfRequests = state.numOfRequests - 1
		if(action.payload){
			theNewGifts = state?.gifts.filter(gift => gift !== action.payload.gift)
		}
		return{
			...state,
			gifts: theNewGifts,
			numOfRequests: theNewNumOfRequests
		}
	}
	if(action.type == "EMPTY_CART"){
		return{
			...state,
			cart: []
		}
	}
	if(action.type == "FETCH_MOVIES"){
		return{
			...state,
			allMovies: action.payload
		}
	}
	if(action.type == "DELETE_MOVIE"){
		var theNewMovies = []
		if(action.payload){
			theNewMovies = state?.allMovies.filter(movie => movie !== action.payload.movie)
		}
		return{
			...state,
			allMovies: theNewMovies
		}
	}
	if(action.type === "DET_MOVIE"){
		return{
			...state,
			detMovie: action.payload
		}
	}
	if(action.type === "UPDATE_BALANCE"){
		return{
			...state,
			balance: action.payload.balance
		}
	}
	return state;
}
export default reducer;