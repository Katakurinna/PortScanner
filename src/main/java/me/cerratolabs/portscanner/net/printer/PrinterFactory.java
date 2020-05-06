package me.cerratolabs.portscanner.net.printer;

import java.io.File;
import java.io.IOException;

public class PrinterFactory {

    public static Printer getPrinter(String filepath) throws IOException {
        if (filepath == null) return new ConsolePrinter();
        return new CommonPrinter(filepath);
    }
}