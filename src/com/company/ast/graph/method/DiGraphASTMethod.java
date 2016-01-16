package com.company.ast.graph.method;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;
import com.company.graph.DiGraph;
import com.company.graph.EdgeType;
import com.company.graph.Node;
import com.company.utils.Pair;

import java.util.List;

public class DiGraphASTMethod extends DiGraph<ASTMethod> {

    private ASTClass cls;

    public DiGraphASTMethod(ASTClass cls) {
        this.cls = cls;
    }

    @Override
    public void addNode(ASTMethod c) {
        NodeMethod node = new NodeMethod(c); // part défault on créer un nouveau noeud

        if(!haveThisNode(node)) // on vérifie si notre noeud nes pas déjà dans notre graphe
            getNodes().add(node); // on ajoute notre noeud au graphe
        else
            node = (NodeMethod) getNode(c); // sinon on récupère le noeud dans notre graphe

        for(Pair<ASTVariable, ASTMethod> pair : c.getCalledMethods()) {

            ASTMethod method = pair.getValue2();

            System.out.println("mm : " + method.getName());

            if(cls.equals(method.getContainerClass())) {

                NodeMethod callM = new NodeMethod(method);

                if (haveThisNode(callM)) {
                    NodeMethod temp = (NodeMethod) getNode(method);
                    temp.addEdge(node, EdgeType.IN);
                    node.addEdge(temp, EdgeType.OUT);
                }
                else {
                    getNodes().add(callM);
                    callM.addEdge(node, EdgeType.IN);
                    node.addEdge(callM, EdgeType.OUT);
                }
            }

        }
    }

}
