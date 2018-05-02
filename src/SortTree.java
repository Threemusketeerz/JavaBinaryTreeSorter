import java.io.*;
import java.util.Random;

/**
 * Made generic and dependant on Comparable<T> to be able to sort..
 * @param <T> Generic type
 */
public class SortTree <T extends Comparable<T>>
{
    // Root node, starting point of everything.
    private BinaryNode<T> root;

    // This is solely created for the purposes of Graphviz.
    static private int nullCount = 0;

    public SortTree()
    {
        this.root = null;
        nullCount = 0;
    }

    public SortTree(T data)
    {
        this.root = new BinaryNode<>(data);
    }

    /**
     * Add data to the sorted tree. The data you add will automatically be positioned in an ordered manner.
     * @param data Wanted data to be passed and ordered into binary tree
     */
    public void add(T data)
    {
        if (root == null)
        {
            root = new BinaryNode<>(data);
            return;
        }

        // Create a node we can iterate with. It starts out as root.
        BinaryNode<T> tempNode = root;
        // For the purpose of checking wether we've "planted" our data yet.
        boolean planted = false;


        System.out.println("--- Growing branch with " + data + " ---");
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
     * @param root  Root of the tree
     * @param body  to write the DOT language to.
     */
    private void generateDotBody(BinaryNode root, StringBuilder body)
    {

        if (root.getLeftChild() != null)
        {
            body.append("\"" + root.toString()+ "\"" + "->" + "\"" + root.getLeftChild().toString()+ "\"" + ";\n");
            generateDotBody(root.getLeftChild(), body);
        }
        else
        {
            nullCount++;
            body.append("null" + nullCount + " [shape=point]; \n");
            body.append("\"" + root.toString()+ "\"" + "->null" + nullCount + ";\n");
        }


        if (root.getRightChild() != null)
        {
            body.append("\"" + root.toString()+ "\"" + "->"
                    + "\"" + root.getRightChild().toString() + "\"" + ";\n");
            generateDotBody(root.getRightChild(), body);
        }
        else
        {
            nullCount++;
            body.append("null" + nullCount + " [shape=point]; \n");
            body.append("\"" + root.toString()+ "\"" + "->null" + nullCount + ";\n");
        }

    }

    /**
     * Builds the DOT file to be shown on viz-js.com
     * @param outputFile                File to write to
     */

    public void buildDotFile(File outputFile)
    {
        try
        {
            FileWriter outputFileWriter = new FileWriter(outputFile);
            String head = "digraph G {\n";
            StringBuilder body = new StringBuilder();
            String tail = "}\n";

            generateDotBody(root, body);

            outputFileWriter.write(head + body.toString() + tail);
            outputFileWriter.flush();
            outputFileWriter.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Failed writing to file");
            ioe.printStackTrace();
        }
    }

    public BinaryNode getRoot()
    {
        return root;
    }

}
