package com.company;

import com.company.ast.ContentClass;
import com.company.objects.ASTGenerator;
import com.company.test.A;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");

        ContentClass cc = new ContentClass();

        ASTParser parser = ASTParser.newParser(AST.JLS8);

        String content = cc.getContent("./src/com/company/test/B.java");

        System.out.println(content);

        parser.setSource(content.toCharArray());

        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        /*ASTGenerator generator = new ASTGenerator();
        generator.initialize();
        generator.parseFile(filePath);*/

        try {
            cu.accept(new ASTVisitor() {

                Set names = new HashSet();

                public boolean visit(VariableDeclarationFragment node) {
                    SimpleName name = node.getName();
                    this.names.add(name.getIdentifier());
                    System.out.println("Declaration of '"+name+"' at line "+cu.getLineNumber(name.getStartPosition()));
                    System.out.println(node.getParent());
                    System.out.println("\n");
                    return true;
                }

                public boolean visit(SimpleName node) {
                    if (this.names.contains(node.getIdentifier())) {
                        System.out.println("Usage of '" + node + "' at line " +	cu.getLineNumber(node.getStartPosition()));
                        System.out.println(node.getParent());
                        System.out.println("\n");
                    }
                    return true;
                }

                public boolean visit(MethodDeclaration node) {
                    System.out.println("Method " + node.getName());
                    System.out.println(node.parameters());
                    for(Object param : node.parameters()) {
                        VariableDeclaration variableDeclaration = (VariableDeclaration) param;
                        String type = variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
                        System.out.println("Param : " + variableDeclaration.getName() + "type : " + type);
                    }

                    Block block = node.getBody();
                    block.accept(new ASTVisitor() {
                        public boolean visit(MethodInvocation node) {
                            System.out.println("Name: " + node.getName());

                            Expression expression = node.getExpression();
                            if (expression != null) {
                                System.out.println("Expr: " + expression.toString());
                            }

                            return true;
                        }
                    });

                    System.out.println("\n");
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
