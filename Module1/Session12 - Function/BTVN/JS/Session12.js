const review = document.querySelector('.console');
//BÀI 1
function Bai1() {
    let input = +prompt('Nhập một số bất kỳ để kiểm tra')
    checkOddEven(input);
}
function checkOddEven(input) {
    if (input % 2 == 0) {
        review.innerHTML = `${input} là số chẵn`
    } else {
        review.innerHTML = `${input} là số lẻ`
    }
}

//BÀI 2
function Bai2() {
    let input = prompt('Nhập một mảng')
    let arr = input.split(' ')
    console.log(arr)
    checkLongestLength(arr);
}
function checkLongestLength(input) {
    let max = 0;
    for (let i = 0; i < input.length; i++) {
        if (input[max].length < input[i].length) {
            max = i;
        }
    }
    return review.innerHTML = `Mảng đã khai báo là: ${input.join(' | ')} <br>
    Phần tử có độ dài lớn nhất là ${input[max]} ở vị trí thứ ${max} của mảng`
}

//BÀI 3
function Bai3() {
    let input = prompt('Hãy nhập bất kỳ')
    let arr = input.split('')
    checkInputLength(arr)
}

function checkInputLength(input) {
    let length = 0;
    for (let i = 0; i < input.length; i++) {
        length++;
        if (input[i] == " ") {
            length--;
        }
    }
    return review.innerHTML = `Chuỗi ký tự khai báo: ${input.join('')} <br>
    Số ký tự có trong chuỗi là: ${length} ký tự trừ khoảng cách`
}

//BÀI 4
function Bai4() {
    let string = prompt('Nhập một chuỗi bất kỳ')
    let input = prompt('Nhập một ký tự bất kỳ')
    let arrStr = string.split('')
    checkString(arrStr, input)
}
function checkString(string, input) {
    let count = 0;
    for (let i = 0; i < string.length; i++) {
        if (input == string[i]) {
            count++
        }
    }
    return review.innerHTML = `Chuỗi đã nhập là: ${string.join('')}<br>
    Ký tự đã nhập ${input == " " ? "&nbsp;" : input} có ${count} lần xuất hiện trong chuỗi`
}

//BÀI 5
function Bai5() {
    let string = prompt('Nhập một chuỗi bất kỳ')
    let input = prompt('Nhập một ký tự bất kỳ')

    let arrStr = string.split(' ')
    searchInput(arrStr, input)
}
function searchInput(array, input) {
    let result = array.filter(x => x.includes(input))
    return review.innerHTML = `Chuỗi đã nhập là: ${array.join(' ')}<br>
    Ký tự muốn tìm là: ${input} có trong chuỗi là ${result.join(' | ')}`
}

//BÀI 6
function Bai6() {
    let string = prompt('Nhập một chuỗi bất kỳ')
    let arrStr = string.split(' ')
    fristCharUpper(arrStr)
}
function fristCharUpper(input) {
    let result = []
    for (const value of input) {
        result.push(value[0].toUpperCase() + value.slice(1));
    }
    return review.innerHTML = `${result.join(' ')}`
}