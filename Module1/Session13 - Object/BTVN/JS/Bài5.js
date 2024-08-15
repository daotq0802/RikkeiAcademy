const bookForm = document.querySelector('.bookForm')
const review = document.querySelector('.msg pre')
let bookList = []

bookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    let book = {
        name: document.querySelector('#name').value,
        author: document.querySelector('#author').value
    }
    bookList.push(book);
    document.forms[0].reset()
    localStorage.setItem('BookList', JSON.stringify(bookList))
    showAllList();
})

function showAllList() {
    let list = JSON.parse(localStorage.getItem('BookList'));
    let stringList = ''
    list.forEach(element => {
        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<div>${textKey}: ${element[key]}</div>`
        }
        stringList += '<br>'
    })
    review.innerHTML = stringList
}

function search() {
    let input = prompt('Nhập tên Author muốn tìm')
    let list = JSON.parse(localStorage.getItem('BookList'));
    let stringList = ''
    let index = 0
    for (let i = 0; i < list.length; i++) {
        if (list[i].author == input) {
            index = i;
            stringList += `<div>Name: ${list[i].name}<br>Author: ${list[i].author}</div><br>`
        }
    }
    review.innerHTML = stringList
}