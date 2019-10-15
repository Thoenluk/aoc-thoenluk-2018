package adventofcode2018;

import java.util.List;

/**
 *
 * @author Lukas Thöni
 */
public class Node implements Comparable {

    private static int highestID = 1;

    private int parentNodeID;
    private int ID;
    private int[] metadata;
    private int[] childrenIDs;

    public Node() {
        super();
    }

    public Node(int parentNodeID, int ID, int[] metadata, int[] childrenIDs) {
        this.parentNodeID = parentNodeID;
        this.ID = ID;
        this.metadata = metadata;
        this.childrenIDs = childrenIDs;
    }

    public int getParentNodeID() {
        return parentNodeID;
    }

    public int getID() {
        return ID;
    }

    public int[] getMetadata() {
        return metadata;
    }

    public int[] getChildrenIDs() {
        return childrenIDs;
    }

    /**
     * Create a new Node as specified in specs starting at index readPosition
     * (inclusive) and add it to List tree. Note that this method recursively
     * adds child Nodes and does so in no particular order. Sorting the tree
     * afterwards may be wise.
     *
     * @param specs
     * @param readPosition
     * @param tree
     * @return The index of the element in specs just after this node concludes.
     */
    public static int hangNodesFromSpecs(int[] specs, int readPosition, int parentNodeID, List<Node> tree) {
        int i = 0;
        Node newNode = new Node();
        newNode.ID = highestID;
        highestID++;
        newNode.childrenIDs = new int[specs[readPosition]];
        readPosition++;
        newNode.metadata = new int[specs[readPosition]];
        readPosition++;
        for (i = 0; i < newNode.childrenIDs.length; i++) {
            newNode.childrenIDs[i] = highestID; //Remember this will be modified inside the recursive call.
            //When this instruction is reached, the ID will be different, but always equal to the
            //ID the new Node is about to take.
            readPosition = hangNodesFromSpecs(specs, readPosition, newNode.ID, tree);
        }
        for (i = 0; i < newNode.metadata.length; i++) {
            newNode.metadata[i] = specs[readPosition];
            readPosition++;
        }
        tree.add(newNode);
        return readPosition;
    }

    public int getValue(List<Node> tree) {
        int value = 0, i = 0;
        if (childrenIDs.length == 0) {
            for (i = 0; i < metadata.length; i++) {
                value += metadata[i];
            }
        } else {
            for (i = 0; i < metadata.length; i++) {
                if (metadata[i] != 0 && (metadata[i] - 1) < childrenIDs.length) {
                    //metadata entry must be reduced by 1, as it is 1-indexed and
                    //childrenIDs is 0-indexed (as it should be.)
                    value += tree.get(childrenIDs[metadata[i] - 1] - 1).getValue(tree);
                }
            }
        }
        return value;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Node)) {
            throw new UnsupportedOperationException("Incomparable object!");
        }
        return this.ID - ((Node) o).ID;
    }

}
