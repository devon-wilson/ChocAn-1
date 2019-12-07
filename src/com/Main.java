package com;

import com.Overlord.*;
import com.Terminal.*;

public class Main {

    public static void main (String[] args) {
        Overlord overlord = new Overlord();


        Terminal terminal = new Terminal(overlord);
        terminal.start();


    }
}
