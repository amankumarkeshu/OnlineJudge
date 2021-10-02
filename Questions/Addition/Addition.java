import java.util.Scanner;

class Addition {
    public int add(int a, int b) {
        // CRIO_SOLUTION_START_MODULE_L1_PROBLEMS
        return a + b;
        // CRIO_SOLUTION_END_MODULE_L1_PROBLEMS
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        int result = new Addition().add(a,b);
        System.out.println(result);
    }
}
