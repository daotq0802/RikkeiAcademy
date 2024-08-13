const display = document.querySelector('.console')
// Bài 1
function Bai1() {
    let numberA = +prompt('Nhập số A')
    let numberB = +prompt('Nhập số B')
    let numberC = +prompt('Nhập số C')
    checkMinNumber(numberA, numberB, numberC)
}

function checkMinNumber(a, b, c) {
    if (a > b && a > c) {
        return display.innerHTML = `Số ${a} là số lớn nhất`
    }
    if (b > c && b > a) {
        return display.innerHTML = `Số ${b} là số lớn nhất`
    }
    return display.innerHTML = `Số ${c} là số lớn nhất`
}

//Bài 2
function Bai2() {
    let input = +prompt('Nhập số bất kỳ')
    isPrimeNumber(input);
}

function isPrimeNumber(number) {
    if (number <= 1) {
        return display.innerHTML = `Số ${number} không phải là số nguyên tố`
    }
    for (let i = 2; i < number ** 1 / 2; i++) {
        if (number % i == 0) {
            return display.innerHTML = `Số ${number} không phải là số nguyên tố`
        }
    }
    return display.innerHTML = `Số ${number} là số nguyên tố`
}

//Bài 3
function Bai3() {
    let input = +prompt('Nhập số bất kỳ')
    checkFactorial(input);
}
function checkFactorial(number) {
    let result = 1;
    if (number <= 1) {
        return display.innerHTML = `Giai thừa của ${number} là 1`
    }
    for (let i = 1; i <= number; i++) {
        result *= i
    }
    return display.innerHTML = `Giai thừa của ${number} là ${result}`
}

//Bài4
function Bai4() {
    let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    display.innerHTML = `Mảng cho sẵn: ${arr.join(" ")}<br>
    Các cặp có tổng là 10 và vị trí trong mảng là: ${twoSum(arr, 10)}`
}

function twoSum(a, target) {
    let result = []
    for (let i = 0; i < a.length; i++) {
        for (let j = i + 1; j < a.length; j++) {
            if (a[i] + a[j] == target) {
                result.push([a[i], a[j]])
            }
        }
    }
    return result.join(" | ")
}

//Bài 5
function Bai5() {
    let input = prompt('Nhập bất kỳ')
    display.innerHTML = `Dữ liệu truyền vào là: ${input}<br>
    ${countAppear(input)}`
}
function countAppear(input) {
    let string = new Map()
    let result = [];
    for (let i = 0; i < input.length; i++) {
        let count = 0;
        for (let j = 0; j < input.length; j++) {
            if (input[i] == input[j]) {
                count++;
            }
        }
        string.set(input[i], count)
    }
    console.log(string)
    string.forEach((count, value) => {
        result.push(`Ký tự ${value == " " ? value = "&nbsp;" : value} xuất hiện ${count} lần`)
    })
    return result.join('<br>')
}