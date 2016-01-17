package com.company;

import com.company.ast.graph.CallGraph;
import com.company.ast.graph.klass.DiGraphASTClass;
import com.company.ast.graph.method.DiGraphASTMethod;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;
import com.company.ast.ASTGenerator;
import com.company.generator.DGSGenerator;
import com.company.generator.Graph2DGenerator;

public class Main {

	public static ASTClass parseFile(File file) {
		ASTGenerator generator = new ASTGenerator();
        generator.initialize();
        System.out.println(file.getName());
        generator.parseFile(file.getAbsolutePath());

        ASTClass root = generator.getClass(file.getName());

        return root;
    }

    public static void parseDir(List<ASTClass> classes, File dir) {
        File[] files = dir.listFiles();
            for (File f : files) {
                    if (f.isDirectory())
                            parseDir(classes, f);
                    else {
                        ASTClass c = parseFile(f);

                        if(c != null)
                            classes.add(c);
                    }

            }
    }

	public static void main(String[] args) {
        File directoryToScan = new File("./src/com/company");
        List<ASTClass> classes = new ArrayList<ASTClass>();
        parseDir(classes, directoryToScan);

        ASTClass a = new ASTClass("null");
        for(ASTClass c : classes)
            if("A".equals(c.getName()))
                a = c;

        CallGraph callGraph = new CallGraph();

        DiGraphASTClass gr = callGraph.getGraphClass(classes);

        DiGraphASTMethod gm = callGraph.getGraphMethod(a);

        DGSGenerator generator = new DGSGenerator();
        File f = generator.generateDGS("test001");
        //generator.generateCallMethodGraph(f, gm);
        generator.generateCallClassGraph(f, gr);

        Graph2DGenerator generator2 = new Graph2DGenerator();
        try {
            generator2.generate(f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
