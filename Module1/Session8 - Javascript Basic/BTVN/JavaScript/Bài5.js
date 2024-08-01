let vatLy = +prompt('Điểm môn Vật Lý');
let hoaHoc = +prompt('Điểm môn Hoá Học');
let sinhHoc = +prompt('Điểm môn Sinh Học');
let log = document.querySelector('.logBox');
if (vatLy != 0 && hoaHoc != 0 && sinhHoc != 0) {
    log.innerHTML = '<p>Vật Lý: ' + vatLy + '</p> <p>Hoá Học: ' + hoaHoc + '</p> <p>Sinh Học: ' + sinhHoc + '</p>'
        + '<p class="dtb">Điểm Trung Bình 3 Môn: ' + ((hoaHoc + vatLy + sinhHoc) / 3) + '</p>';
} else {
    log.innerHTML = '<p class="error">Hãy nhập đủ điểm của cả 3 môn</p>';
}