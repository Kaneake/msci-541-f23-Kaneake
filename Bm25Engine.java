import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Bm25Engine {

    public static int[] getIDsOfTop10(String query, ArrayList<ArrayList<Posting>> invIndex, ArrayList<String> lexicon){
        int[] queryAsIds = GetQueryAsIds(query, lexicon);
        TreeMap<Double, Integer> allResults = new TreeMap<>(Comparator.reverseOrder());
        int[] top10 = new int[10];

        for(int queryID = 0; queryID < queryAsIds.length; queryID++){ //for every term in query
                //Get ArrayList of posting objects of docs and term frequencies for given query term ID
                ArrayList<Posting> posts = invIndex.get(queryID);
                double docLength = 0;
                try {
                    docLength = BM25.GetDocLength(queryID);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                int N = 78731;
                int ni = posts.size();
                double IDF = Math.log((N - ni + 0.5)/(ni + 0.5));

                //BM25(D,Q, frequency of term i in doc D)
                for(int docCount = 0; docCount < posts.size(); docCount++){
                    double result = BM25.calculateBM25(posts.get(docCount).docId, queryID, posts.get(docCount).count, IDF, docLength);
                    allResults.put(result, posts.get(docCount).docId);
                }
            }

            int k = 0;
            for(Map.Entry<Double, Integer> entry : allResults.entrySet()){
                if(k==10) {break;}
                top10[k] = entry.getValue();
                k++;
            }

        System.out.println(top10);
        return top10;
    }


    public static int[] GetQueryAsIds(String query, ArrayList<String> lexicon){
        query = query.toLowerCase();
        String[] tokensArray = query.split("\\s+");
        int[] queryAsIds = new int[tokensArray.length];

        //after the for loop, the queryAsIds is int[], a query as termIds
        for(int i=0; i<tokensArray.length; i++){
            if(lexicon.contains(tokensArray[i])){
                queryAsIds[i] = lexicon.indexOf(tokensArray[i]);
            } else {
                //we dismiss the term we dont have in the lexicon
            }
        }

        return queryAsIds; 
    }
}