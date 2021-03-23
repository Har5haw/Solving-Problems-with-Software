package Part_1_Finding_many_Genes;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        StringBuilder stringBuilder = new StringBuilder(dna);
        int index = 0;
        while(true) {
            index = stringBuilder.indexOf(stopCodon, startIndex + 3);
            if (index == -1 || (index - startIndex) % 3 == 0) {
                break;
            }
            startIndex += 3;
        }
        if (index != -1) {
            return index;
        } else {
            return dna.length();
        }
    }

    public void testFindStopCodon(){
        System.out.println(findStopCodon("ATGOPYOPYTAA", 0, "TAA"));
    }

    public String findGene(String dna, int start) {
        final String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon, start);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }

    public void testFindGene() {
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("Gene: " + findGene(dna, 0));
    }

    public void printAllGenes(String dna) {
        int start = 0;
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + gene);
            start = dna.indexOf(gene, start) + gene.length();
        }
    }

    public void testPrintAllGenes() {
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
    }
}
