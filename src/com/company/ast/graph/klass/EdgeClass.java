package com.company.ast.graph.klass;

import com.company.ast.objects.ASTClass;
import com.company.graph.Edge;

public class EdgeClass extends Edge<ASTClass> {

    public EdgeClass(NodeClass node) {
        super(node);
    }
}
