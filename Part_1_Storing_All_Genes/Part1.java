package Part_1_Storing_All_Genes;

import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = 0;
        while(true) {
            index = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);
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

    public void testFindStopCodon() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int index = findStopCodon(dna, 0, "TAA");
        System.out.println("Index = " + index);
    }

    public String findGene(String dna, int start) {
        final String startCodon = "ATG";
        int startIndex = dna.toUpperCase().indexOf(startCodon, start);
        if (startIndex == -1) {
            return "";
        }
        int index1 = findStopCodon(dna, startIndex, "TAA");
        int index2 = findStopCodon(dna, startIndex, "TAG");
        int index3 = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(index1, Math.min(index2, index3));
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

    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource geneList = new StorageResource();
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            geneList.add(gene);
            start = dna.indexOf(gene, start) + gene.length();
        }
        return geneList;
    }

    public void testGetAllGenes() {
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource geneList = getAllGenes(dna);
        System.out.println("Testing getAllGenes on " + dna);
        for(String gene : geneList.data()) {
            System.out.println("Gene: " + gene);
        }
    }
}
