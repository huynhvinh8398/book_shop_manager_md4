package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);


    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
                "u.id, " +
                "u.urlImage," +
                "u.fullName, " +
                "u.username, " +
                "u.password, " +
                "u.phone, " +
                "u.locationRegion," +
                "u.role)" +
            "FROM User u  WHERE u.deleted = false ")
    List<UserDTO> findAllUserDTOAnDeletedIsFalse();
    List<User> findAllByDeletedIsFalse();


    @Query("SELECT new com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.urlImage," +
            "u.fullName, " +
            "u.username, " +
            "u.password, " +
            "u.phone, " +
            "u.locationRegion," +
            "u.role)" +
            "FROM User AS u " +
            "WHERE u.id = :id"
    )
    UserDTO getUserDTOById();




    Boolean existsByUsernameAndIdIsNot(String username, Long id);



    @Query("SELECT NEW com.cg.model.dto.UserDTO (u.id, u.username) FROM User u WHERE u.username = ?1")
    Optional<UserDTO> findUserDTOByUsername(String username);

    @Modifying
    @Query("UPDATE User AS u set u.deleted = true WHERE u.id= :id")
    void deleteUserById(@Param("id") Long id);
    Optional<UserDTO> findUserDTOById(Long id);



}
