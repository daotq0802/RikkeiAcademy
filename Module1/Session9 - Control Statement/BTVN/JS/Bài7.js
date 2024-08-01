let inputNum = +prompt("Mời nhập một số");
for (let i = 1; i <= inputNum; i++) {
    if (i % 3 == 0) {
        document.write(`${i} Fizz | `);
    }
    if (i % 3 == 0 && i % 5 == 0) {
        document.write(`${i} FizzBuzz<br>`);
    }
    if (i % 5 == 0) {
        document.write(`${i} Buzz | `);
    }
}