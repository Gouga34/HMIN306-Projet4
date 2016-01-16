package com.company.ast.graph;


import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.graph.DiGraph;

public class CallGraph {

    private DiGraph<ASTClass> graph = new DiGraph<ASTClass>();

    public CallGraph() {



    }

    public DiGraph<ASTClass> getGraph() {
        return graph;
    }

    public DiGraph<ASTMethod> getGraphMethod(ASTClass cls) {

        DiGraph<ASTMethod> graph = new DiGraph<ASTMethod>();


            

        return graph;
    }


}
