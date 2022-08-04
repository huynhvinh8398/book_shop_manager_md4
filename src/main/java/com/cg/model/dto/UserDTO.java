package com.cg.model.dto;

import com.cg.model.LocationRegion;
import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;


    private String username;


    private String password;


    private String fullName;


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

    public UserDTO(Long id,String urlImage, String fullName, String username, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.urlImage = urlImage;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

}
