const review = document.querySelector('.listProduct pre')
const cartList = document.querySelector('.cartList pre')
const sumPrice = document.querySelector('.sumPrice pre')
const numberPattern = /\B(?=(\d{3})+(?!\d))/g;
class Product {
    constructor(name, price, quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

const listProduct = [];
const shoppingCart = [];

listProduct.push(new Product('Iphone 15', 12000000, 4))
listProduct.push(new Product('Ferrari', 800000000, 5))
listProduct.push(new Product('Boat', 15600000, 4))
localStorage.setItem('ListProduct', JSON.stringify(listProduct))

function showAllList() {
    const list = JSON.parse(localStorage.getItem('ListProduct'));
    let stringList = ''
    list.forEach(element => {
        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<div>${textKey}: ${element[key]}</div>`
        }
        stringList += `<div><button onclick="addToCart('${element.name}', ${element.price}, 1)">Add To Cart</button></div><br>`
    })
    review.innerHTML = stringList
}

function checkInCart(name) {
    const list = JSON.parse(localStorage.getItem('ShoppingCart'))
    let index = list.findIndex(element => element.name === name);
    if (index != -1) {
        return index
    } else {
        return -1
    }
}

function addToCart(productName, price, quantity) {
    document.querySelector('.cartList').style.display = 'block'
    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    if (list === null) {
        shoppingCart.push(new Product(productName, price, quantity));
        localStorage.setItem('ShoppingCart', JSON.stringify(shoppingCart))
        displayCart()
        calculateTotal()
    } else {
        let index = checkInCart(productName)
        switch (index) {
            case -1:
                list.push(new Product(productName, price, quantity));
                localStorage.setItem('ShoppingCart', JSON.stringify(list))
                displayCart()
                calculateTotal()
                break;
            case index:
                list[index].quantity++
                localStorage.setItem('ShoppingCart', JSON.stringify(list))
                displayCart()
                calculateTotal()
                break;
        }
    }
}

function displayCart() {
    const list = JSON.parse(localStorage.getItem('ShoppingCart'));
    let stringList = ''
    list.forEach(element => {
        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<div>${textKey}: ${element[key]}</div>`
        }
        stringList += `<div><button onclick="removeFromCart('${element.name}')">Delete</button></div><br>`
    })
    cartList.innerHTML = stringList
}

function removeFromCart(productName) {
    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    let index = checkInCart(productName)
    if (index != -1) {
        list.splice(index, 1)
        localStorage.setItem('ShoppingCart', JSON.stringify(list))
        displayCart()
    }
    calculateTotal()
}
function clearCart() {
    localStorage.removeItem('ShoppingCart')
    document.querySelector('.cartList').style.display = 'none'
    sumPrice.innerHTML = `Total: 0`
}

function calculateTotal() {
    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    let sum = 0
    list.forEach(element => {
        sum += element.price * element.quantity
    })
    sumPrice.innerHTML = `Total: ${sum.toString().replace(numberPattern, ",")}`
}