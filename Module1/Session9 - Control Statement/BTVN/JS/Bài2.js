let inputNum = prompt("Hãy nhập một số bất kỳ");
if (!isNaN(inputNum)) {
    if (inputNum % 2 == 0) {
        document.write("Số bạn nhập là số chẵn")
    } else {
        document.write("Số bạn nhập là số lẻ")
    }
} else {
    document.write("Số bạn nhập không hợp lệ")

}