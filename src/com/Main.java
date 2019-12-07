package com;

import com.Overlord.*;
import com.Terminal.*;

public class Main {

    public static void main (String[] args) {
        Overlord overlord = new Overlord();
        /*
        overlord.viewDirectory("123456789");
        overlord.viewMembers();
        overlord.viewProviders();
        overlord.viewServices();
        */



        Terminal terminal = new Terminal(overlord);
        terminal.start();
    }
}
