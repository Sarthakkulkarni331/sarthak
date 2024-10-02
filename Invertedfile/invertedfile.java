import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class invertedfile {
    
    public static void main(String[] args) throws IOException {
        
        String filePath = "C:\\Users\\Student\\Desktop\\Invertedfile\\text1.txt"; // Replace with your own file path
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        String line;
        int lineNumber = 0;
        
        // Map to store the inverted index
        Map<String, Map<Integer, Integer>> invertedIndex = new HashMap<>();
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            
            // Split the line into individual words
            String[] words = line.split(" ");
            
            // Process each word
            for (String word : words) {
                // Remove any punctuation and convert to lowercase
                word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                
                // Add the word to the inverted index
                if (!word.isEmpty()) {
                    if (!invertedIndex.containsKey(word)) {
                        invertedIndex.put(word, new HashMap<>());
                    }
                    Map<Integer, Integer> docMap = invertedIndex.get(word);
                    if (!docMap.containsKey(lineNumber)) {
                        docMap.put(lineNumber, 0);
                    }
                    docMap.put(lineNumber, docMap.get(lineNumber) + 1);
                }
            }
        }
        
        // Print the inverted index
        for (String word : invertedIndex.keySet()) {
            System.out.print(word + " -> ");
            Map<Integer, Integer> docMap = invertedIndex.get(word);
            for (int docId : docMap.keySet()) {
                System.out.print(docId + ":" + docMap.get(docId) + " ");
            }
            System.out.println();
        }
        
        reader.close();
    }
}