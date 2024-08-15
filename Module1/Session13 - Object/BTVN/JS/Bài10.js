const review = document.querySelector('.listProduct pre')
const cartList = document.querySelector('.cartList pre')
const sumPrice = document.querySelector('.sumPrice pre')
const numberPattern = /\B(?=(\d{3})+(?!\d))/g;
const PRODUCT_STORAGE = 'ListProduct'
const CART_STORAGE = 'ShoppingCart'
class Product {
    constructor(name, price, quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

const listProduct = [];
listProduct.push(new Product('Iphone 15', 12000000, 4))
listProduct.push(new Product('Ferrari', 800000000, 5))
listProduct.push(new Product('Boat', 15600000, 4))
localStorage.setItem('ListProduct', JSON.stringify(listProduct))

//Hiện thị tất cả sản phẩm ra màn hình
function showAllList() {
    const list = JSON.parse(localStorage.getItem('ListProduct'));
    let stringList = ''
    list.forEach(element => {
        stringList += '<div class="item">'

        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<p>${textKey}: ${element[key]}</p>`
        }
        stringList += `<div><button onclick="addToCart('${element.name}', ${element.price}, 1)">Add To Cart</button></div></div>`
    })
    review.innerHTML = stringList
}

//Kiểm tra sản phẩm có trong danh sách hay không
function checkInCart(name) {
    const list = JSON.parse(localStorage.getItem('ShoppingCart'))
    let index = list.findIndex(element => element.name === name);
    if (index != -1) {
        return index
    } else {
        return -1
    }
}

//Thêm sản phẩm vào danh sách
function addToCart(productName, price, quantity) {
    document.querySelector('.cartList').style.display = 'flex'
    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    if (list == null) {
        const shoppingCart = [];
        shoppingCart.push(new Product(productName, price, quantity));
        addToLocalStorage(CART_STORAGE, shoppingCart)
    } else {
        //Gọi phương thức kiểm tra trùng lặp
        let index = checkInCart(productName)
        switch (index) {
            case -1:
                list.push(new Product(productName, price, quantity));
                addToLocalStorage(CART_STORAGE, list)
                break;
            case index:
                list[index].quantity++
                addToLocalStorage(CART_STORAGE, list)
                break;
        }
    }
}

//Hiển thị tất cả sản phẩm có trong giỏ hàng
function displayCart() {
    const list = JSON.parse(localStorage.getItem('ShoppingCart'));
    let stringList = ''
    if (list != null) {
        document.querySelector('.cartList .clearAll').style.display = 'block'
        list.forEach(element => {
            stringList += '<div class="item">'

            for (const key in element) {
                let textKey = key.split('').join('')
                textKey = textKey[0].toUpperCase() + textKey.slice(1)
                stringList += `<p>${textKey}: ${element[key]}</p>`
            }
            stringList += `<div><button onclick="removeFromCart('${element.name}')">Delete</button></div></div>`
        })
        cartList.innerHTML = stringList
        calculateTotal()
    } else {
        emptyList()
        try {
            if (list.length == 0) {
                emptyList()
            }
        } catch (error) {
            cartList.innerHTML = `Dont Have Any Product In Cart`
        }
    }
}

//Khi giỏ hàng trống
function emptyList() {
    document.querySelector('.cartList .clearAll').style.display = 'none'
    document.querySelector('.sumPrice').style.display = 'none'
}

//Xoá sản phẩm trong giỏ hàng
function removeFromCart(productName) {
    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    //Lấy vị trí sản phẩm
    let index = checkInCart(productName)
    switch (index) {
        case index:
            list.splice(index, 1)
            addToLocalStorage(CART_STORAGE, list)
            break;
    }
    if (list.length == 0) {
        cartList.innerHTML = `Dont Have Any Product In Cart`
        document.querySelector('.cartList .clearAll').style.display = 'none'
        document.querySelector('.sumPrice').style.display = 'none'
    }
}

//Xoá toàn bộ giỏ hàng
function clearCart() {
    localStorage.removeItem('ShoppingCart')
    displayCart()
}

//Tổng giá tiền của từng sản phẩm và tất cả sản phẩm
function calculateTotal() {
    document.querySelector('.sumPrice').style.display = 'block'

    let list = JSON.parse(localStorage.getItem('ShoppingCart'))
    let sum = 0
    let total = 0
    list.forEach(element => {
        sum = element.price * element.quantity
        element.sum = sum.toString().replace(numberPattern, ",")
        total += sum
    })
    localStorage.setItem(CART_STORAGE, JSON.stringify(list))
    sumPrice.innerHTML = `<div><label>Total: </label><span>${total.toString().replace(numberPattern, ",")}</span>`
}

function addToLocalStorage(storageName, list) {
    localStorage.setItem(storageName, JSON.stringify(list))
    calculateTotal()
    displayCart()
}