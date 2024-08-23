class ShoppingCart {
    constructor(userId, cartList) {
        this.id = `Cart-${Date.now()}`
        this.userId = userId
        this.cartList = cartList
    }
    setUserId(userId) {
        this.username = userId
    }
    setCartList(cartList) {
        this.cartList = cartList
    }
    getUserId() {
        return this.userId
    }
    getCartList() {
        return this.cartList
    }
}