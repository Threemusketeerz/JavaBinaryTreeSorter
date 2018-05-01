public class BinaryNode <T extends Comparable<T>>
{
    //private BinaryNode parent;
    private T data;
    private BinaryNode leftChild;
    private BinaryNode rightChild;

    public BinaryNode()
    {
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BinaryNode(T data)
    {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BinaryNode(T data, BinaryNode leftChild, BinaryNode rightChild)
    {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public T getData()
    {
        return data;
    }

    public BinaryNode getLeftChild()
    {
        return leftChild;
    }

    public void setLeftChild(BinaryNode leftChild)
    {
        this.leftChild = leftChild;
    }

    public BinaryNode getRightChild()
    {
        return rightChild;
    }

    public void setRightChild(BinaryNode rightChild)
    {
        this.rightChild = rightChild;
    }
}

