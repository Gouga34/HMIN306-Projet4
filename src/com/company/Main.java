package com.company;

import com.company.ast.graph.CallGraph;
import com.company.ast.graph.klass.DiGraphASTClass;
import com.company.ast.graph.method.DiGraphASTMethod;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;
import com.company.ast.ASTGenerator;

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

            CallGraph callGraph = new CallGraph();

            DiGraphASTClass gr = callGraph.getGraphClass(classes);

            System.out.println(gr.toString());
	}
}
