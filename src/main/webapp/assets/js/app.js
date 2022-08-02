class App {
    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

    }
    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: t
            });
        }

        static showErrorAlert(t) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 3500,
                message: t
            });
        }
        //
        // static showConfirmDelete() {
        //     return Swal.fire({
        //         title: 'Are you sure to deactive the selected product ?',
        //         icon: 'warning',
        //         showCancelButton: true,
        //         confirmButtonColor: '#3085d6',
        //         cancelButtonColor: '#d33',
        //         confirmButtonText: 'Yes, remove it!'
        //     });
        // }
    }


}
class Customer{
    constructor(id,userName, password, fullName, phone, email,address) {
        this.id = id;
        this.userName = userName;
        this.pasword = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;

    }

}
class Category{
    constructor(id, name,code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}

class Product{
    constructor(id, productName, category, amountProduct, priceProduct, image){
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.amountProduct = amountProduct;
        this.priceProduct = priceProduct;
        this.image = image;

    }
}