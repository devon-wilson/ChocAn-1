package com;

import com.Overlord.*;
import com.Terminal.*;

public class Main {

    public static void main (String[] args) {
        Overlord overlord = new Overlord();
        overlord.genMemberReport("123456789");
        overlord.genProviderReport("123456789");

        /*
        Terminal terminal = new Terminal(overlord);
        terminal.start();

         */
    }
}
