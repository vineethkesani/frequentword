package Easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class ChampTitles {

    public static void main(String args[]) throws FileNotFoundException, IOException {

        FileReader fr = new FileReader("passage.txt");
        BufferedReader br = new BufferedReader(fr);
        String text = "";
        String sz = null;
        while ((sz = br.readLine()) != null) {
            text = text.concat(sz);
        }
        String[] words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        System.out.println("Total number of words: " + words.length);
        System.out.println("******************************");

        // getting word frequency of each word
        HashMap<String, Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        // Sorting the map based on Value
        HashMap<String, Integer> sortedMap =
                map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry:: getKey, Map.Entry:: getValue, (e1,e2)->e1, LinkedHashMap::new));

        int i=0;
        Iterator<String> itrkey = sortedMap.keySet().iterator();
        Iterator<Integer> itrvalue = sortedMap.values().iterator();
        System.out.println("Top 10 words of the text are: ");

        // looping and printing top 10 frequent words
        while(i<=9 && itrkey.hasNext()){
            System.out.println(itrkey.next() + ": " +itrvalue.next());
            i++;
        }
        System.out.println("******************************");

        Map.Entry<String,Integer> entry = sortedMap.entrySet().iterator().next();
        String topFrequent = entry.getKey();
        String[] sentences = text.toLowerCase().split("(?<=[a-z])\\.\\s+");

        // finding the last sentence with top frequent word
        System.out.println("Last sentence with top frequent word: ");
        for(int j= sentences.length-1; j>=0; j--){
            if(sentences[j].indexOf(" "+topFrequent+ " ") !=-1){
                System.out.println(sentences[j]);
                break;
            }
        }
    }
}
