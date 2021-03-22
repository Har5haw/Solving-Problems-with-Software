package Part_4_Finding_Web_Links;

import edu.duke.URLResource;

public class Part4 {
    public void readUrl(){
        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : urlResource.words()){
            if(word.length() > 0){
                if(word.contains("youtube.com")){
                    StringBuilder stringBuilder = new StringBuilder(word);
                    int index1 = stringBuilder.indexOf("\"");
                    int index2 = stringBuilder.indexOf("\"", index1+1);
                    System.out.println(stringBuilder.substring(index1+1, index2));
                }
            }
        }
    }
}
