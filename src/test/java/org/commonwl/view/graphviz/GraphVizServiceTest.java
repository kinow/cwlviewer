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

package org.commonwl.view.graphviz;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.*;


public class GraphVizServiceTest {

    private GraphVizService graphVizService;

    /**
     * Use a temporary directory for testing
     */
    @Rule
    public TemporaryFolder graphvizFolder = new TemporaryFolder();

    /**
     * Generate a service for testing using the temporary folder
     */
    @Before
    public void setUp() throws Exception {
        graphVizService = new GraphVizService(graphvizFolder.getRoot().getAbsolutePath());
    }

    /**
     * Check that a valid png file can be generated from DOT source
     */
    @Test
    public void getGraphAsPng() throws Exception {

        File dotSource = new File("src/test/resources/graphviz/testWorkflow.dot");
        File png = graphVizService.getGraph("workflowid.png",
                readFileToString(dotSource), "png");
        BufferedImage actualImg = ImageIO.read(png);

        // Check a valid image has been created
        assertNotNull(actualImg);
        assertTrue(actualImg.getWidth() > 0);
        assertTrue(actualImg.getHeight() > 0);

    }

    /**
     * Check that a valid svg file can be generated from DOT source
     */
    @Test
    public void getGraphAsSvg() throws Exception {

        File dotSource = new File("src/test/resources/graphviz/testWorkflow.dot");
        File svg = graphVizService.getGraph("workflowid.svg",
                readFileToString(dotSource), "svg");
        String svgString = FileUtils.readFileToString(svg);

        assertTrue(svgString.contains("Generated by graphviz"));

    }

    /**
     * Check that an xdot file can be generated from DOT source
     */
    @Test
    public void getGraphAsXDot() throws Exception {

        File dotSource = new File("src/test/resources/graphviz/testWorkflow.dot");
        File xdot = graphVizService.getGraph("workflowid.dot",
                readFileToString(dotSource), "xdot");
        String xdotString = FileUtils.readFileToString(xdot);

        assertNotNull(xdotString);
        assertTrue(xdotString.length() > 0);

    }

    /**
     * Check that files in the graphVizFolder can be
     * deleted with deleteCache()
     */
    @Test
    public void deleteCache() throws Exception {

        File png = graphvizFolder.newFile("exampleid.png");
        File svg = graphvizFolder.newFile("exampleid.svg");
        File dot = graphvizFolder.newFile("exampleid.dot");

        graphVizService.deleteCache("exampleid");

        assertFalse(png.exists());
        assertFalse(svg.exists());
        assertFalse(dot.exists());

    }

}