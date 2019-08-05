import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Compressor {
    public static void main(String[] args) {
        ArrayList<String> fileContent = openFile("text.txt");
        System.out.println(fileContent.get(0));
        String newContent = findingPatterns(fileContent.get(0));
        System.out.println(newContent);
        // saveFile(newContent, "compressedText.txt");
    }

    public static ArrayList<String> openFile(String fileName) {
        ArrayList<String> records = new ArrayList<String>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while((line= reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
            return records;

        } catch (Exception e) {

          System.err.format("Exception occurred trying to read '%s'.", fileName);
          e.printStackTrace();
          return null;

        }
    }

    public static void saveFile(String newContent, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(newContent);
            writer.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static String findingPatterns(String oldContent) {

        // POR REPETICOES GRANDES DE LETRAS
        // ------------------------------------------------------------------------------------ //
        // StringBuilder newContent = new StringBuilder();
        // int charNumber = 1;
        // char charContent = ' ';

        // // compara letra a letra e descobre o numero de letras repetidas
        // for(int i = 0; i < oldContent.length(); i++) {
        //     if(i + 1 < oldContent.length()) {
        //         charContent = oldContent.charAt(i);

        //         if(charContent == oldContent.charAt(i+1)) {
        //             charNumber++;
        //         } else {
        //             newContent.append(charNumber).append(charContent);            
        //             charNumber = 1;
        //         }
        //     } else {
        //         newContent.append(charNumber).append(charContent);
        //     }
        // }
        // ------------------------------------------------------------------------------------ //

        // POR PADROES NO TEXTO
        // ------------------------------------------------------------------------------------ //

        // padrao usado 'luiz eduardo ferreira da costa carvalho foi a feira e comprou duas batatas'
        StringBuilder newContent = new StringBuilder();

        // inicio da lista de padroes
        newContent.append('\0');
        
        // checa se há padrões no texto
        int repNumber = 1;
        String temp = "";
        for(int j = 0; j < oldContent.length()-3; j++) {
            StringBuilder str = new StringBuilder();
            str.append(oldContent.charAt(j)).append(oldContent.charAt(j+1)).append(oldContent.charAt(j+2));
            for(int i = j+3; i < oldContent.length()-3; i+=3) {
                StringBuilder tempStr = new StringBuilder();
                tempStr.append(oldContent.charAt(i)).append(oldContent.charAt(i+1)).append(oldContent.charAt(i+2));
                if(str.toString().equals(tempStr.toString())) {
                    repNumber++;
                    System.out.println(tempStr.toString());
                    System.out.println(repNumber);  
                }    
            }
            
            repNumber = 1;
        }
        // ------------------------------------------------------------------------------------ //
        return newContent.toString();
    }
}