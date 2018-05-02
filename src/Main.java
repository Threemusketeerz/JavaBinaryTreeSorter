import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Main
{

    private static Integer[] generateDataSet(Integer amount)
    {
        Integer[] generatedSet = new Integer[amount];
        Random random = new Random();
        for (int i = 0; i < amount; i++)
            generatedSet[i] = random.nextInt(100);

        return generatedSet;
    }


    public static void main(String[] args)
    {
        int amount = 1000;
        Integer[] dataSet = generateDataSet(amount);

        Integer[] fixedSet = { 7, 5, 6, 3, 2 }; // Expecting ordered to 2, 3, 5, 6, 7
        Integer[] fixedSet2 = { 10, 2, 4, 2, 0, 3, 5, 2, 6, 8, 3, 2, 3, 6, 4, 7};
        Integer[] fixedSet3 = {2, 6, 8, 3, 2, 3, 6, 4, 7}; // Expecting ordered to 2, 2, 3, 4, 6, 6, 7, 8,

        SortTree sortTree = new SortTree<Integer>();

        for (Integer i : dataSet)
        {
            sortTree.add(i);
        }

        sortTree.traverse(sortTree.getRoot());

        Path currentRelativePath = Paths.get("");

        // Builds the DOT file to a file in directory call dotfile.dot
        File dotFile = new File(currentRelativePath.toAbsolutePath().toString() + "/dotfile.dot");
        sortTree.buildDotFile(dotFile);


        System.out.println("");
        System.out.println("DATASET: ");
        for (int i = 0; i < amount; i++)
            System.out.print(dataSet[i] + ",");
    }
}
