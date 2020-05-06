package me.cerratolabs.portscanner.net.printer;

public class ConsolePrinter implements Printer {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printLastMessage(String message) {
        System.out.println(message);
    }

    /**
     * Print the help messages and close the application.
     */
    public void helpByParamsError() {
        System.err.println("The command syntax is wrong.");
        System.err.println("You can write the command in the following way:");
        System.err.println("java -jar portScanner-1.5.jar ipOrElseDomain minPortRange maxPortRange [file-to-save-open-ports.txt]");
        System.err.println("java -jar PortScanner-1.5.jar 127.0.0.1 0 65535 file.txt");
        System.err.println("or else");
        System.err.println("java -jar PortScanner-1.5.jar ipOrElseDomain [file-to-save-open-ports.txt]");
        System.err.println("java -jar PortScanner-1.5.jar cloudflare.com file.txt");
        System.err.println("The file is not obligatory option.");
        System.exit(1);
    }
}