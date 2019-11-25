import java.io.IOException;

public class main
{
    public static void main(String[] args) throws IOException
    {
        String path = "C:\\Users\\Rubles\\IdeaProjects\\ChocAn\\";
        String[] data = {"help", "me", "out"};
        readwrite.fileWrite(path + "test2.txt", data, true);
    }
}