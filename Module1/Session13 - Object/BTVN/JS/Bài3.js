const review = document.querySelector('.msg pre');
const studentForm = document.querySelector('.stdForm');
let listStudent = [];

studentForm.addEventListener('submit', (event) => {
    event.preventDefault();
    let student = {
        id: Date.now(),
        name: document.querySelector('#name').value
    }
    listStudent.push(student);
    document.forms[0].reset();
    localStorage.setItem('ListStudent', JSON.stringify(listStudent))
    showAllList();
})

function showAllList() {
    let list = JSON.parse(localStorage.getItem('ListStudent'));
    let stringList = '';
    list.forEach(element => {
        for (const key in element) {
            let textKey = key.split('').join('');
            textKey = textKey[0].toUpperCase() + textKey.slice(1);
            stringList += `<div>${textKey}: ${element[key]}</div>`
        }
    });
    review.innerHTML = stringList;
}