const review = document.querySelector('.msg pre')
const prdForm = document.querySelector('.productForm')
let data = JSON.parse(localStorage.getItem('ProductList')) || [];

prdForm.addEventListener('submit', (event) => {
    event.preventDefault();
    let product = {
        id: Date.now(),
        name: document.querySelector('#name').value,
        price: document.querySelector('#price').value,
        quantity: document.querySelector('#quantity').value
    }
    data.push(product)
    document.forms[0].reset()
    localStorage.setItem('ProductList', JSON.stringify(data));
    showAllList()
})

function showAllList() {
    let keySorted = data.sort((a, b) => { return a.price - b.price })
    let stringList = ''
    keySorted.forEach(element => {
        stringList += '<div class="item">'

        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<p>${textKey}: ${element[key]}</p>`
        }
        stringList += `</div>`
    })
    review.innerHTML = stringList;
}