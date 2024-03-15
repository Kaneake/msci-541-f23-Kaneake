import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner; 

public class HW5 {
    //creates lexicon where index is integer id, and the value is the term
    public static ArrayList<String> lexicon = new ArrayList<String>();
    //inverted index: arraylist of arraylists of objects that contain docId and Count params
    public static ArrayList<ArrayList<Posting>> invIndex = new ArrayList<ArrayList<Posting>>();
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, IOException {
        String queryEntry = "";
        Scanner input = new Scanner(System.in);
        GetLexiconAndIndex("output.txt");
        System.out.println("Inverted index and lexicon are loaded.");

        while(queryEntry.toLowerCase() != "Q"){
            System.out.print("Enter query: ");
            queryEntry = input.nextLine();
            if(queryEntry == "Q"){System.exit(0);}

            long startTime = System.currentTimeMillis();
            int[] top10DocIDs = Bm25Engine.getIDsOfTop10(queryEntry, invIndex, lexicon);

            for(int i = 0; i<top10DocIDs.length; i++){
                System.out.println(i + ". " + GetDocEngine.retriveInfo(top10DocIDs[i]));
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nRetrieval took " + (endTime - startTime) + " seconds.");
        }
        
        input.close();
    }

    //Deserialize lexicon and index
    @SuppressWarnings (value="unchecked")
    public static void GetLexiconAndIndex(String indexPathString){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(indexPathString));         
            invIndex = (ArrayList<ArrayList<Posting>>)in.readObject();
            lexicon = (ArrayList<String>)in.readObject();
            in.close();
        }catch(Exception e){System.out.println(e);}
    }
}
