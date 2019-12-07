package com.DataBaseManager;

import com.DataClasses.*;
import com.ReadWrite.ReadWrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

public class DataBaseManager<Object> {
    /* Populate tree with array of objects
    Takes a tree root and an array of objects to populate the tree.
    Returns the number of objects added to the tree.
     */

    private TreeMap<String, Object> managers;
    private TreeMap<String, Object> providers;
    private TreeMap<String, Object> members;
    private TreeMap<String, Object> services;
    private TreeMap<String, ArrayList> memberRecords;
    private TreeMap<String, ArrayList> providerRecords;

    public DataBaseManager() {
        this.managers = buildTree("data/users/managers.csv");
        this.providers = buildTree("data/users/providers.csv");
        this.members = buildTree("data/users/members.csv");
        this.services = buildTree("data/providerDirectory.csv");
        this.memberRecords = buildRecordTree(3, "data/records/records.csv");
        this.providerRecords = buildRecordTree(2, "data/records/records.csv");
    }

    private TreeMap<String, ArrayList> buildRecordTree(int type, String filename) {
        TreeMap<String, ArrayList> root = new TreeMap<>();
        String[] fileData;

        if (type > 3 || type < 2)
            return null;

        if((fileData = ReadWrite.fileRead(filename)) == null)
            return null;

        for (int i = 1; i < fileData.length; i++) {
            String[] lineData = fileData[i].split(",");

            /* Use arrayLists of records
            store record arrayLists organized by provider ID # as a key.
             */
            ArrayList recordList;
            Record newObject = new Record(lineData);

            recordList = root.get(lineData[type]);
            if(recordList == null){
                recordList = new ArrayList<Record>();
                recordList.add(newObject);
                root.put(lineData[type], recordList);
            }
            else
                recordList.add(newObject);
        }
        return root;
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
        4 - MEMBER RECORD
        5 - PROVIDER RECORD
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
                return (Object) memberRecords.get(key);
            case (5):
                return (Object) providerRecords.get(key);
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
        4 - MEMBER RECORD
        5 - PROVIDER RECORD
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
                return (Object) memberRecords.remove(key);
            case (5):
                return (Object) providerRecords.remove(key);
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
        4 - MEMBER RECORD
        5 - PROVIDER RECORD
         */
        try {
            switch (type) {

                case (0):
                    return managers.put(key, obj);
                case (1):
                    return providers.put(key, obj);
                case (2):
                    return members.put(key, obj);
                case (3):
                    return services.put(key, obj);
                case (4):
                    ArrayList<Record> memRecords= memberRecords.get(key);
                    if(memRecords == null){
                        memRecords = new ArrayList<Record>();
                        memRecords.add((Record) obj);
                        memberRecords.put(key, memRecords);
                    }else {
                        memRecords.add((Record) obj);
                    }
                    return null;
                case (5):
                    ArrayList<Record> provRecords= providerRecords.get(key);
                    if(provRecords == null){
                        provRecords = new ArrayList<Record>();
                        provRecords.add((Record) obj);
                        providerRecords.put(key, provRecords);
                    }else {
                        provRecords.add((Record) obj);
                    }
                    return null;
            }
            return null;
        }
        catch (ClassCastException a) {
            return null;
        }
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
        4 - MEMBER RECORD
        5 - PROVIDER RECORD
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
                return (Object) memberRecords.replace(key, (ArrayList) obj);
            case (5):
                return (Object) providerRecords.replace(key, (ArrayList) obj);
        }
        return null;
    }

    protected ArrayList getAll(int type) {
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
            4 - MEMBER RECORD
            5 - PROVIDER RECORD
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
                    return (ArrayList) memberRecords.values();
                case (5):
                    return (ArrayList) providerRecords.values();
            }
            if (values == null)
                return null;

            return new ArrayList<>(values);
        }
        catch (ClassCastException a) {
            return null;
        }

    }

    protected ArrayList<String> treeToStringArray(int type) {
        /*
        Each tree has it's own integer code
        0 - MANAGER
        1 - PROVIDER
        2 - MEMBER
        3 - SERVICE
         */

        ArrayList<String> list = new ArrayList<String>();

        // Determines first line of file
        String[] firstLine = {"Manager", "Provider", "Member", "Directory"};
        list.add(firstLine[type]);

        // Get data from all objects in tree
        ArrayList allData = getAll(type);
        for (java.lang.Object i : allData) {

            String[] line = null;

            switch (type) {
                case (0):
                    Manager manager = (Manager) i;
                    line = manager.getAll();
                    break;
                case (1):
                    Provider provider = (Provider) i;
                    line = provider.toStringArray();
                    break;
                case (2):
                    Member member = (Member) i;
                    line = member.toStringArray();
                    break;
                case (3):
                    Service service = (Service) i;
                    line = service.getAll();
                    break;
            }
            String lineData = String.join(",", line);
            list.add(lineData);
        }

        return list;
    }
}