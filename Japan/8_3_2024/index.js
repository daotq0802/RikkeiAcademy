let inputNum = + prompt('Hãy nhập một số nguyên dương');
let n = 1;
while (n <= inputNum) {
    if (n % 3 == 0 && n % 5 != 0) {
        document.write(`${n} `)
    }
    n++
}