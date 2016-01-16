package com.company.ast.graph.method;

import com.company.ast.objects.ASTMethod;
import com.company.graph.Edge;
import com.company.graph.Node;


public class EdgeMethod extends Edge<ASTMethod> {

    public EdgeMethod(NodeMethod node) {
        super(node);
    }
}
