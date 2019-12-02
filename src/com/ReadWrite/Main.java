package com.ReadWrite;

import java.io.IOException;

public class Main
{
    public Main(String[] args) throws IOException
    {
        String path = "C:\\Users\\Rubles\\IdeaProjects\\ChocAn\\";
        String[] data = {"3", "best", "'"};
        int i;

        ReadWrite.fileWrite(path + "test", data, true);
    }
}