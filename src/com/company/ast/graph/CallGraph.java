package com.company.ast.graph;


import com.company.ast.graph.method.DiGraphASTMethod;
import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.graph.DiGraph;

public class CallGraph {

    private DiGraph<ASTClass> graph;

    public CallGraph() {



    }

    public DiGraph<ASTClass> getGraph() {
        return graph;
    }

    public DiGraphASTMethod getGraphMethod(ASTClass cls) {

        DiGraphASTMethod graph = new DiGraphASTMethod(cls);

        for(ASTMethod method : cls.getMethods()) {
            graph.addNode(method);
        }

        return graph;
    }


}
