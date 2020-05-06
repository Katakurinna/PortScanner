package me.cerratolabs.portscanner.net.printer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonPrinter extends ConsolePrinter {

    private File file;
    private static BufferedWriter writer;

    public CommonPrinter(String filePath) throws IOException {
        file = new File(filePath);
        writer = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void printMessage(String message) {
        super.printMessage(message);
        try {
            writer.write(message);
            writer.flush();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printLastMessage(String message) {
        super.printMessage(message);
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}