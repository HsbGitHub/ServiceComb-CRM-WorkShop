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

package org.apache.servicecomb.crm.edge.filter;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.provider.springmvc.reference.async.CseAsyncRestTemplate;
import org.apache.servicecomb.crm.log.api.LogDTO;
import org.apache.servicecomb.swagger.invocation.Response;
import org.springframework.http.HttpEntity;

public class LogFilter implements HttpServerFilter {

  private static final String LOG_SERVICE_NAME = "infrastructure:log-service";

  private final CseAsyncRestTemplate restTemplate = new CseAsyncRestTemplate();

  @Override
  public int getOrder() {
    return 1;
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    String userName = invocation.getContext().get(AuthenticationFilter.EDGE_AUTHENTICATION_NAME);
    if (StringUtils.isNotEmpty(userName)) {
      HttpEntity<LogDTO> request = new HttpEntity<>(
          new LogDTO(userName, invocation.getMicroserviceName(), invocation.getOperationName(), new Date()));
      try {
        restTemplate.postForEntity("cse://" + LOG_SERVICE_NAME + "/record", request, Boolean.class);
      } catch (Exception ignored) {
      }
    }
    return null;
  }
}
