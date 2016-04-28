
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConvertXMLtoJSON {
  public static void main(final String[] args) throws Exception{
	  
	Scanner ob=new Scanner(System.in);
    System.out.println("Choose the JSON file you want to generate: ");
    System.out.println("\t 1. buildings.json (Please make sure you have the respective buldings.xml in the classpath.)");
    System.out.println("\t 2. students.json  (Please make sure you have the respective students.xml in the classpath.)");
    String name=ob.nextLine(); 
    
    File inputFile = null;
    String outputFileName = "";
    if(name.equals("1")){
    	inputFile = new File("buildings.xml");
    	outputFileName = "buildings.json";
    }else{
    	inputFile = new File("students.xml");
    	outputFileName = "students.json";
    }
    try{
      final InputStream inputStream = new FileInputStream(inputFile);
      final StringBuilder builder = new StringBuilder();
      int ptr = 0;
      while ((ptr = inputStream.read()) != -1)
      {
        builder.append((char) ptr);
      }

      final String xml = builder.toString();
      final JSONObject jsonObj = XML.toJSONObject(xml);
      // System.out.println(jsonObj.toString());
      // System.out.println(jsonObj.toString().split(",").length);
      final FileWriter fileWriter = new FileWriter(outputFileName);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      for (int i = 0; i < jsonObj.toString(2).split(",").length; i++) {
        System.out.print(jsonObj.toString(2).split(",")[i]);
        bufferedWriter.write(jsonObj.toString(2).split(",")[i]);
        if(i < jsonObj.toString(2).split(",").length -1){
            System.out.println(",");
        bufferedWriter.write(",");
        }
      }

      inputStream.close();
      bufferedWriter.close();
    }
    catch (final IOException ex) {
      System.out.println(
          "Error writing to file '"
          + outputFileName + "'");
      // Or we could just do this:
      // ex.printStackTrace();
    } catch (final Exception e)
    {
      e.printStackTrace();
    }
  }
}