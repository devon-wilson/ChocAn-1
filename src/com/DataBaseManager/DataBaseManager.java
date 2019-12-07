package com.DataBaseManager;

import com.DataClasses.*;
import com.ReadWrite.ReadWrite;

import java.util.ArrayList;
import java.util.Collection;
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
    protected TreeMap<String, Object> recordsList;

    public DataBaseManager() {
        this.managers = buildTree("data/managers.csv");
        this.providers = buildTree("data/providers.csv");
        this.members = buildTree("data/members.csv");
        this.services = buildTree("data/providerDirectory.csv");
        this.recordsList = buildTree("data/records.csv");
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
            /* Use arrayLists of records
            store record arrayLists organized by provider ID # as a key.
             */
            ArrayList<Record> recordList;

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
                case "Directory":
                    newObject = (Object) new Service(lineData);
                    break;
                case "Record":
                    newObject = (Object) new Record(lineData);
            }

            if (newObject != null) {
                /* Add records based on 5th element (service #).
                Add all other  objects based on 2nd element (ID #).
                 */
                if (objectType.equals("Record")) {
                    recordList = (ArrayList<Record>) root.get(lineData[2]);
                    if(recordList == null){
                        recordList = new ArrayList<Record>();
                        recordList.add((Record) newObject);
                        root.put(lineData[2], (Object) recordList);
                    }
                    else
                        recordList.add((Record) newObject);
                }
                else {
                    root.put(lineData[1], newObject);
                }
            }
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
        4 - RECORD
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
            case (4):
                return recordsList.get(key);
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
        4 - RECORD
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
            case (4):
                return recordsList.remove(key);
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
        4 - RECORD
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
            case (4):
                return recordsList.put(key, obj);
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
        4 - RECORD
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
            case (4):
                return recordsList.replace(key, obj);
        }
        return null;
    }

    protected ArrayList<Object> getAll(int type) {
        if (type < 0 || type > 4)
            return null;

        Collection<Object> values = null;

        try {
            /*
            Each tree has it's own integer code
            0 - MANAGER
            1 - PROVIDER
            2 - MEMBER
            3 - SERVICE
            4 - RECORD
             */
            switch(type) {

                case (0):
                    values = managers.values();
                    break;
                case (1):
                    values = providers.values();
                    break;
                case (2):
                    values = members.values();
                    break;
                case (3):
                    values = services.values();
                    break;
                case (4):
                    values = recordsList.values();
                    break;
            }
            if (values == null)
                return null;

            return new ArrayList<>(values);
        }
        catch (ClassCastException a) {
            return null;
        }

    }
}