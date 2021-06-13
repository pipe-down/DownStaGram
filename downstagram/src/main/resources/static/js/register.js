const idInput = document.getElementById('loginId');
const pwInput = document.getElementById('1');
const loginInput = document.getElementsByClassName('container2')[0];
const loginBtn = document.getElementById('btn_login');
const linkToMain = document.getElementsByTagName('a')[0];

function idCheck() {
    var hasAt = idInput.value.indexOf('@');
    return hasAt !== -1 ? true : false;
}

function pwCheck() {
    return pwInput.value.length >= 8 ? true : false;
}

document.addEventListener('keyup', function(event) {
    if (event.keyCode === 13) {
        loginBtn.click();
    }
})