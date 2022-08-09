package com.cg.service.user;

import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    List<UserDTO> findAllUserDTOAnDeletedIsFalse();

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);


    Boolean existsById(Long id);

    Boolean existsByUserName(String username);

    Boolean existsByPhone(String phone);


    Boolean existsByUserAndIdIsNot(String username, Long id);

    void deleteUserSoft(User user);
    void deleteUserById(Long id);

    Optional<UserDTO> findUserDTOById(Long id);


}
