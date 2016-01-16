package com.company.ast.graph.method;

import com.company.ast.objects.ASTMethod;
import com.company.graph.Edge;
import com.company.graph.Node;


public class NodeMethod extends Node<ASTMethod> {
    /**
     * @param value : la classe que l'on veut stocker dans un noeud
     */
    public NodeMethod(ASTMethod value) {
        super(value);
    }

    @Override
    public String toString() {
        String result =  "Node{" +
                "value = " + getValue().getName();


        result += "\nparents : " + (getParents().size()) + " \n\t";

        for (Edge parent : getParents()) {
            result += ((ASTMethod) parent.getNode().getValue()).getName() + " - ";
        }

        result += "\nchild : " + (getChildren().size()) + " \n\t";

        for (Edge child : getChildren()) {
            result += ((ASTMethod) child.getNode().getValue()).getName() + " - ";
        }
        return result;
    }
}
