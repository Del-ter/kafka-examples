public class Util_ToPositive {
    public int toPositive(int number) {
        return number & 2147483647;
    }

    public static void main(String[] args) {
        Util_ToPositive utilExamples = new Util_ToPositive();
        System.out.println(utilExamples.toPositive(3));
    }
}
