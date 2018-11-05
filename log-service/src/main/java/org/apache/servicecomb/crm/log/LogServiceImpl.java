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

package org.apache.servicecomb.crm.log;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.crm.log.api.LogDTO;
import org.apache.servicecomb.crm.log.api.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestSchema(schemaId = "log")
@RequestMapping(path = "/")
public class LogServiceImpl implements LogService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

  private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

  @Override
  @PostMapping(path = "record")
  public boolean record(@RequestBody LogDTO log) {
    try {
      //简化处理，只是打印一下日志
      LOGGER.info(OBJ_MAPPER.writeValueAsString(log));
    } catch (JsonProcessingException ignored) {
    }
    return true;
  }
}