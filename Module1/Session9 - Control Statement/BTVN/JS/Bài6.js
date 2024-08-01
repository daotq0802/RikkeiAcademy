let inputNum = +prompt("Mời nhập một số");
let result = 1;
document.write(`Giai thừa của ${inputNum} là `);
for (let i = 1; i <= inputNum; i++) {
    if (i < inputNum) {
        document.write(`${i} * `);
    } else {
        document.write(`${i} `);
    }
    result *= i;
}
document.write(`= ${result}`);
