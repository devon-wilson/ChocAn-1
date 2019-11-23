import java.io.*;
import java.util.ArrayList;

public class readwrite
{
    static String[] fileRead(String filename) throws IOException {
        FileReader file = new FileReader(filename);
        ArrayList<String> input = new ArrayList<String>();
        String[] data;
        int i = 0;
        int c;

        input.add("");
        while((c = file.read()) != -1)
        {
            if (c == ',') {
                input.add("");
                ++i;
            } else
                input.set(i, input.get(i) + (char) c);
        }
        file.close();

        c = input.size();
        data = new String[c];
        for(i = 0; i < c; ++i)
            data[i] = input.get(i);

        return data;
    }

    static int fileWrite(String filename, String[] data, boolean append) throws IOException
    {
        FileWriter file;
        int i;

        if(data[0] == null)
            return -1;

        file = new FileWriter(filename + ".txt", append);
        for(i = 0; i < data.length; ++i)
        {
            file.write(data[i]);
            file.write(',');
        }
        file.write('\n');

        file.close();
        return 1;
    }
}
