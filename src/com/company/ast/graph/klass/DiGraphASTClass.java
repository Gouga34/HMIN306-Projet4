package com.company.ast.graph.klass;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.graph.DiGraph;
import com.company.graph.EdgeType;

import java.util.ArrayList;
import java.util.List;

public class DiGraphASTClass extends DiGraph<ASTClass> {

    private List<ASTClass> allClass = new ArrayList<ASTClass>();

    public DiGraphASTClass(List<ASTClass> allClass) {
        this.allClass = allClass;
    }

    @Override
    public void addNode(ASTClass c) {

        System.out.println("I1 : " + c.getName());

        NodeClass node = new NodeClass(c);

        if(!haveThisNode(node))
            getNodes().add(node);
        else
            node = (NodeClass) getNode(c);

        for(ASTClass cls : allClass) {

            if(!c.equals(cls)) {

                System.out.println("I2 : " + cls.getName());

                NodeClass nc = new NodeClass(cls);

                int weight = 0;

                for (ASTMethod mtd : c.getMethods()) {
                    weight += mtd.getWeightOfMethod(cls);
                }

                System.out.println("WEIGHT : " + weight);

                if (weight > 0) {

                    if (haveThisNode(nc)) {
                        NodeClass temp = (NodeClass) getNode(cls);
                        temp.addEdge(node, EdgeType.IN);
                        EdgeClass e = node.addEdge(temp, EdgeType.OUT);
                        if (e != null)
                            e.setWeight(weight);
                    } else {
                        getNodes().add(nc);
                        nc.addEdge(node, EdgeType.IN);
                        EdgeClass e = node.addEdge(nc, EdgeType.OUT);
                        if (e != null)
                            e.setWeight(weight);
                    }
                }
            }
        }
    }
}
