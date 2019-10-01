package test;


/**
 * Application for unit test.
 */
public class Application {
    /**
     * Run unittest and show result.
     * @param args @deprecated not used.
     */
    public static void main(String[] args) {
        NQueensTest nQueensTest = new NQueensTest();
        if (!nQueensTest.test()) {
            System.out.println("nqueens test failure");
            return;
        }

        System.out.println("Test all pass");
    }
}
