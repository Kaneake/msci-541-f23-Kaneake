/**
 * Author: kanykei nurmambetova
 * Date: Sept 24, 2023
 * Course: MSCI 541
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetDoc {
    public static void main(String[] args) {
        //PART A) The program accepts 3 command line arguments
        String storagePathString = "";
        boolean isInputID = false;
        String idOrDocnoString = "";
        try{
            storagePathString = args[0];
            isInputID = args[1].equals("id");
            idOrDocnoString = args[2];

            //Feedback of what id is provided
            if(isInputID){System.out.println("You provided internal integer id.");}
            else{System.out.println("You provided DOCNO of a document.");}
        } catch(ArrayIndexOutOfBoundsException e){
            //PART B) if no arguments provided to the program
            System.out.println("Only One, Two, or No arguments supplied to the program. " 
            + "Please enter all three arguments: \n " +
            " 1. the path to the location of documents and metadata provided to IndexEngine.java\n "
            + "2. type either [id] or [docno] \n" 
            + " 3. type the internal integer id or DOCNO of a document. \nExiting program.");
            e.printStackTrace();
            System.exit(0);
        }



        //PART C) Look up of a document and fetching
        try {
            FileReader fr = new FileReader("addressBook.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String pathString = "";

            //PART F) Make sure retrival is possible with either id or DOCNO
            if (isInputID) { //search by id
                while((line = br.readLine())!=null){
                    String[] arr = line.split("@");
                    String idContent = (String) Array.get(arr,0);

                    if(idContent.equals(idOrDocnoString)){
                        String[] arr2 = line.split("@");
                        pathString += (String) Array.get(arr2,4);
                        break;
                    }
                }
            } else { //search by docno
                while((line = br.readLine())!=null){
                    if(line.contains(idOrDocnoString)){
                        String[] arr2 = line.split("@");
                        pathString += (String) Array.get(arr2,4);
                        break;
                    }
                }
            }
            fr.close();

            //PART E) Produce nice output of Metadata from Part D) and the whole fetched document including <DOC></DOC> tags
            //PART D) Fetch id, DOCNO, date, and Headline if it exists
            String[] arr3 = line.split("@");
            System.out.println("docno: "+ Array.get(arr3, 3) + "\n"
                                + "internal id: " + Array.get(arr3, 0) + "\n"
                                + "date: " + Array.get(arr3, 1) + "\n"
                                + "headline: " + Array.get(arr3, 2) + "\n");
            //use pathString to fetch String
            var out = new BufferedOutputStream(System.out);
            Files.copy(Paths.get(pathString), out);
            out.flush();


        } catch (FileNotFoundException e) {
            System.out.println("GetDoc cannot find addressBook.txt");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
