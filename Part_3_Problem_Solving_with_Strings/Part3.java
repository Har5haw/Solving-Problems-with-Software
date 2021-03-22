package Part_3_Problem_Solving_with_Strings;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        StringBuilder stringBuildera = new StringBuilder(stringa);
        StringBuilder stringBuilderb = new StringBuilder(stringb);

        int index1 = stringBuilderb.indexOf(stringa);
        if(index1 == -1) return false;
        int index2 = stringBuilderb.indexOf(stringa, index1+stringa.length());
        if(index2 == -1) return false;
        return true;
    }

    public void testing(){
        System.out.println(twoOccurrences("shaw", "harshawharshaw"));
        System.out.println(twoOccurrences("shaw1", "harshaw1harshaw"));
    }
}
