package com.ReadWrite;

import java.io.*;
import java.util.ArrayList;

public class ReadWrite
{
    public static String[] fileRead(String filename)
    {
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            ArrayList<String> input = new ArrayList<>();
            String line;

            while ((line = file.readLine()) != null) {
                input.add(line);
            }
            file.close();

            int size = input.size();
            String[] data = new String[size];

            for (int i = 0; i < size; ++i)
                data[i] = input.get(i);

            return data;
        }
        catch (IOException a) {
            System.out.println("Could not open " + filename);
            return null;
        }
    }

    public static int fileWrite(String filename, String data, boolean append) throws IOException
    {
        FileWriter file;
        int i;

        if(data== null)
            return -1;

        file = new FileWriter(filename, append);
        file.write(data);
        file.write('\n');
        file.close();
        return 1;
    }

    public static int fileWrite(String filename, String[] data, boolean append) throws IOException
    {
        FileWriter file;
        int i;

        if (data == null)
            return -1;

        file = new FileWriter(filename, append);
        for(i = 0; i < data.length - 1; ++i)
        {
            file.write(data[i]);
            if(i != data.length - 1)
                file.write(',');
        }
        file.write(data[i]);
        file.write('\n');

        file.close();
        return 1;
    }
}