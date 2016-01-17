package com.company.generator;


import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceDGS;
import org.graphstream.ui.swingViewer.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.algorithm.Toolkit;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Graph2DGenerator {

    public Graph2DGenerator () {

    }

    public void generate(File file) throws IOException, InterruptedException {

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new MultiGraph(file.getName());
        //graph.addAttribute("ui.antialias");
        //graph.addAttribute("ui.quality");
        graph.setAttribute("ui.stylesheet", "url(data/style.css);");

        Viewer viewer = graph.display();
        View view = viewer.getDefaultView();

        //view.getCamera().setViewPercent(0.9);


        FileSource source = new FileSourceDGS();
        source.addSink( graph );
        source.begin(file.getPath());




        while(source.nextEvents()){

        }
        source.end();


    }
}
