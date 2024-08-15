const review = document.querySelector('.review pre');
let inforForm = document.getElementById('inforForm');
let inforArray = [];

//Thêm Object vào Array
const handleSubmit = (ev) => {
    ev.preventDefault();
    let information = {
        id: Date.now(),
        name: document.querySelector('#name').value,
        phone: document.querySelector('#phone').value,
        address: document.querySelector('#address').value
    }
    inforArray.push(information);
    document.forms[0].reset();

    //Lưu vào Local Storage
    localStorage.setItem('MyListInformation', JSON.stringify(inforArray));
    showAllList()
}

//Hiển thị tất cả các phần tử đã lưu
function showAllList() {
    let list = JSON.parse(window.localStorage.getItem('MyListInformation'))
    let stringList = ''
    list.forEach(element => {
        stringList += '<div class="item">'
        for (const key in element) {
            let textKey = key.split('').join('');
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<p>${textKey}: ${element[key]}</p>`
        }
        stringList += `<div><button onclick={deleteAddress(${element.id})}>Xoá Address</button><button onclick={addEmail(${element.id})}>Thêm Email</button></div></div>`
    });
    review.innerHTML = stringList;
}
inforForm.addEventListener('submit', handleSubmit)

function deleteAddress(id) {
    let list = JSON.parse(window.localStorage.getItem('MyListInformation'))
    let index = list.findIndex(element =>
        element.id === id
    )
    delete list[index].address
    localStorage.setItem('MyListInformation', JSON.stringify(list));
    showAllList()
}

function addEmail(id) {
    document.querySelector('.address').style.display = 'none'
    document.querySelector('.email').style.display = 'block'
    let list = JSON.parse(window.localStorage.getItem('MyListInformation'))
    let index = list.findIndex(element => element.id === id);
    document.querySelector('#id').value = list[index].id
    document.querySelector('#name').value = list[index].name
    document.querySelector('#phone').value = list[index].phone
    document.querySelector('.newEmail').style.display = 'inline-block'

    document.querySelector('.newEmail').addEventListener('click', () => {
        list[index].email = document.querySelector('#email').value
        localStorage.setItem('MyListInformation', JSON.stringify(list));
        showAllList()
        document.querySelector('.address').style.display = 'block'
        document.querySelector('.email').style.display = 'none'
        document.querySelector('.newEmail').style.display = 'none'

        document.forms[0].reset()
    })
}