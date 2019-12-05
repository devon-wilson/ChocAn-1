package com.DataBaseManager;

import com.DataClasses.*;
import com.ReadWrite.ReadWrite;

import java.util.TreeMap;

public class DataBaseManager<Object> {
    /* Populate tree with array of objects
    Takes a tree root and an array of objects to populate the tree.
    Returns the number of objects added to the tree.
     */

    protected TreeMap<String, Object> managers;
    protected TreeMap<String, Object> providers;
    protected TreeMap<String, Object> members;
    protected TreeMap<String, Object> services;

    public DataBaseManager() {
        this.managers = buildTree("data/managers.csv");
        this.providers = buildTree("data/providers.csv");
        this.members = buildTree("data/members.csv");
        this.services = buildTree("data/services.csv");
    }

    private TreeMap<String, Object> buildTree(String filename) {
        TreeMap<String, Object> root = new TreeMap<>();
        String[] fileData;

        if((fileData = ReadWrite.fileRead(filename)) == null)
            return null;

        // Determines what kind of objects will be created
        // First line of all .csv files
        String objectType = fileData[0];

        for (int i = 1; i < fileData.length; i++) {
            // Need to do some sort of RTTI depending on the filename
            String[] lineData = fileData[i].split(",");

            Object newObject = null;

            switch (objectType) {
                case "Manager":
                    newObject = (Object) new Manager(lineData);
                    break;
                case "Provider":
                    newObject = (Object) new Provider(lineData);
                    break;
                case "Member":
                    newObject = (Object) new Member(lineData);
                    break;
                case "Service":
                    newObject = (Object) new Service(lineData);
                    break;
            }

            if (newObject != null)
                root.put(lineData[1], newObject);
        }

        return root;
    }

    /* Return the queried item
    Takes the object database tree root and the object id as an argument.
    Returns a reference to the object if found, or null if the object
    is not in the tree.
     */
    public Object findData(int type, String key) {
        /*
        Each tree has it's own integer code
        0 - MANAGER
        1 - PROVIDER
        2 - MEMBER
        3 - SERVICE
         */
        switch(type) {

            case (0):
                return managers.get(key);
            case (1):
                return providers.get(key);
            case (2):
                return members.get(key);
            case (3):
                return services.get(key);
        }
        return null;
    }

    /* Remove queried item from tree
    Takes the object tree root and the id of the object to be removed.
    Returns the object associated with the id, or null if the object
    was not in the tree.
     */
    public Object removeTreeData(int type, String key){
        /*
        Each tree has it's own integer code
        0 - MANAGER
        1 - PROVIDER
        2 - MEMBER
        3 - SERVICE
         */
        switch(type) {

            case (0):
                return managers.remove(key);
            case (1):
                return providers.remove(key);
            case (2):
                return members.remove(key);
            case (3):
                return services.remove(key);
        }
        return null;
    }

    /* Add item to tree
    Takes the object tree root and the object to be added to the tree.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public Object addTreeData(int type, String key, Object obj){
        /*
        Each tree has it's own integer code
        0 - MANAGER
        1 - PROVIDER
        2 - MEMBER
        3 - SERVICE
         */
        switch(type) {

            case (0):
                return managers.put(key, obj);
            case (1):
                return providers.put(key, obj);
            case (2):
                return members.put(key, obj);
            case (3):
                return services.put(key, obj);
        }
        return null;
    }

    /* Update an item
    Takes the object tree root and the object to be updated.
    Returns a reference to the previous object associated with the id,
    or null if there was no object associated with the id.
     */
    public Object updateTreeData(int type, String key, Object obj){
        /*
        Each tree has it's own integer code
        0 - MANAGER
        1 - PROVIDER
        2 - MEMBER
        3 - SERVICE
         */
        switch(type) {

            case (0):
                return managers.replace(key, obj);
            case (1):
                return providers.replace(key, obj);
            case (2):
                return members.replace(key, obj);
            case (3):
                return services.replace(key, obj);
        }
        return null;
    }
}
