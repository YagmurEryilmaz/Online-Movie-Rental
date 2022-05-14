const reducer = (state,action) =>{
	if(action.type === "LOGIN"){
		var theName = action.payload.name;
		var theAccType = action.payload.role
		return{
			...state,
			...action.payload,
			name: theName,
			accountType: theAccType,
			loggedIn :true

		}

		
	}
	return state;
}
export default reducer;