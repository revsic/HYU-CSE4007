package assignment1.test;


public class Application {
    public static void main(String[] args) {
        NQueensTest nQueensTest = new NQueensTest();
        if (!nQueensTest.test()) {
            System.out.println("nqueens test failure");
            return;
        }

        System.out.println("Test all pass");
    }
}