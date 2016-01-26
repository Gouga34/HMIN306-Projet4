package com.company.generator;

import com.company.ast.graph.klass.DiGraphASTClass;
import com.company.ast.graph.method.DiGraphASTMethod;
import com.company.ast.graph.method.NodeMethod;
import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;
import com.company.graph.DiGraph;
import com.company.graph.Edge;
import com.company.graph.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DGSGenerator {


    public DGSGenerator () {

    }

    public File generateDGS(String name) {

        File file = new File("./src/data/" + name + ".dgs");
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));

            output.write("DGS003\n");
            output.write("\"" + name + "\" 00\n");

            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void generateCallClassGraph(File file, DiGraphASTClass graph) {

        createNode(file, "ROOT");

        for(Node<ASTClass> cls : graph.getNodes()) {
            createNode(file, cls.getValue().getName());
            createEdge(file, "ROOT", cls.getValue().getName(), "");
        }

        for(Node<ASTClass> cls : graph.getNodes()) {
            for(Edge<ASTClass> child : cls.getChildren()) {
                String a = cls.getValue().getName();
                String b = child.getNode().getValue().getName();
                createEdge(file, a, b, a + " -- " + child.getWeight() + " --> " + b);
            }
        }
    }

    public void generateCallMethodGraph(File file, DiGraphASTMethod graph) {

        createNode(file, "ROOT");

        String className = graph.getCls().getName();

        for(Node<ASTMethod> method : graph.getNodes()) {
            String s =  className + "." + method.getValue().getName() + "(";

            for(ASTVariable var : method.getValue().getParameters()) {
                s += var.getType().getName() + ", ";
            }

            s += ")";
            createNode(file, s);
            createEdge(file, "ROOT", s, "");
        }

        for(Node<ASTMethod> method : graph.getNodes()) {
            for(Edge<ASTMethod> child : method.getChildren()) {

                String s =  className + "." + method.getValue().getName() + "(";

                for(ASTVariable var : method.getValue().getParameters())
                    s += var.getType().getName()+ ", ";

                s += ")";

                String a = s;
                String b = className + "." + child.getNode().getValue().getName() + "(";

                for(ASTVariable var : child.getNode().getValue().getParameters())
                    b += var.getType().getName()+ ", ";

                b+= ")";

                createEdge(file, a, b, a + " -> " + b);
            }
        }
    }

    private void createNode(File file, String name) {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));

            output.write("an \"" + name + "\"\n");
            output.write("cn \"" + name + "\" label=\"" + name + "\"\n");

            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEdge(File file, String node1, String node2, String label) {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));

            output.write("ae \""+ node1+node2+ "\" \"" + node1 + "\" "+ "\""+node2+"\"\n");
            output.write("ce \""+ node1+node2+ "\" label=\""+ label + "\"\n");

            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
