import java.io.IOException;

public class main
{
    public static void main (String[] args) throws IOException
    {
        String path = "C:\\Users\\Rubles\\IdeaProjects\\ChocAn\\";
        String[] data = {"3", "best", "'"};
        int i;

        readwrite.fileWrite(path + "test", data, true);
    }
}