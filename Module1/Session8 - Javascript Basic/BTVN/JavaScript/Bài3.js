let high = +prompt('Hãy nhập chiều dài hình chữ nhật');
let width = +prompt('Hãy nhập chiều rộng hình chữ nhật');;
let log = document.querySelector('.logBox');
if (high != 0 && width != 0)
    log.innerHTML = "<p>Chiều dài: " + high + '</p>'
        + '<p>Chiều rộng: ' + width + '</p>'
        + '<p class="dienTich">Diện tích hình chữ nhật là: ' + (high * width) + "</p>";
else{
    log.innerHTML = 'Không xác định được chiều dài hoặc chiều rộng của hình chữ nhật'
}