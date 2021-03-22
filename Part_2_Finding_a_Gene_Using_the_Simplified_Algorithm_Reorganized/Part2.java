package Part_2_Finding_a_Gene_Using_the_Simplified_Algorithm_Reorganized;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        boolean isUpper = Character.isUpperCase(dna.charAt(0));
        StringBuilder stringBuilder = new StringBuilder(dna.toLowerCase());
        int indexATG = stringBuilder.indexOf(startCodon.toLowerCase());
        if(indexATG == -1) return "";
        int indexTAA = stringBuilder.indexOf(stopCodon.toLowerCase(), indexATG);
        if(indexTAA == -1) return "";
        if((indexTAA - indexATG)%3 == 0){
            if(isUpper) {
                return stringBuilder.substring(indexATG + 3, indexTAA).toUpperCase();
            }
            else {
                return stringBuilder.substring(indexATG + 3, indexTAA);
            }
        }else{
            return "";
        }
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("ATGUYTUYTTAA", "ATG", "TAA"));
    }
}
