public class Test {

    public static void main(String[] args) {
        String indexStr = "i+1";
        int var1 = indexStr.indexOf("+");
        String var2 = indexStr.substring(var1);
        Integer varIndex = Integer.parseInt(var2); //TODO check var - 0 ou 1 ou plus
        System.out.println(varIndex);

    }
}
