package com.company;

import java.io.File;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;
import com.company.ast.ASTGenerator;

public class Main {

	public static void parseFile(File file) {
		ASTGenerator generator = new ASTGenerator();
        generator.initialize();
        generator.parseFile(file.getAbsolutePath());

        ASTClass root = generator.getClass(file.getName());

        for(ASTVariable att : root.getAttributes()) {
            System.out.println(att.toString());
        }

        for(ASTMethod method : root.getMethods()) {
            System.out.println(method.toString());
        }
	}

	public static void main(String[] args) {
		File directoryToScan = new File("./src/com/company/test");
		File[] files = directoryToScan.listFiles();

		for (File f : files) {
			parseFile(f);
		}
	}
}
