/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.validation.validators;

import org.apache.sqoop.validation.Status;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 */
public class TestStartsWith {

  AbstractValidator validator = new StartsWith();

  @Test
  public void test() {
    assertEquals(0, validator.getMessages().size());

    // Default, no string argument set
    validator.validate("str");
    assertEquals(Status.OK, validator.getStatus());
    assertEquals(0, validator.getMessages().size());

    // Searched substring is entire string
    validator.validate("str");
    assertEquals(Status.OK, validator.getStatus());
    assertEquals(0, validator.getMessages().size());

    // Just starts with
    validator.reset();
    validator.setStringArgument("str");
    validator.validate("strstr");
    assertEquals(Status.OK, validator.getStatus());
    assertEquals(0, validator.getMessages().size());

    // Null string
    validator.reset();
    validator.setStringArgument("str");
    validator.validate(null);
    assertEquals(Status.ERROR, validator.getStatus());
    assertEquals(1, validator.getMessages().size());

    // Empty string
    validator.reset();
    validator.setStringArgument("str");
    validator.validate("");
    assertEquals(Status.ERROR, validator.getStatus());
    assertEquals(1, validator.getMessages().size());

    // "Random" string
    validator.reset();
    validator.setStringArgument("str");
    validator.validate("Ahoj tady je meduza");
    assertEquals(Status.ERROR, validator.getStatus());
    assertEquals(1, validator.getMessages().size());
  }

}
