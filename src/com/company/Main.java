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
        if (args.length < 1) {
        	System.out.println("La ligne de commande doit être de la forme <dirpath> [classname]");
        	return;
        }
        
        String nameClass = "";
        if (args.length == 2) {
        	nameClass = args[1];
        }
		
		File directoryToScan = new File(args[0]);
        List<ASTClass> classes = new ArrayList<ASTClass>();
        parseDir(classes, directoryToScan);

        CallGraph callGraph = new CallGraph();

        DGSGenerator generator = new DGSGenerator();
        Graph2DGenerator generator2 = new Graph2DGenerator();


        File astClass = null;

        for(ASTClass cls : classes) {
            DiGraphASTMethod gm = callGraph.getGraphMethod(cls);
            File f = generator.generateDGS(cls.getName());
            generator.generateCallMethodGraph(f, gm);

            if(cls.getName().equals(nameClass)) {
            	astClass = f;
            }
        }

        DiGraphASTClass gr = callGraph.getGraphClass(classes);
        File f = generator.generateDGS("app");
        generator.generateCallClassGraph(f, gr);


        try {
        	if(astClass != null)
        		generator2.generate(astClass);
        	else
        		generator2.generate(f);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
