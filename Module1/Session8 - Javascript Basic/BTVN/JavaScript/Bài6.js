let celsius = +prompt("Hãy nhập độ C");
let log = document.querySelector('.logBox');
log.innerHTML = '<p>Độ C chuyển đổi: ' + celsius + ' độ </p>'
    + '<p class="changeToF">Độ F là: ' + ((9 / 5 * celsius) + 32) + ' Độ<p>'