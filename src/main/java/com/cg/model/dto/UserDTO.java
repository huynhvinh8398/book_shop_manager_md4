package com.cg.model.dto;

import com.cg.model.LocationRegion;
import com.cg.model.Role;
import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Validator {

    private Long id;

//    @Size(max = 20, message = "username không quá 20 kí tự")
//    @NotEmpty(message = "username không được để trống")
    @Email(message = "Vui lòng nhập đúng định dạng UserName VD(vinh@gmail.com)")
    private String username;

//    @NotBlank(message = "password không được để trống")
//    @Size(max = 20, message = "password không quá 20 kí tự")

    private String password;

//    @NotEmpty(message = "fullname không được để trống")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "FullName phải là chữ , không có kí tự đặt biệt và số")
    private String fullName;

     @Pattern(regexp = "^[0][1-9][0-9]{8,9}$",message = "Số điện thoai gồm 10 số bắt đầu bằng số 0")
//    @NotEmpty(message = "Phone không được để trống")
    private String phone;

    @Column(name = "url_image")
    private String urlImage;


    @Valid
    private LocationRegionDTO locationRegion;

    @Valid
    private RoleDTO role;


    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }


    public User toUserr(){
        return new User()
                .setId(id)
                .setUrlImage(urlImage)
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setRole(role.toRole())
                .setLocationRegion(locationRegion.toLocationRegion());

    }

    public UserDTO(Long id, String fullName, String username, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole());
    }

    public UserDTO(Long id, String urlImage, String fullName, String username, String password, String phone, LocationRegion locationRegion, Role role) {
        this.id = id;
        this.urlImage = urlImage;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.role = role.toRoleDTO();
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        String fullNameCheck = userDTO.getFullName();
        String usernameCheck = userDTO.getUsername();
        String passwordCheck = userDTO.getPassword();
        String phoneCheck = userDTO.getPhone();

        if ((fullNameCheck.trim().isEmpty())) {
            errors.rejectValue("fullName", "fullName.isEmpty");
            return;
        }

        if ((usernameCheck.trim()).isEmpty()) {
            errors.rejectValue("username", "username.isEmpty", "Vui Lòng Nhập Email Người Dùng");
            return;
        }

        if ((passwordCheck.trim()).isEmpty()) {
            errors.rejectValue("password", "password.isEmpty", "Vui Lòng Nhập Mật Khẩu Người Dùng");
            return;
        }


        if ((phoneCheck.trim()).isEmpty()) {
            errors.rejectValue("phone", "phone.isEmpty", "Vui Lòng Nhập Số Điện Thoại Người Dùng");
            return;
        }

        if ((fullNameCheck.length() < 3 || fullNameCheck.length() > 100)) {
            errors.rejectValue("fullName", "fullName.length", "Tên Từ 3 Đến 100 Ký Tự");
            return;
        }

        if ((usernameCheck.length() > 100)) {
            errors.rejectValue("username", "fullName.length", "Email Tối Đa 100 Ký Tự");
            return;
        }

        if (passwordCheck.length() > 20) {
            errors.rejectValue("password", "password.length", "Mật Khẩu Tối Đa 20 Ký Tự");
            return;
        }

        if (!usernameCheck.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$")) {
            errors.rejectValue("username", "username.matches", "Email Nhập Vào Không Hợp Lệ");
            return;
        }
    }

}
