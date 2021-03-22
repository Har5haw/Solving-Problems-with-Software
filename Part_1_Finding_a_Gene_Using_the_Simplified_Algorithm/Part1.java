package Part_1_Finding_a_Gene_Using_the_Simplified_Algorithm;

public class Part1 {
    public String findSimpleGene(String dna){
        StringBuilder stringBuilder = new StringBuilder(dna);
        int indexATG = stringBuilder.indexOf("ATG");
        if(indexATG == -1) return "";
        int indexTAA = stringBuilder.indexOf("TAA", indexATG);
        if(indexTAA == -1) return "";
        if((indexTAA - indexATG)%3 == 0){
            return stringBuilder.substring(indexATG+3, indexTAA);
        }else{
            return "";
        }
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("ATGUYTUYTTAA"));
    }
}
