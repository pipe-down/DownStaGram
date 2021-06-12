const idInput = document.getElementById('loginId');
const pwInput = document.getElementById('passwd');
const loginInput = document.getElementsByClassName('container')[0];
const loginBtn = document.getElementById('btn_login');
const linkToMain = document.getElementsByTagName('a')[0];

function idCheck() {
    var hasAt = idInput.value.indexOf('@');
    return hasAt !== -1 ? true : false;
}

function pwCheck() {
    return pwInput.value.length >= 5 ? true : false;
}

loginInput.addEventListener('keyup', function(event) {
    const completedInput = (idCheck() && pwCheck());
    loginBtn.disabled = completedInput ? false : true;
    linkToMain.href = completedInput ? "/guest/register" : "#none";
})

document.addEventListener('keyup', function(event) {
    if (event.keyCode === 13) {
        loginBtn.click();
    }
})