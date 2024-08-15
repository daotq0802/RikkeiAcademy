const review = document.querySelector('.msg pre')
const contactForm = document.querySelector('.contactForm')
let btnUpdate = document.querySelector('.btnUpdate')
let listContact = []
//Phương thức thêm danh bạ mới
function addContact() {
    let contact = {
        name: document.querySelector('#name').value,
        phone: document.querySelector('#phone').value,
        email: document.querySelector('#email').value
    }
    listContact.push(contact)
    localStorage.setItem('ListContact', JSON.stringify(listContact))
}

//Hiển thị tất cả danh bạ
function displayContact() {
    let list = JSON.parse(localStorage.getItem('ListContact'))
    let stringList = ''
    list.forEach(element => {
        stringList += '<div class="item">'
        for (const key in element) {
            let textKey = key.split('').join('')
            textKey = textKey[0].toUpperCase() + textKey.slice(1)
            stringList += `<p>${textKey}: ${element[key]}</p>`
        }
        stringList += `<div><button onclick={updateContactByName('${element.name}')}>Sửa</button><button onclick={deleteContactByName('${element.name}')}>Xoá</button></div></div>`
    })
    review.innerHTML = stringList
}
//Sự kiện submit thêm danh bạ mới
contactForm.addEventListener('submit', (event) => {
    event.preventDefault()
    addContact()
    displayContact()
})

//Tìm kiếm danh bạ theo tên
function findContactByName() {
    let list = JSON.parse(localStorage.getItem('ListContact'))
    let searchInput = prompt('Tìm kiếm danh bạ theo tên')
    let stringList = ''
    let index = list.findIndex(element => element.name === searchInput)
    console.log(index)
    if (index != -1) {
        stringList = `<div class="item"><p>Name: ${list[index].name}</p><p>Phone: ${list[index].phone}</p><p>Email: ${list[index].email}</p></div>`
        review.innerHTML = stringList
    } else {
        alert('Không tìm thấy danh bạ')
        displayContact()
    }
}

//Xoá danh bạ theo tên
function deleteContactByName(name) {
    let list = JSON.parse(localStorage.getItem('ListContact'))
    let index = list.findIndex(element => element.name == name)
    list.splice(index, 1)
    localStorage.setItem('ListContact', JSON.stringify(list))
    displayContact()
}

//Sửa danh bạ theo tên
function updateContactByName(nameIndex) {
    let list = JSON.parse(localStorage.getItem('ListContact'))
    let index = list.findIndex(element => element.name == nameIndex)
    let name = document.querySelector('#name')
    let phone = document.querySelector('#phone')
    let email = document.querySelector('#email')
    document.querySelector('.addContact').style.display = 'none'
    document.querySelector('.searchContact').style.display = 'none'

    btnUpdate.style.display = 'inline-block'
    name.disabled = true
    name.value = list[index].name
    phone.value = list[index].phone
    email.value = list[index].email
    btnUpdate.addEventListener('click', () => {
        list[index].phone = phone.value
        list[index].email = email.value
        localStorage.setItem('ListContact', JSON.stringify(list))
        displayContact()
        btnUpdate.style.display = 'none'
        document.querySelector('.addContact').style.display = 'inline-block'
        document.querySelector('.searchContact').style.display = 'inline-block'
    })
}




