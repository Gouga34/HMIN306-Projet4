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

    /**
     *
     * @param clss
     * @return
     */
    public DiGraphASTClass getGraphClass(List<ASTClass> clss) {
        DiGraphASTClass graph = new DiGraphASTClass(clss);

        for(ASTClass cl : clss) {
            graph.addNode(cl);
        }

        return graph;
    }

    /**
     * Permet de calculer le graphe d'appel au sein d'une classe
     * @param cls : une ASTClass
     * @return un DiGraph contenant les méthodes de la classes
     */
    public DiGraphASTMethod getGraphMethod(ASTClass cls) {

        DiGraphASTMethod graph = new DiGraphASTMethod(cls);

        //System.out.println(cls.getMethods().size());

        for(ASTMethod method : cls.getMethods()) {
            //System.out.println(method.getName());
            graph.addNode(method);
        }

        return graph;
    }

}
