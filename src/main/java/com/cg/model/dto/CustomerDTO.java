package com.cg.model.dto;

import com.cg.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor

@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "full name không được để trống")
    private String fullName;

    @NotBlank(message = "Điện thoại không được để trống")
    private String phone;
    @NotBlank(message = "Email không được để trống")
    private String email;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;



    public CustomerDTO(Long id, String fullName, String phone, String email, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Customer toCustomer(){
            return new Customer()
                    .setId(id)
                    .setFullName(fullName)
                    .setPhone(phone)
                    .setEmail(email)
                    .setAddress(address);
    }
}
