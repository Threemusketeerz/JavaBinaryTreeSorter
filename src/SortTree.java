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

    public void order(BinaryNode root, Integer dataSetIndex)
    {
        root.setData(dataSetIndex);

        BinaryNode leftNode = root.getLeftChild();
        BinaryNode rightNode = root.getRightChild();

        Integer data = dataSet[dataSetIndex];

        if (leftNode != null && leftNode.getData() <= data)
        {
            order(leftNode, dataSetIndex);
        }
        else if (rightNode != null && rightNode.getData() > data)
            order(rightNode, dataSetIndex);
        else if (leftNode == null)
        {
            root.setLeftChild(new BinaryNode(data));
            leftNode = root.getLeftChild();
            order(leftNode, dataSetIndex + 1);
        }
        else
        {
            root.setRightChild(new BinaryNode(data));
            rightNode = root.getLeftChild();
            order(rightNode, dataSetIndex + 1);
        }
    }

    public void traverse(BinaryNode root)
    {
        BinaryNode node = root;

        if (node.getLeftChild() != null)
        {
            while (node.getLeftChild() != null)
            {
                node = node.getLeftChild();
                traverse(node);
            }

            if (node.getRightChild() != null)
            {
                node = node.getRightChild();
                traverse(node);
            }
        }
        System.out.print(node.getData());
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
        Integer[] dataSet = SortTree.generateDataSet(100);
        SortTree sortTree = new SortTree(new BinaryNode(dataSet[0]), dataSet);

        sortTree.order(sortTree.getRoot(), 1);
        sortTree.traverse(sortTree.getRoot());
    }

}
