function myFunction() {
	var popup = document.getElementById("popup"),
	fname = document.getElementById("fname").value, 
	lname = document.getElementById("lname").value,
	phone = document.getElementById("phone").value,
	email = document.getElementById("email").value,
	message = document.getElementById("message").value;

	document.getElementById("fnamePopup").innerText=fname;
	document.getElementById("lnamePopup").innerText=lname;
	document.getElementById("phonePopup").innerText=phone;
	document.getElementById("emailPopup").innerText=email;
	document.getElementById("messagePopup").innerText=message;

	if(fname && lname && email && message){
		popup.classList.add('visible');
	}
}


function cerrar(){

	document.getElementById("popup").classList.remove('visible');
}