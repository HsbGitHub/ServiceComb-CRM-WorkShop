package org.apache.servicecomb.crm.user.api;

public interface AuthenticationService {
  String validate(String token);
}