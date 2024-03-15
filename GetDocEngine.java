import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class GetDocEngine {
    @SuppressWarnings("unlikely-arg-type")
    public static String retriveInfo(int docID) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, IOException{
        FileReader fr = new FileReader("addressBook.txt");
        String rString = "";
        BufferedReader br = new BufferedReader(fr);
        String line;
        String pathString = "";

        //find doc address using id
        while((line = br.readLine())!=null){
                String[] arr = line.split("@");
                String idContent = (String) Array.get(arr,0);

                if(idContent.equals(docID)){
                    String[] arr2 = line.split("@");
                    pathString += (String) Array.get(arr2,4);
                    break;
                }
        }
        String[] arr3;
        arr3 = line.split("@");
        rString += Array.get(arr3, 2) + " (" + Array.get(arr3, 1)+ ")\n" + GetSnippet(pathString) + " ("+ Array.get(arr3, 3) + ")";
        
        System.out.println(rString);

        br.close();
        return rString;
    }

    public static String GetSnippet(String pathString) throws IOException{
        String line;
        String snippet="";
        int t = 0;
        FileReader fr = new FileReader(pathString);
        BufferedReader br = new BufferedReader(fr);
        while((line = br.readLine())!=null){
            if(line.contains("<TEXT>")){
                t=1;
            } else if(line.contains("<P>") || line.contains("</P>")){
                //do nothing, dont add that line to 'text to token'
            } else if(t==1){
                snippet = line;
            }
        }

        br.close();
        return snippet;

    }
}
