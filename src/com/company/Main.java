package com.company;

import com.company.ast.ContentClass;
import com.company.objects.ASTClass;
import com.company.objects.ASTMethod;
import com.company.objects.ASTVariable;
import com.company.test.A;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");

        ContentClass cc = new ContentClass();

        ASTParser parser = ASTParser.newParser(AST.JLS8);

        String content = cc.getContent("./src/com/company/test/B.java");

        String nameC = "B";

       // System.out.println(content);

        parser.setSource(content.toCharArray());

        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        AST ast = cu.getAST();

        TypeDeclaration typeDeclaration = ((TypeDeclaration) cu.types().get(0));

        System.out.println("Classe: " + typeDeclaration.getName());

        System.out.println("\n");

        List types = cu.types();

        FieldDeclaration fields[] = typeDeclaration.getFields();

        for (FieldDeclaration field : fields) {
            System.out.println("Type Field: " + field.getType());
            System.out.println("Name Field: " + ((VariableDeclarationFragment) field.fragments().get(0)).getName().toString());
        }

        System.out.println("\n");

        MethodDeclaration methods[] = typeDeclaration.getMethods();

        for (MethodDeclaration method : methods) {
            System.out.println("METHOD: " + method.getName());

            System.out.println("Return : " + method.getReturnType2());

            for (Object param : method.parameters()) {
                System.out.println("Parameters : ");
                VariableDeclaration variableDeclaration = (VariableDeclaration) param;
                String type = variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
                System.out.println("Param : " + variableDeclaration.getName() + " - type : " + type);
            }

            System.out.println("BODY: ");

            Block block = method.getBody();
            MethodInvocationVisitor miv = new MethodInvocationVisitor();
            block.accept(miv);

            List<VariableDeclarationStatement> variableDeclarations = miv.getVariableDeclarations();
            for (VariableDeclarationStatement var : variableDeclarations) {
                System.out.println("Variable body: " + var.toString());
                System.out.println("Variable type : " + var.getType());
                System.out.println("Variable name: " + ((VariableDeclarationFragment) var.fragments().get(0)).getName());
            }

            List<MethodInvocation> listmi = miv.getMethods();
            for (MethodInvocation methodBody : listmi) {

                System.out.println("METHOD INVOCATION body: " + methodBody.toString());
                System.out.println("Appelant: " + methodBody.getExpression());
                System.out.println("Method body name : " + methodBody.getName());

                List arguments = methodBody.arguments();
                System.out.println("Arguments : ");
                for (Object arg : arguments) {
                    System.out.println("Argument: " + arg.toString());
                    MethodInvocation variableDeclaration = (MethodInvocation) arg;
                    String type = variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
                    System.out.println("Argument type : " + " - type : " + type);
                }

            }


            System.out.println("\n");

        }



/*

        final ASTClass astClass = new ASTClass(nameC);

        try {
            cu.accept(new ASTVisitor() {

                public boolean visit(VariableDeclarationStatement node) {
                    System.out.println("TITI " + node.getType());
                    System.out.println("Varivel statement name: " + ((VariableDeclarationFragment) node.fragments().get(0)).getName());
                    System.out.println("\n");

                    return true;
                }

                public boolean visit(VariableDeclarationFragment node) {
                    SimpleName name = node.getName();
                    System.out.println("Declaration of '"+name+"' at line "+cu.getLineNumber(name.getStartPosition()));
                    System.out.println("\n");
                    //System.out.println(" b " + node.getParent());
                    //String s = node.getParent().getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
                    //String type = node.getStructuralProperty(SingleVariableDeclaration.NAME_PROPERTY).toString();
                    //System.out.println("Type : " + type);
                    //ASTClass varType = new ASTClass("ee");
                    //ASTVariable variable = new ASTVariable(name.toString(), varType);
                    //astClass.addAttribute(variable);
                    //System.out.println(fd.getType());
                    System.out.println();
                    System.out.println(node.getNodeType());
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
        }*/

    }
}
