import parser.decoration.table.errors.DecorationTableParsingErrorType;

public class Test {

    /*public static void main(String[] args) {
        String indexStr = "i+12";
        int var1 = indexStr.indexOf("+");
        String var2 = indexStr.substring(var1+1);
        Integer varIndex = Integer.parseInt(var2); //TODO check var - 0 ou 1 ou plus
        System.out.println(varIndex);

    }*/

    /*public static void main(String[] args) {
        System.out.println(DecorationTableParsingErrorType.values().length);
        String indexStr = "i+12";
        int var1 = indexStr.indexOf("+");
        String var2 = indexStr.substring(var1+1);
        Integer varIndex = Integer.parseInt("cc"); //TODO check var - 0 ou 1 ou plus
        System.out.println(varIndex);

    }*/

    /*public static void main (String[] args) {
        String str = "a+";
        int tmp1 = str.indexOf("+");
            String leftVal = str.substring(0, tmp1);
            String rightVal = str.substring(tmp1+1);
        System.out.println(rightVal.isEmpty());
            System.out.println(Integer.parseInt(leftVal));

    }*/

    public static void main (String[] args) {
        String str = "coucou %s";
        System.out.println(String.format(str, "Hello"));

    }

}
