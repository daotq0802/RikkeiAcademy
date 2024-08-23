let currentStatus = 0
const login = document.querySelector('#loginModal')
const regis = document.querySelector('#registerModal')
const loginButton = document.querySelector('.loginButton')
const backToLogin = document.querySelector('#btLogin')

let showModal = (modalName) => {
    if (modalName) {
        const modal = new bootstrap.Modal(modalName);
        modal.show();
    }
}
loginButton.addEventListener('click', () => {
    showModal(login)
})
backToLogin.addEventListener('click', () => {
    showModal(login)
})

document.querySelector('.goToRegister').addEventListener('click', () => {
    showModal(regis)
})

// ! Login Form
// const loginForm = document.querySelectorAll('form')[0]
const login_button = document.querySelector('#login-button')
const loginUser = document.querySelectorAll('.login-input')[0]
const loginPassword = document.querySelectorAll('.login-input')[1]
const dataAccount = JSON.parse(localStorage.getItem('Account')) || []

function loginMethod() {
    let index = dataAccount.findIndex(item => { return item.password === loginPassword.value && item.name === loginUser.value })
    if (index != -1) {
        loginButton.style.display = 'none'
        document.querySelector('.user-infor').removeAttribute('hidden')
        document.querySelector('.user-infor i').setAttribute('hidden', true)
        document.querySelector('.user-infor span').innerHTML = `Hello, <strong>${dataAccount[index].name}</strong>`
        document.querySelector('.ID-handler').value = dataAccount[index].id
        currentStatus = 1
    } else {
        loginForm.reset()
    }
}
// login_button.addEventListener('click', (e) => {
//     e.preventDefault()
//     let index = dataAccount.findIndex(item => { return item.password === loginPassword.value && item.name === loginUser.value })
//     if (index != -1) {
//         loginButton.style.display = 'none'
//         document.querySelector('.user-infor').removeAttribute('hidden')
//         document.querySelector('.user-infor i').setAttribute('hidden', true)
//         document.querySelector('.user-infor span').innerHTML = `Hello, <strong>${dataAccount[index].name}</strong>`
//         document.querySelector('.ID-handler').value = dataAccount[index].id
//         currentStatus = 1
//     } else {
//         loginForm.reset()
//     }
// })

// ! Register Form
const registerForm = document.querySelectorAll('form')[1]
const regisUser = document.querySelectorAll('.regis-input')[0]
const regisEmail = document.querySelectorAll('.regis-input')[1]
const regisAge = document.querySelectorAll('.regis-input')[2]
const regisPassword = document.querySelectorAll('.regis-input')[3]
const regisPhone = document.querySelectorAll('.regis-input')[5]
const validEmail = /[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}/igm
function checkUserEmail(email) {
    let validEmail = dataAccount.find(user => {
        user.email === email
        return true
    })
    return false
}

function registerMethod() {
    let index = dataAccount.findIndex(item => { return item.email === regisEmail.value })
    if (index == -1 || dataAccount.length < 1) {
        if (!checkUserEmail()) {
            if (validEmail.test(regisEmail.value)) {
                let user = new User(regisUser.value, regisAge.value,
                    regisEmail.value, regisPassword.value, regisPhone.value)
                dataAccount.push(user);
                localStorage.setItem('Account', JSON.stringify(dataAccount))
            }
        }
        else {
            alert('Not Good')
        }
    } else {
        alert('Not Good')
    }
}
// registerForm.addEventListener('submit', (e) => {
//     e.preventDefault()
//     let index = dataAccount.findIndex(item => { return item.email === regisEmail.value })
//     if (index == -1 || dataAccount.length < 1) {
//         if (regisEmail.value.matches(validEmail) && !checkUserEmail()) {
//             let user = new User(regisUser.value, regisAge.value,
//                 regisEmail.value, regisPassword.value, regisPhone.value)
//             dataAccount.push(user);
//             localStorage.setItem('Account', JSON.stringify(dataAccount))
//             console.log(user);
//             registerForm.reset()
//         }
//         else {
//             alert('Not Good')
//         }
//     } else {
//         alert('Not Good')
//     }
// })

// ! Get Product
const productView = document.querySelector('.product-contain')
let dataProduct = JSON.parse(localStorage.getItem('Product'))
showProduct = () => {
    let product_html = '';
    dataProduct.forEach(item => {
        product_html += `<div class="card card-item" style="width: 18rem;">
                <img src="${item.image}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">${item.description}</p>
                    <a onclick="addToCart('${item.id}')" class="btn btn-primary">Add to Cart</a>
                </div>
            </div>`
    })
    productView.innerHTML = product_html
}

// ! Add Product to Shopping Cart
let dataCart = JSON.parse(localStorage.getItem('ShoppingCart')) || []
let indexCart = 0;
let indexCartList = 0;
let listCart = []

function checkCart(id) {
    for (let i = 0; i < dataCart[indexCart].cartList.length; i++) {
        if (dataCart[indexCart].cartList[i].id === id) {
            return indexCartList = i
        }
    }
    return indexCartList = -1
}

function checkUserInCart() {
    for (let i = 0; i < dataCart.length; i++) {
        if (dataCart[i].userId === document.querySelector('.ID-handler').value) {
            return indexCart = i
        }
    }
    return indexCart = -1
}

function addToCart(id) {
    let productIndex = dataProduct.findIndex(product => { return product.id === id })

    if (currentStatus == 0) {
        showModal(login)
        return
    } else {
        try {
            checkUserInCart(id)
            checkCart(id)
        } catch (error) {
        }
        if (dataCart.length < 1 || indexCart == -1) {
            listCart.push({
                id: dataProduct[productIndex].id, name: dataProduct[productIndex].name, price: dataProduct[productIndex].price,
                description: dataProduct[productIndex].description, role: dataProduct[productIndex].role, quantity: 1
            })
            dataCart.push(new ShoppingCart(document.querySelector('.ID-handler').value, listCart))
            localStorage.setItem('ShoppingCart', JSON.stringify(dataCart))
        } else {
            if (indexCart != -1 && indexCartList == -1) {
                dataCart[indexCart].cartList.push({
                    id: dataProduct[productIndex].id, name: dataProduct[productIndex].name, price: dataProduct[productIndex].price,
                    description: dataProduct[productIndex].description, role: dataProduct[productIndex].role, quantity: 1
                })
                localStorage.setItem('ShoppingCart', JSON.stringify(dataCart))
            } else {
                dataCart[indexCart].cartList[indexCartList].quantity++
                localStorage.setItem('ShoppingCart', JSON.stringify(dataCart))
            }
        }
    }
}