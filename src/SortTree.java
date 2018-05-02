import java.io.FileOutputStream;
import java.util.Random;

public class SortTree <T extends Comparable<T>>
{
    private BinaryNode<T> root;
    private T[] dataSet;

    public SortTree(T[] dataSet)
    {
        try
        {
            this.root = new BinaryNode<T>(dataSet[0]);
        }
        catch (ArrayIndexOutOfBoundsException aiooe)
        {
            System.out.println("You cannot add an empty array!");
            aiooe.printStackTrace();
        }
        this.dataSet = dataSet;
    }

    public SortTree(BinaryNode<T> root, T[] dataSet)
    {
        this.root = root;
        this.dataSet = dataSet;
    }

    public void add(T data)
    {
        BinaryNode<T> tempNode = root;
        boolean planted = false;
        System.out.println("--- Growing branch with " + data + " ---");
        if (root.getData() == null)
            root.setData(data);

        while (!planted)
        {
            int comparison = tempNode.getData().compareTo(data);

            if (comparison >= 0 && tempNode.getLeftChild() == null)
            {
                tempNode.setLeftChild(new BinaryNode<T>(data));
                planted = true;
                System.out.println("Planted a new node on left branch!");
            }

            if (comparison < 0 && tempNode.getRightChild() == null)
            {
                tempNode.setRightChild(new BinaryNode<T>(data));
                planted = true;
                System.out.println("Planted a new node on right branch!");
            }

            if (comparison >= 0 && tempNode.getLeftChild() != null && !planted)
            {
                tempNode = tempNode.getLeftChild();
                System.out.println("Setting tempNode to new leftChild");
            }
            else if (comparison < 0 && tempNode.getRightChild() != null && !planted)
            {
                tempNode = tempNode.getRightChild();
                System.out.println("Setting tempNode to new rightChild");
            }
        }
        System.out.println("--- DONE ---");
    }

    public void sort()
    {
        for (int i = 1; i < dataSet.length; i++)
        {
            add(dataSet[i]);
        }
    }

    public void traverse(BinaryNode root)
    {

        if (root.getLeftChild() != null)
        {
            traverse(root.getLeftChild());
        }

        System.out.print(root.getData() + ",");

        if (root.getRightChild() != null)
        {
            traverse(root.getRightChild());
        }
    }

    /**
     * Generate a graphviz visual of the binary tree.
     * @param root          Root of the tree
     * @param fileToWriteTo File to write the DOT language to.
     */
    public void generateVisual(BinaryNode root, FileOutputStream fileToWriteTo)
    {
        if (root.getLeftChild() != null)
        {
            traverse(root.getLeftChild());
        }

//        System.out.print(root.getData() + ",");

        if (root.getRightChild() != null)
        {
            traverse(root.getRightChild());
        }

    }

    public BinaryNode getRoot()
    {
        return root;
    }

    public static Integer[] generateDataSet(Integer amount)
    {
        Integer[] generatedSet = new Integer[amount];
        Random random = new Random();
        for (int i = 0; i < amount; i++)
            generatedSet[i] = random.nextInt(10);

        return generatedSet;
    }

    public static void main(String[] args)
    {
        int amount = 100;
        Integer[] dataSet = SortTree.generateDataSet(amount);

        Integer[] fixedSet = { 7, 5, 6, 3, 2 }; // Expecting ordered to 2, 3, 5, 6, 7
        Integer[] fixedSet2 = { 10, 2, 4, 2, 0, 3, 5, 2, 6, 8, 3, 2, 3, 6, 4, 7 };
        Integer[] fixedSet3 = { 2, 6, 8, 3, 2, 3, 6, 4, 7 }; // Expecting ordered to 2, 2, 3, 4, 6, 6, 7, 8,
        System.out.print("");
        // Init rootNode with a value you want compared to or a datasetvalue.
        // For now it grabs the first value and skips it in the iteration.
//        BinaryNode rootNode = new BinaryNode<Integer>(dataSet[0]);
        // Takes an array of something comparable.
        // I suppose you could make this "addable"
        SortTree sortTree = new SortTree<>(fixedSet2);
        for (int i = 1; i < dataSet.length; i++)
        {
            sortTree.add(dataSet[i]);
        }

        // This would also be able to
//        sortTree.sort();
        sortTree.traverse(sortTree.getRoot());

        System.out.println("");
        System.out.println("DATASET: ");
        for (int i = 0; i < amount; i++)
            System.out.print(dataSet[i] + ",");
    }
}
