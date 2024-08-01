const PI = Math.PI;
let r = +prompt('Bán kính đường tròn');
let log = document.querySelector('.logBox');
if (log != 0) {
    log.innerHTML =
        "<p>Đường tròn có bán kình là: " + r + "</p>"
        + '<p class="chuVi">Chu vi hình tròn là: ' + (2 * PI * r) + '</p>'
        + '<p class="dienTich">Diện tích hình tròn là: ' + (PI * Math.pow(r, 2)) + '</p>';
}