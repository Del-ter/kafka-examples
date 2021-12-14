public class Util_Matches {
    public static void main(String[] args) {
        String a ="新北兴华养猪场";
        String b ="兴华";
        if (a.matches(".*"+b+".*")){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
