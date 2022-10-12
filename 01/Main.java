import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> nums = loadInput("input.txt");
        
        List<Integer> fuelReqs = gatherAllFuelReq(nums);
        int sums = fuelReqs.stream().reduce(0, Integer::sum);
        System.out.println(sums);

        List<Integer> fuelReqs2 = gatherAllFuelReq2(nums);
        int sums2 = fuelReqs2.stream().reduce(0, Integer::sum);
        System.out.println(sums2);
    }

    /**
     * Returns an ArrayList<integer> of all the numbers in `filename`, with each number being
     * separated by a new line.
     * @param filename the file with all numbers.
     * @return         an ArrayList<Integer> of numbers from `filename`.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static final List<Integer> loadInput(String filename) throws FileNotFoundException, IOException {
        BufferedReader fin = new BufferedReader(new FileReader(filename));
        
        ArrayList<Integer> nums = new ArrayList<>();

        String current;
        while ((current = fin.readLine()) != null) {
            nums.add(Integer.valueOf(current));
        }

        fin.close();

        return Collections.unmodifiableList(nums);
    }

    private static int calculateFuelReq(int mass) {
        return (int) (Math.floor(mass/3) - 2);
    }

    private static int calculateFuelReq2(int mass) {
        int fuel = 0;

        int current = mass;
        while ((current = calculateFuelReq(current)) > 0) {
            fuel += current;
        }

        return fuel;
    }

    private static List<Integer> gatherAllFuelReq(List<Integer> masses) {
        ArrayList<Integer> fuelReqs = new ArrayList<>();

        for (Integer mass : masses) {
            fuelReqs.add(calculateFuelReq(mass));
        }

        return Collections.unmodifiableList(fuelReqs);
    }

    private static List<Integer> gatherAllFuelReq2(List<Integer> masses) {
        ArrayList<Integer> fuelReqs = new ArrayList<>();

        for (Integer mass : masses) {
            fuelReqs.add(calculateFuelReq2(mass));
        }

        return Collections.unmodifiableList(fuelReqs);
    }
}