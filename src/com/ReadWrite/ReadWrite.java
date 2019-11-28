package com.ReadWrite;

import java.io.*;
import java.util.ArrayList;

public class ReadWrite
{

    public String[] fileRead(String filename)
    {
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            ArrayList<String> input = new ArrayList<>();
            String[] data;
            String line;

            while ((line = file.readLine()) != null) {

                input.add(line);
            }
            file.close();

            int size = input.size();
            data = new String[size];
            for (int i = 0; i < size; ++i)
                data[i] = input.get(i);

            return data;
        }
        catch (IOException a) {
            System.out.println("Could not open " + filename);
            return null;
        }
    }

    static String[][] fileReadAll(String filename) throws IOException
    {
        String[][] ret = {{"Placeholder"}, {"Placeholder"}};
        return ret;
    }

    static int fileWrite(String filename, String[] data, boolean append)
    {
        if(data[0] == null)
            return -1;

        try {
            FileWriter file = new FileWriter(filename + ".txt", append);
            int i;
            for (i = 0; i < data.length - 1; ++i) {
                file.write(data[i]);
                file.write(',');
            }
            file.write(data[i]);
            file.write('\n');

            file.close();
            return 1;
        }
        catch (IOException a) {
            System.out.println("Could not open " + filename);
            return -1;
        }
    }
}
