package com.cg.model;

import com.cg.model.dto.UserDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String phone;

    private String urlImage;



    @OneToOne
    @JoinColumn(name = "location_region_id", nullable = false)
    private LocationRegion locationRegion;





    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserDTO toUserDTO(){
        return new UserDTO()
                .setId(id)
                .setUrlImage(urlImage)
                .setFullName(fullName)
                .setUsername(username)
               .setPassword(password)
                .setPhone(phone)
                .setRole(role.toRoleDTO())
                .setLocationRegion(locationRegion.toLocationRegionDTO());
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
