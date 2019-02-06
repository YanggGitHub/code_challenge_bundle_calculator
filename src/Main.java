import java.util.ArrayList;

public class Main {

    private static int modResult(int inputValue, int bundleValue) {
        return inputValue % bundleValue;
    }

    private static int subResult(int looResult, int bundleValue) {
        return looResult - loopNum(looResult, bundleValue) * bundleValue;
    }

    private static int loopNum(int inputValue, int bundleValue) {
        return inputValue / bundleValue;
    }

    private static ArrayList<Integer> loopResult(int inputValue, int bundleValue, int loopNum) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = loopNum; i > 0; i--) {
            result.add(inputValue - (bundleValue * i));
        }
        return result;
    }

    private static void printArray(ArrayList<Integer> list) {
        for (int x : list) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {

        boolean hasSolution = false;
        int inputValue = 18;
//      int inputValue = 18;


        ArrayList<Integer> bundleValue = new ArrayList<>();
        bundleValue.add(9);
//        bundleValue.add(6);
//        bundleValue.add(3);

        ArrayList<Integer> outPut = new ArrayList<>();

        while (hasSolution == true) {
            System.out.println(inputValue);
            for (int i = 0; i < bundleValue.toArray().length; i++) {
                boolean hasLoopSolution = false;
                outPut.clear();
                int currentBundleValue1 = bundleValue.get(i);

                // Check if there is only 1 value in the bundle and if this bundle value works
                if (bundleValue.toArray().length == 1 && modResult(inputValue, currentBundleValue1) == 0) {
                    outPut.add(loopNum(inputValue, currentBundleValue1));
                    outPut.add(currentBundleValue1);
                    hasLoopSolution = hasSolution = true;
                    printArray(outPut);
                    break;
                }
                // Check if there is only 1 value left in the bundle loop and if this bundle value works
                if (i + 1 == bundleValue.toArray().length && modResult(inputValue, currentBundleValue1) == 0) {
                    outPut.add(loopNum(inputValue, currentBundleValue1));
                    outPut.add(currentBundleValue1);
                    hasLoopSolution = hasSolution = true;
                    printArray(outPut);

                    break;
                }

                int loopNumValue = loopNum(inputValue, bundleValue.get(i)); // 9->3 6->5 3->11
                ArrayList<Integer> loopResultValue = loopResult(inputValue, currentBundleValue1, loopNumValue);
                for (int y : loopResultValue) {
                    outPut.clear();
                    outPut.add(loopNumValue);
                    outPut.add(currentBundleValue1);
                    //Jump to the next value if the first mod value of this loop equals to 0
                    if (y == 0) {
                        hasLoopSolution = hasSolution = true;
                        continue;
                    }

                    for (int j = i + 1; j <= bundleValue.toArray().length; j++) {
                        if (j >= bundleValue.toArray().length) {
                            break;
                        }
                        int currentBundleValue2 = bundleValue.get(j);
                        int remainValue = modResult(y, currentBundleValue2);

                        if (remainValue == 0) {
                            int loopNumResult2 = loopNum(y, currentBundleValue2);
                            outPut.add(loopNumResult2);
                            outPut.add(currentBundleValue2);
                            hasLoopSolution = hasSolution = true;
                            break;
                        }
                        y = subResult(y, currentBundleValue2);

                    }
                    if (hasLoopSolution == true) {
                        printArray(outPut);
                    }
                }
            }
            if (hasSolution == false) {
                inputValue = inputValue + 1;
            }


        }
    }
}








