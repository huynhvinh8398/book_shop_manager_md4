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

        static showConfirmDeleteProduct() {
            return Swal.fire({
                title: 'Bạn có muốn xoá sản phẩm này?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Có, Xoá ngay!',
                cancelButtonText: 'Huỷ'
            });
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

    }
    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

}
class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class User{
    constructor(id,username, password, fullName, phone, email,locationRegion, urlImage,role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.locationRegion = locationRegion;
        this.urlImage = urlImage;
        this.role = role;

    }

}
class Role {
    constructor(id ,code, name) {
        this.id = id;
        this.code = code;
        this.name = name;
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
    constructor(id, productName, category, amountProduct, priceProduct, image, isdeleted){
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.amountProduct = amountProduct;
        this.priceProduct = priceProduct;
        this.image = image;
        this.isdeleted = isdeleted;

    }
}