package org.apache.servicecomb.crm.user.api;

import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<Boolean> logon(UserDTO user);

  ResponseEntity<Boolean> login(UserDTO user);

  ResponseEntity<Boolean> changePassword(UserUpdateDTO userUpdate);
}
