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
import java.util.Map;
import java.util.function.BiFunction;

public class DarkLaunchRuleItem {

  private static Map<DarkLaunchOperator, BiFunction<String, String, Boolean>> matchFuncs = new HashMap<>();

  static {
    matchFuncs.put(DarkLaunchOperator.CONTAINS, String::contains);
    matchFuncs.put(DarkLaunchOperator.START_WITH, String::startsWith);
    matchFuncs.put(DarkLaunchOperator.END_WITH, String::endsWith);
    matchFuncs.put(DarkLaunchOperator.EQUALS, String::equals);
  }

  private DarkLaunchOperator operator;

  private String value;

  private String version;

  public DarkLaunchOperator getOperator() {
    return operator;
  }

  public void setOperator(DarkLaunchOperator operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public DarkLaunchRuleItem() {
  }

  public DarkLaunchRuleItem(DarkLaunchOperator operator, String value, String version) {
    this.operator = operator;
    this.value = value;
    this.version = version;
  }

  public boolean match(String value) {
    return matchFuncs.get(operator).apply(value, this.value);
  }
}