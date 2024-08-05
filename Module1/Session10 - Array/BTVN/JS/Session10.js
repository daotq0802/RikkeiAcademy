const display = document.querySelector('.console');
//BÀI 1
function Bai1() {
    let exampleArray = [];
    let input = prompt('Nhập số phần tử mảng');
    if (!isNaN(input) && input != "") {
        let i = 1;
        while (i <= input) {
            exampleArray.push(i);
            i++
        }
        display.innerHTML = `Các phần tử trong mảng là: ${exampleArray}`;
    } else {
        display.innerHTML = 'Trong mảng không tồn tại số nguyên hoặc mảng rỗng';
    }
}

//BÀI 2
function Bai2() {
    display.innerHTML = `Hãy thử tìm kiếm phần tử bất kỳ trong mảng <br>
    <button onclick="searchInArray()">Tìm Kiếm</button>`
}
function searchInArray() {
    let exampleArray = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    let input = +prompt('Điền phần tử muốn tìm');
    if (input != "" && !isNaN(input) && exampleArray.includes(input)) {
        display.innerHTML = `Phần tử ${input} có trong mảng và nằm ở vị trí thứ ${exampleArray.indexOf(input)} của mảng`;
    } else {
        display.innerHTML = `Phần tử muốn tìm không tồn tại`;
    }
}

//BÀI 3
function Bai3() {
    let count = 0;
    let uniqueArray = [1, 2, 1, 3, 4, 4, 3, 5, 2, 5, 6, 7, 8, 5, 7, 6, 4, 3, 4, 7, 2, 4, 5, 6];
    let input = +prompt('Nhập số cần kiểm tra tần suất xuất hiện trong mảng');
    for (let i = 0; i < uniqueArray.length - 1; i++) {
        if (uniqueArray[i] == input) {
            count++
        }
    }
    display.innerHTML = `Số ${input} xuất hiện ${count} lần trong mảng`
}

//BÀI 4
function Bai4() {
    let inputA = +prompt('Nhập số A');
    let inputB = +prompt('Nhập số B');
    let divideA = [];
    let divideB = [];
    for (let i = 1; i <= inputA; i++) {
        if (i % inputB == 0) {
            divideB.push(i);
        }
    }
    for (let i = 1; i <= inputB; i++) {
        if (i % inputA == 0) {
            divideA.push(i);
        }
    }
    if (inputA > inputB) {
        display.innerHTML = `Mảng các số chia hết cho B trong khoảng từ 1 đến ${inputA} là: ${divideB}`;
    } else {
        display.innerHTML = `Mảng các số chia hết cho A trong khoảng từ 1 đến ${inputB} là: ${divideA}`;
    }
}

//BÀI 5
function Bai5() {
    let uniqueArray = [1, 5, 7, 3, 5, 7, 99, 0, 2, 1, 5, 7, 4, 3, 5, 43, 6];
    let reverseArray = [];
    for (let i = uniqueArray.length - 1; i >= 0; i--) {
        reverseArray.push(uniqueArray[i]);
    }
    display.innerHTML = `Mảng ban đầu: ${uniqueArray} <br>
    Mảng sau khi đảo ngược thứ tự: ${reverseArray}`
}

//BÀI 6
function Bai6() {
    let uniqueArray = [5, 10, 3, 6, 2, 7, -4, 2, -5, 9, -7, -12]
    let sortArray = [...uniqueArray];

    for (let i = 0; i < sortArray.length - 1; i++) {
        for (let j = i + 1; j < sortArray.length; j++) {
            if (sortArray[i] > sortArray[j]) {
                let tmp = sortArray[i];
                sortArray[i] = sortArray[j];
                sortArray[j] = tmp;
            }
        }
    }
    display.innerHTML = `Mảng ban đầu: ${uniqueArray} <br>
    Mảng sau khi sắp xếp: ${sortArray}`
}

//BÀI 7
function Bai7() {
    let uniqueArray = [1, 5, 7, 3, 5, 7, 99, 0, 2, 1, 5, 7, 4, 3, 5, 43, 6];
    let cloneArray = [...uniqueArray]
    let lines = [];
    let map = new Map();

    for (let i = 0; i < uniqueArray.length; i++) {
        let count = 0;
        for (let j = 0; j < cloneArray.length; j++) {
            if (uniqueArray[i] == cloneArray[j]) {
                count++;
                delete cloneArray[j];
            }
        }
        if (count >= 2) {
            map.set(uniqueArray[i], count);
        }
    }
    map.forEach((value, key) => {
        lines.push(`${key} ===> Số lần lặp lại: ${value}`)
    })
    display.innerHTML = lines.join('<br>')
}