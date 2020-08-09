import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] arr = str.split("\\s+");
            System.out.println(arr[arr.length - 1].trim().length());
        }
    }
}