package com.company.ast.graph;


import com.company.ast.graph.klass.DiGraphASTClass;
import com.company.ast.graph.method.DiGraphASTMethod;
import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.graph.DiGraph;

import java.util.List;

public class CallGraph {

    public CallGraph() {



    }

    public DiGraphASTClass getGraphClass(List<ASTClass> clss) {
        DiGraphASTClass graph = new DiGraphASTClass(clss);

        for(ASTClass cl : clss) {
            graph.addNode(cl);
        }

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
