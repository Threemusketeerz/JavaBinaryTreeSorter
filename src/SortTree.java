import java.util.Random;

public class SortTree
{
    private BinaryNode root;
    private Integer[] dataSet;


    public SortTree(BinaryNode root, Integer[] dataSet)
    {
        this.root = root;
        this.dataSet = dataSet;
    }

    public void order(Integer data)
    {
        BinaryNode tempNode = root;
        boolean planted = false;
        System.out.println("--- Growing branch with " + data + " ---");
        while (!planted)
        {
            if (data <= tempNode.getData() && tempNode.getLeftChild() == null)
            {
                BinaryNode newNode = new BinaryNode(data);
                tempNode.setLeftChild(newNode);
                planted = true;
                System.out.println("Planted a new node on left branch!");
            }

            if (data > tempNode.getData() && tempNode.getRightChild() == null)
            {
                tempNode.setRightChild(new BinaryNode(data));
                planted = true;
                System.out.println("Planted a new node on right branch!");
            }

            if (data <= tempNode.getData() && tempNode.getLeftChild() != null && !planted)
            {
                tempNode = tempNode.getLeftChild();
                System.out.println("Setting tempNode to new leftChild");
            }
            else if (data > tempNode.getData() && tempNode.getRightChild() != null && !planted)
            {
                tempNode = tempNode.getRightChild();
                System.out.println("Setting tempNode to new rightChild");
            }
        }
        System.out.println("--- DONE ---");
    }

    public void iterateOverDataSet()
    {
        for (int i = 1; i < dataSet.length; i++)
        {
            order(dataSet[i]);
        }
    }

    public void traverse(BinaryNode root)
    {

        if (root.getLeftChild() != null)
        {
            traverse(root.getLeftChild());
        }

        System.out.print(root.getData());

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
        Integer[] fixedSet2 = { 10, 2, 4, 2, 0, 3, 5, 2, 6, 8, 3, 2, 3, 6, 4, 7};
        Integer[] fixedSet3 = {2, 6, 8, 3, 2, 3, 6, 4, 7}; // Expecting ordered to 2, 2, 3, 4, 6, 6, 7, 8,
        System.out.print("");
        // Init rootNode with a value you want compared to or a datasetvalue.
        // For now it grabs the first value and skips it in the iteration.
        BinaryNode rootNode = new BinaryNode(dataSet[0]);
        SortTree sortTree = new SortTree(rootNode, dataSet);

        sortTree.iterateOverDataSet();
        sortTree.traverse(sortTree.getRoot());

        System.out.println("");
        System.out.println("DATASET: ");
        for (int i = 0; i < amount; i++)
            System.out.print(dataSet[i]);
    }
}
