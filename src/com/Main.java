package com;

import com.DataBaseManager.DataBaseManager;
import com.DataClasses.Manager;
import com.DataClasses.Provider;

public class Main {

    public static void main (String[] args) {

        DataBaseManager DBM = new DataBaseManager();


        Manager retrieved = (Manager) DBM.findData(0,"012345678");
        retrieved.display();
    }
}
