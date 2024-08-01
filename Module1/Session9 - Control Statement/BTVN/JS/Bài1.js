let age = +prompt("Bạn sinh năm bao nhiêu??");
let year = new Date().getFullYear();
if(age > 0){
    document.write(`Tuổi của bạn năm nay là: ${year - age} tuổi`)
}