const review = document.querySelector('.msg pre')
const prdForm = document.querySelector('.productForm')
let prdList = [];

prdForm.addEventListener('submit', (event) => {
    event.preventDefault();
    let product = {
        id: Date.now(),
        name: document.querySelector('#name').value,
        price: document.querySelector('#price').value,
        quantity: document.querySelector('#quantity').value
    }
    prdList.push(product)
    document.forms[0].reset()
    localStorage.setItem('ProductList', JSON.stringify(prdList));
    showAllList()
})

function showAllList() {
    let list = JSON.parse(localStorage.getItem('ProductList'));
    let keySorted = list.sort((a, b) => { return a.price - b.price })
    let stringList = ''
    keySorted.forEach(element => {
        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<div>${textKey}: ${element[key]}</div>`
        }
        stringList += `<br>`
    })
    review.innerHTML = stringList;
}