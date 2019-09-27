// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.devtools;

import static org.openqa.selenium.devtools.Log.clear;
import static org.openqa.selenium.devtools.Log.disable;
import static org.openqa.selenium.devtools.Log.enable;
import static org.openqa.selenium.devtools.Log.entryAdded;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by aohana
 */
public class ChromeDevToolsLogTest extends ChromeDevToolsTestBase {

  @Test
  public void verifyEntryAddedAndClearLog() {

    devTools.send(enable());

    devTools
        .addListener(entryAdded(), logEntry -> {
          Assert.assertEquals(true, logEntry.getText().contains("404"));
          Assert.assertEquals(true, logEntry.getLevel().equals("error"));
        });

    chromeDriver.get(appServer.whereIsSecure("notValidPath"));

    devTools.send(clear());
    devTools.send(disable());

  }

}
