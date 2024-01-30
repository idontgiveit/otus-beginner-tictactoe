package ru.otus.example;

public class TestReturn {
    public static void main(String[] args) {
        int[] functionCallResult = createSingleDimensioned();

        for (int i = 0; i < 10; i++) {
            System.out.println(functionCallResult[i]);
        }
    }

    public static int[] createSingleDimensioned () {
        System.out.println("called");
        int[] result = new int[10];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }


}
