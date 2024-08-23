class User {
    constructor(name, age, email, password, phone) {
        this.id = `PC-${Date.now()}`;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    setName(name) {
        this.name = name
    }
    setName(age) {
        this.age = age
    }
    setName(password) {
        this.password = password
    }
    setName(email) {
        this.email = email
    }
    setName(phone) {
        this.phone = phone
    }
    getId() {
        return this.id
    }
    getName() {
        return this.id
    }
    getAge() {
        return this.id
    }
    getPassword() {
        return this.id
    }
    getEmail() {
        return this.id
    }
    getPhone() {
        return this.id
    }


}