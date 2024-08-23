class Product {
    constructor(id, name, image, price, description, stock, role) {
        this.id = `Product-${Date.now() * Math.floor(Math.random() * 100)}`;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.role = role;
    }
    setId(id){
        this.id = id
    }

    getId(){
        return this.id
    }

    setName(name) {
        this.name = name
    }
    setRole(role) {
        this.role = role;
    }
    setPrice(price) {
        this.price = price;
    }
    setImage(image) {
        this.image = image
    }
    setDescription(description) {
        this.description = description;
    }
    setStock(stock) {
        this.stock = stock
    }
    getRole() {
        return this.role
    }
    getName() {
        return this.name
    }
    getPrice() {
        return this.price
    }
    getImage() {
        return this.image
    }
    getDescription() {
        return this.description
    }
    getStock() {
        return this.stock
    }
}

let product = [
    new Product(
        'Titan 18 HX A14V',
        'https://asset.msi.com/resize/image/global/product/product_17035046674023a211767f3af074d5b9f843f5f9e1.png62405b38c58fe0f07fcef2367d8a9ba1/400.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Titan 18 HX A7V',
        'https://asset.msi.com/resize/image/global/product/product_1712041094076d7028f4e219134fc80b6947af83cb.png62405b38c58fe0f07fcef2367d8a9ba1/400.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Raider 18 HX A14V',
        'https://asset.msi.com/resize/image/global/product/product_17150494753022d6ce5a404d8aad962fd3c40935ae.png62405b38c58fe0f07fcef2367d8a9ba1/400.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Raider GE78 HX 14V',
        'https://asset.msi.com/resize/image/global/product/product_16897537971a5bf13cb50e1e26b0c835af9ad4e311.png62405b38c58fe0f07fcef2367d8a9ba1/400.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Vector 17 HX A14V',
        'https://asset.msi.com/resize/image/global/product/product_17047673239aceafaf74e626ce772a983847e00c28.png62405b38c58fe0f07fcef2367d8a9ba1/400.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product(
        'ASUS TUF Gaming A14 FA401WV (FA401WV-AI9R4060)',
        'https://drh2.img.digitalriver.com/DRHM/Storefront/Company/asusjp/images/product/thumbnail/24Q3%E3%80%80FA401WV/00_1stLook_1500_FA401WV-AI9R4060.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ROG Strix G16 G614JIR (G614JIR-I94R4070)',
        'https://drh1.img.digitalriver.com/DRHM/Storefront/Company/asusjp/images/product/thumbnail/24Q1_G614/00_G814G614_1stlook_1500x1500_G614JIR-I94R4070.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming A15 FA506NF (FA506NF-R5R2050S)',
        'https://drh2.img.digitalriver.com/DRHM/Storefront/Company/asusjp/images/product/thumbnail/24Q1_FA506N/00_1stLook_1500_FA506NF-R5R2050S_FA506NF-R5R2050A_v3.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming A15 FA507NV (FA507NV-R7R4060T/S)',
        'https://drh1.img.digitalriver.com/DRHM/Storefront/Company/asusjp/images/product/thumbnail/23Q2_FA507N/00_FA507_1stLook_1500_FA507NV-R7R4060T.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming F16 FX607JV (FX607JV-I7R4060S)',
        'https://drh1.img.digitalriver.com/DRHM/Storefront/Company/asusjp/images/product/thumbnail/24Q1_FX607JV-I7R4060S/00_1stLook_1500_FX607JV-I7R4060S.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'Dell G15',
        'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/g-series/g15-5530/media-gallery/gray/non-touch/4-zone-rgb-kb/notebook-laptop-g15-5530-gray-gallery-1.psd?fmt=png-alpha&wid=480&hei=320',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware M16 R2',
        'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/alienware-notebooks/alienware-m16-r2-intel/media-gallery/laptop-aw-m16r2-nt-bk-gallery-3.psd?fmt=png-alpha&wid=480&hei=320',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware Aurora R16',
        'https://i.dell.com/sites/csimages/App-Merchandizing_Images/ja/alienware-aurora-r16-desktop_bk-liquid-cool_480x320.png',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware x16 R2',
        'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/alienware-notebooks/alienwar-x16-mlk/gallery/notebook-alienware-x16-r2-gray-gallery-12.psd?fmt=png-alpha&wid=480&hei=320',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware M18 R2',
        'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/alienware-notebooks/alienware-m18-mlk/media-gallery/hd/laptop-alienware-m18-r2-hd-perkey-intel-bk-gallery-2.psd?fmt=png-alpha&wid=480&hei=320',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
]
