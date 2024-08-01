let high = +prompt('Hãy nhập chiều dài hình chữ nhật', 0);
let width = +prompt('Hãy nhập chiều rộng hình chữ nhật', 0);;
let log = document.querySelector('.logBox');
if (high != 0 && width != 0)
    log.innerHTML = "<p>Chiều dài: " + high + '</p><p>Chiều rộng: ' + width + '</p>'
        + '<p class="dienTich">Diện tích hình chữ nhật là: ' + (high * width) + "</p>"
        + '<p class="chuVi">Chu vi hình chữ nhật: ' + ((high + width) / 2) + '</p>';