class Product {
    constructor(name, image, price, description, stock, role) {
        this.id = `Product-${Date.now() * Math.floor(Math.random() * 100)}`;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.role = role;
    }
}

let product = [
    new Product(
        'Titan 18 HX A14V',
        './Images/Product/pc-product-1.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Titan 18 HX A7V',
        './Images/Product/pc-product-2.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Raider 18 HX A14V',
        './Images/Product/pc-product-3.jpg',
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Raider GE78 HX 14V',
        './Images/Product/pc-product-4.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product('Vector 17 HX A14V',
        './Images/Product/pc-product-5.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'MSI'),
    new Product(
        'ASUS TUF Gaming A14 FA401WV (FA401WV-AI9R4060)',
        './Images/Product/pc-product-6.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ROG Strix G16 G614JIR (G614JIR-I94R4070)',
        './Images/Product/pc-product-7.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming A15 FA506NF (FA506NF-R5R2050S)',
        './Images/Product/pc-product-8.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming A15 FA507NV (FA507NV-R7R4060T/S)',
        './Images/Product/pc-product-9.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'ASUS TUF Gaming F16 FX607JV (FX607JV-I7R4060S)',
        './Images/Product/pc-product-10.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'ASUS'
    ),
    new Product(
        'Dell G15',
        './Images/Product/pc-product-11.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware M16 R2',
        './Images/Product/pc-product-12.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware Aurora R16',
        './Images/Product/pc-product-13.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware x16 R2',
        './Images/Product/pc-product-14.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
    new Product(
        'Alienware M18 R2',
        './Images/Product/pc-product-15.jpg',
        Math.floor(Math.random() * 1000000),
        'PEAK PERFORMANCE FOR GAMERS INTEL® CORE™ i9 PROCESSOR 14900HX',
        Math.floor(Math.random() * 100),
        'DELL'
    ),
]