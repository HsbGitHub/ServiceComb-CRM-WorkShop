/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.crm.log.api;

import java.util.Date;

public class LogDTO {
  private String userName;

  private String serviceName;

  private String operationName;

  private Date invokeTime;

  public String getUserName() {
    return userName;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getOperationName() {
    return operationName;
  }

  public Date getInvokeTime() {
    return invokeTime;
  }

  public LogDTO() {
  }

  public LogDTO(String userName, String serviceName, String operationName, Date invokeTime) {
    this.userName = userName;
    this.serviceName = serviceName;
    this.operationName = operationName;
    this.invokeTime = invokeTime;
  }
}
