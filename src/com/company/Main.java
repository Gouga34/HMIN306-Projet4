package com.company;

import com.company.ast.ContentClass;
import com.company.objects.ASTClass;
import com.company.objects.ASTMethod;
import com.company.objects.ASTVariable;
import com.company.objects.ASTGenerator;
import com.company.test.A;
import org.eclipse.jdt.core.dom.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        ASTGenerator generator = new ASTGenerator();
        generator.initialize();
        generator.parseFile("./src/com/company/test/B.java");

        ASTClass root = generator.getClass("B.java");

        for(ASTVariable att : root.getAttributes()) {
            System.out.println(att.toString());
        }

        for(ASTMethod method : root.getMethods()) {
            System.out.println(method.toString());
        }


                /*
            List<MethodInvocation> listmi = miv.getMethods();
            for (MethodInvocation methodBody : listmi) {

                System.out.println("METHOD INVOCATION body: " + methodBody.toString());
                System.out.println("Appelant: " + methodBody.getExpression());
                System.out.println("Method body name : " + methodBody.getName());

                List arguments = methodBody.arguments();
                System.out.println("Arguments : ");
                for (Object arg : arguments) {
                    System.out.println("Argument: " + arg.toString());
                  //  MethodInvocation variableDeclaration = (MethodInvocation) arg;
                    //String type = variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
                    //System.out.println("Argument type : " + " - type : " + type);
                }

            }
*/
    }
}
