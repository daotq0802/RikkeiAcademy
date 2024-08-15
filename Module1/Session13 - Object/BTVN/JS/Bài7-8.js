const review = document.querySelector('.msg pre')
const loginRegisForm = document.querySelector('.regisLoginForm')

//Register Element
const regisUsername = document.querySelector('#regisUsername')
const regisEmail = document.querySelector('#regisEmail')
const regisPassword = document.querySelector('#regisPassword')
const flags = "gm";
const pattern = "[A-Za-z0-9\._%+\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z]{2,}"
const regexPattern = new RegExp(pattern, flags);

//Login Element
const loginUsername = document.querySelector('#loginUsername')
const loginPassword = document.querySelector('#loginPassword')

let listAccount = []
function regisAccount() {
    let account = {
        id: Date.now(),
        username: regisUsername.value,
        email: regisEmail.value,
        password: regisPassword.value
    }
    let valid = listAccount.findIndex(element => element.email == regisEmail.value)
    if (valid == -1) {
        if (regisUsername.value != '' &&regisEmail.value != '' &&regisPassword.value != '' && regisUsername.value.length >= 3 && regisPassword.value.length >= 8) {
            if (regisEmail.value.match(pattern)) {
                listAccount.push(account)
                localStorage.setItem('ListAccount', JSON.stringify(listAccount))
            } else {
                alert('Email invalidated')
            }
        } else {
            alert('Wrong Input')
        }
    } else {
        alert('Email has been exist')
    }
    document.forms[0].reset()
}

function loginAccount() {
    const list = JSON.parse(localStorage.getItem('ListAccount'))
    let validUsername = list.findIndex(element => element.username === loginUsername.value)
    let validPassword = list.findIndex(element => element.password === loginPassword.value)
    if (validUsername == validPassword ) {
        alert('Login Success!!')
        review.innerHTML = `<div>ID: ${list[validUsername].id}<br>Username: ${list[validUsername].username}<br>Email: ${list[validUsername].email}<br>Password: ${list[validUsername].password}</div>`
    } else {
        alert(`Doesnt Exist Account With Username ${loginUsername} or Wrong Password`)
        review.innerHTML = ``
    }
    document.forms[0].reset()
}

function moveToRegis() {
    document.querySelector('.regisForm').style.display = 'block'
    document.querySelector('.loginForm').style.display = 'none'
}

function moveToLogin() {
    document.querySelector('.regisForm').style.display = 'none'
    document.querySelector('.loginForm').style.display = 'block'
}