class ShoppingCart {
    constructor(userId, cartList) {
        this.id = `Cart-${Date.now()}`
        this.userId = userId
        this.cartList = cartList
    }
}