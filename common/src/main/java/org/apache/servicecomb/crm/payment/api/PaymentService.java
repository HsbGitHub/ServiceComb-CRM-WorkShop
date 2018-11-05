package org.apache.servicecomb.crm.payment.api;

import org.springframework.http.ResponseEntity;

public interface PaymentService {
  ResponseEntity<Boolean> deposit(PaymentDTO payment);
}
