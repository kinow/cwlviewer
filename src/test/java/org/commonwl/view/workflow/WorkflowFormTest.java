/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.commonwl.view.workflow;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WorkflowFormTest {

    /**
     * Test for the form stripping unnecessary trailing slashes from directory URLs
     */
    @Test
    public void getGithubURL() throws Exception {

        String unchangedURL = "https://github.com/common-workflow-language/workflows/tree/master/workflows/compile";
        WorkflowForm testForm = new WorkflowForm(unchangedURL);
        assertEquals(unchangedURL, testForm.getGithubURL());

        WorkflowForm testForm2 = new WorkflowForm("https://github.com/common-workflow-language/workflows/tree/master/workflows/compile/");
        assertEquals("https://github.com/common-workflow-language/workflows/tree/master/workflows/compile", testForm2.getGithubURL());

        testForm2.setGithubURL("https://github.com/common-workflow-language/workflows/tree/master/workflows/make-to-cwl/////");
        assertEquals("https://github.com/common-workflow-language/workflows/tree/master/workflows/make-to-cwl", testForm2.getGithubURL());

    }

}