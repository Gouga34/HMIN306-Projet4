package com.company.ast.graph.klass;

import com.company.ast.objects.ASTClass;
import com.company.graph.Edge;
import com.company.graph.EdgeType;
import com.company.graph.Node;

public class NodeClass extends Node<ASTClass> {

    public NodeClass(ASTClass value) {
        super(value);
    }

    public EdgeClass addEdge(NodeClass n, EdgeType et) {

        EdgeClass e = null;

        if(!isSameNode(n)) {
            if(et.equals(EdgeType.IN)) { // si on veut une relation parent
                if(!haveThisParent(n)) {
                    e = new EdgeClass(n);
                    getParents().add(e);
                }
            }
            else if (et.equals(EdgeType.OUT)) { // si on veut une relation enfant
                if(!haveThisChild(n)) {
                    e = new EdgeClass(n);
                    getChildren().add(e);
                }
            }
        }

        return e;
    }

    @Override
    public String toString() {
        String result =  "Node{" +
                "value = " + getValue().getName();

        result += "\nparents : " + (getParents().size()) + " \n\t";

        for (Edge parent : getParents()) {
            result += ((ASTClass) parent.getNode().getValue()).getName() + " : " + parent.getWeight() +  " - ";
        }

        result += "\nchild : " + (getChildren().size()) + " \n\t";

        for (Edge child : getChildren()) {
            result += ((ASTClass) child.getNode().getValue()).getName() + " : " + child.getWeight() + " - ";
        }
        return result;
    }

}
