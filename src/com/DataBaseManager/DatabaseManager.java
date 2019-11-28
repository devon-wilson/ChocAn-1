package com.DataBaseManager;

import java.util.TreeMap;

public class DatabaseManager<E> {
    /* Populate tree with array of objects
    Takes a tree root and an array of objects to populate the tree.
    Returns the number of objects added to the tree.
     */

    TreeMap<String, Object> providers;
    TreeMap<String, Object> members;
    TreeMap<String, Object> services;

    public DatabaseManager() {
        //providers = buildTree()
        //members = buildTree()
        //services = buildTree()
    }

    private TreeMap buildTree(String filename) {
        //TODO: make this function do things
        return null;
    }

    /* Return the queried item
    Takes the object database tree root and the object id as an argument.
    Returns a reference to the object if found, or null if the object
    is not in the tree.
     */
    public E findData(TreeMap<String, E> root, String key) {
        return root.get(key);
    }

    /* Remove queried item from tree
    Takes the object tree root and the id of the object to be removed.
    Returns the object associated with the id, or null if the object
    was not in the tree.
     */
    public E removeTreeData(TreeMap<String, E> root, String key){
        return root.remove(key);
    }

    /* Add item to tree
    Takes the object tree root and the object to be added to the tree.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public E addTreeData(TreeMap<String, E> root, String key, E obj){
        return root.put(key, obj);
    }

    /* Update an item
    Takes the object tree root and the object to be updated.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public E updateTreeData(TreeMap<String, E> root, String key, E obj){
        return root.replace(key, obj);
    }
}
