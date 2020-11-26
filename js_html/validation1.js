var continueBtn = document.getElementById("Continue");
const correctImage = '<img src="./correct.png" height=\"20px\"> ';
const wrongImage = '<img src="./wrong.png" height=\"20px\"> ';

continueBtn.onclick = function() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var gender = document.getElementById("Gender").value;
    var state = document.getElementById("State").value;
    var myhint = document.getElementById("hint");

    
if((validateFirstName(firstName)&validateLastName(lastName)&validateGender(gender)&validateState(state))==false){
    myhint.style["visibility"] = "visible";
}else{
    myhint.style["visibility"] = "hidden";
    location.href = "./validation2.html";
}

    
    
}

//Must contain only alphabetic or numeric characters.
function validateFirstName(s){
    var letterNumber = /^[0-9a-zA-Z]+$/;
    
    if(s.match(letterNumber)){
        document.getElementById("firstNameCheck").innerHTML=correctImage;
        document.getElementById("rule1").style.color="white";
        return true;
    }else{
        document.getElementById("firstNameCheck").innerHTML=wrongImage;
        document.getElementById("rule1").style.color="#FA8B74";
        return false;
       
    }
}

function validateLastName(s){
    var letterNumber = /^[0-9a-zA-Z]+$/;
    
    if(s.match(letterNumber)){
        document.getElementById("lastNameCheck").innerHTML=correctImage;
        document.getElementById("rule2").style.color="white";
        return true;
    }else{
        document.getElementById("lastNameCheck").innerHTML=wrongImage;
        document.getElementById("rule2").style.color="#FA8B74";
        return false;
       
    }
}

function validateGender(s){
    if(s!="---select---"){
        document.getElementById("genderCheck").innerHTML=correctImage;
        document.getElementById("rule3").style.color="white";
        return true;
    }else{
        document.getElementById("rule3").style.color="#FA8B74";
        document.getElementById("genderCheck").innerHTML=wrongImage;
        return false;
    }
}

function validateState(s){
    if(s!="---select---"){
        document.getElementById("stateCheck").innerHTML=correctImage;
        document.getElementById("rule4").style.color="white";
        return true;
    }else{
        document.getElementById("stateCheck").innerHTML=wrongImage;
        document.getElementById("rule4").style.color="#FA8B74";
        return false;
    }
}