public class Test {

    /*public static void main(String[] args) {
        String indexStr = "i+12";
        int var1 = indexStr.indexOf("+");
        String var2 = indexStr.substring(var1+1);
        Integer varIndex = Integer.parseInt(var2); //TODO check var - 0 ou 1 ou plus
        System.out.println(varIndex);

    }*/

    public static void main(String[] args) {
        String indexStr = "i+12";
        int var1 = indexStr.indexOf("+");
        String var2 = indexStr.substring(var1+1);
        Integer varIndex = Integer.parseInt("cc"); //TODO check var - 0 ou 1 ou plus
        System.out.println(varIndex);

    }
}
