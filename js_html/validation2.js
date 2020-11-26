var submitBtn = document.getElementById("Submit");
const correctImage = '<img src="./correct.png" height=\"20px\"> ';
const wrongImage = '<img src="./wrong.png" height=\"20px\"> ';

submitBtn.onclick=function(){
    var email = document.getElementById("Email").value;
    var phone = document.getElementById("Phone").value;
    var address = document.getElementById("Address").value;
    var myhint = document.getElementById("hint");


    if((validateEmail(email)&validatePhone(phone)&validateAddress(address))==false){
        myhint.style["visibility"] = "visible";
    }else{
        myhint.style["visibility"] = "hidden";
    }

}

function validateEmail(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0]) &&alphaNumCheck(periodSplit[1])) {
            document.getElementById("EmailCheck").innerHTML=correctImage;
            document.getElementById("rule1").style.color="white";
            return true;
        }
    }
    //valCheck = false;
    document.getElementById("EmailCheck").innerHTML=wrongImage;
    document.getElementById("rule1").style.color="#FA8B74";
    return false;
}

function validatePhone(phone){
    gangSplit = phone.split('-');
    if(gangSplit.length==3 && numCheck(gangSplit[0]) && gangSplit[0].length==3 
    && numCheck(gangSplit[1]) &&gangSplit[1].length==3 
    && numCheck(gangSplit[2]) && gangSplit[2].length==4){
        document.getElementById("PhoneCheck").innerHTML=correctImage;
        document.getElementById("rule2").style.color="white";
        return true;
    }else if( phone.length==10 && numCheck(phone) ) {
        document.getElementById("PhoneCheck").innerHTML=correctImage;
        document.getElementById("rule2").style.color="white";
        return true;
    }
    else{
        document.getElementById("PhoneCheck").innerHTML=wrongImage;
        document.getElementById("rule2").style.color="#FA8B74";
    }
}

function validateAddress(address){
    commaSplit = address.split(',');
    if(commaSplit.length==2 && alphaCheck(commaSplit[0]) &&alphaCheck(commaSplit[1]) ){
        document.getElementById("AddressCheck").innerHTML=correctImage;
        document.getElementById("rule3").style.color="white";
        return true;
    }
    document.getElementById("AddressCheck").innerHTML=wrongImage;
    document.getElementById("rule3").style.color="#FA8B74";
}

function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function numCheck(entry) {
    let regex = /^[0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function alphaCheck(entry){
    let regex = /^[a-zA-Z]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
    
}

