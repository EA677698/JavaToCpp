package me.erick;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input Java file input:");
        String URL = input.nextLine();
        if(URL.contains(".java")){
            try {
                input.close();
                File subject = loadFile(URL);
                Scanner reader = new Scanner(subject);
                File output = new File(System.getProperty("user.dir") + "\\output.cpp");
                PrintWriter writer = new PrintWriter(output, "UTF-8");
                while (reader.hasNextLine()){
                    checkSyntax(writer, reader.nextLine());
                }
                writer.close();
//                File cpp = new File(System.getProperty("user.dir") + "\\output.txt");
//                output.renameTo(cpp);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else{
            System.out.println("Input file is not a Java file.");
        }
    }

    public static File loadFile(String URL){
        try {
            File javaCode = new File(URL);
            return javaCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTranslatedCode(PrintWriter writer, String line){

        try{
            writer.println(line);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void checkSyntax(PrintWriter writer, String line){
        String input = line;
        input = input.replace("public static void main(String[] args)","int main()");
        input = input.replace("public","");
        input = input.replace("private","");
        input = input.replace("String", "string");
        input = input.replace("indexOf","find");
        input = input.replace("substring","substr");
        if(input.contains("System.out.println")) {
            input = line.substring(0,line.length()-1);
            input += "<<endl;";
            input = input.replace("System.out.println", "cout<<");
        }
        System.out.println(input);
        writeTranslatedCode(writer,input);
    }

}
