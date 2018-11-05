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

package org.apache.servicecomb.crm.edge.darklaunch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.servicecomb.serviceregistry.definition.DefinitionConst;

public class DarkLaunchRule {

  private Map<String, List<DarkLaunchRuleItem>> headerRules;

  private String defaultVersion;

  public Map<String, List<DarkLaunchRuleItem>> getHeaderRules() {
    return headerRules;
  }

  public void setHeaderRules(
      Map<String, List<DarkLaunchRuleItem>> headerRules) {
    this.headerRules = headerRules;
  }

  public String getDefaultVersion() {
    return defaultVersion;
  }

  public void setDefaultVersion(String defaultVersion) {
    this.defaultVersion = defaultVersion;
  }

  public DarkLaunchRule() {
    headerRules = new HashMap<>();
    defaultVersion = DefinitionConst.VERSION_RULE_ALL;
  }

  public DarkLaunchRule(List<DarkLaunchRuleItem> userRules,
      Map<String, List<DarkLaunchRuleItem>> headerRules, String defaultVersion) {
    this.headerRules = headerRules;
    this.defaultVersion = defaultVersion;
  }

  public String matchVersion(Map<String, String> headers) {
    for (Entry<String, List<DarkLaunchRuleItem>> rule : headerRules.entrySet()) {
      if (headers.containsKey(rule.getKey())) {
        String value = headers.get(rule.getKey());
        for (DarkLaunchRuleItem item : rule.getValue()) {
          if (item.match(value)) {
            return item.getVersion();
          }
        }
      }
    }
    return defaultVersion;
  }

  public String matchVersion(List<Entry<String, String>> headers) {
    for (Entry<String, String> header : headers) {
      if (headerRules.containsKey(header.getKey())) {
        for (DarkLaunchRuleItem item : headerRules.get(header.getKey())) {
          if (item.match(header.getValue())) {
            return item.getVersion();
          }
        }
      }
    }
    return defaultVersion;
  }
}