import java.util.Map;
import java.util.TreeMap;

public class DatabaseManager<E extends Object> {
    /* Populate tree with array of objects
    Takes a tree root and an array of objects to populate the tree.
    Returns the number of objects added to the tree.
     */
    public int buildTree(TreeMap<Integer, E> root, E[] obj) {
        int i;
        for(i = 0; i < obj.length; i++)
            root.put(obj[i].id, obj[i]);
        return i;
    }

    /* Return the queried item
    Takes the object database tree root and the object id as an argument.
    Returns a reference to the object if found, or null if the object
    is not in the tree.
     */
    public E findData(TreeMap<Integer, E> root, int id) {
        return root.get(id);
    }

    /* Remove queried item from tree
    Takes the object tree root and the id of the object to be removed.
    Returns the object associated with the id, or null if the object
    was not in the tree.
     */
    public E removeTreeData(TreeMap<Integer, E> root, int id){
        return root.remove(id);
    }

    /* Add item to tree
    Takes the object tree root and the object to be added to the tree.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public E addTreeData(TreeMap<Integer, E> root, E obj){
        return root.put(obj.id, obj);
    }

    /* Update an item
    Takes the object tree root and the object to be updated.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public E updateTreeData(TreeMap<Integer, E> root, E obj){
        return root.replace(obj.id, obj);
    }
}
