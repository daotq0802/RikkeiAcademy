let numA = +prompt('Mời nhập số A');
let numB = +prompt('Mời nhập số B');
document.write(`Các số trong khoảng từ 1 đến ${numA} chia hết cho ${numB} là : `);
for (let i = 1; i <= numA; i++) {
    if (i % numB == 0) {
        document.write(`${i} `)
    }
}