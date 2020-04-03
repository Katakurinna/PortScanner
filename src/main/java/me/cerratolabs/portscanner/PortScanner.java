package me.cerratolabs.portscanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.stream.IntStream;

public class PortScanner {

    private static String ipAddress;
    private static int minPort = 0;
    private static int maxPort = 65536;

    private static BufferedWriter writer;
    private static boolean haveWriter = false;
    private static int openPortsCount = 0;

    public static void main(String[] args) throws IOException {
        // Check that you have entered the correct number of parameters
        if (args.length < 1 || args.length > 4) {
            helpByParamsError();
        }

        // Set the IP address.
        ipAddress = args[0];

        // Check if you introduce ports.
        if (args.length >= 3) {
            // Set the min and max ports
            try {
                minPort = Integer.parseInt(args[1]);
                maxPort = Integer.parseInt(args[2]) + 1; // Last port is not included.
            } catch (NumberFormatException nfe) {
                // If you dont introduce the port numbers correctly
                // print the help messages and finish the application
                helpByParamsError();
            }
        }

        // If you decide to save all in file
        if (args.length == 2 || args.length == 4) {
            writer = new BufferedWriter(new FileWriter(args[3]));
            haveWriter = true;
        }

        // Write start message
        String startMessage = "Starting port scouting to IP: " + ipAddress + ". First port: " + minPort + ", last port: " + (maxPort - 1) + ".";
        if (haveWriter) startMessage += " Saving in file: " + args[3];
        printMessage(startMessage);

        // Start port scouting in parallel form (efficient lambda).
        IntStream.range(minPort, maxPort).parallel().forEach(PortScanner::checkPort);

        // Write end message
        String endMessage = "Ended port scouting to IP " + ipAddress + ". " + openPortsCount + " ports opened.";
        if (haveWriter) endMessage += " Saved in file: " + args[3];
        printMessageWithoutNewLine(endMessage);

        // If you decide to save all in a file, it close the stream.
        if (haveWriter) {
            if (writer != null) writer.close();
        }
    }

    /**
     * Check if the port is open
     * If port is open, print a message.
     * else, dont do nothing.
     *
     * @param port port to scout.
     */
    public static void checkPort(int port) {
        if (isPortOpen(port)) printMessage("" + port);
    }

    /**
     * Check if the port is opened.
     * If the port is opened, increment by 1 the number of ports opened.
     *
     * @param port port to scout.
     * @return true if port is opened, false if not. Be careful, the fact
     * that you throw an exception is taken as the port is not open and
     * returns false, but it can also be the case that the IP address is wrong.
     */
    public static boolean isPortOpen(int port) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, port), 3 * 1000);
            socket.close();
            openPortsCount++;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Print a message in console, if you decide to save a file,
     * print the message in the file too and make new line.
     *
     * @param message message to print (and save).
     */
    public static void printMessage(String message) {
        printMessageWithoutNewLine(message);
        if (haveWriter) {
            try {
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print a message in console, if you decide to save a file,
     * print the message in the file too and dont make new line.
     *
     * @param message message to print (and save).
     */
    public static void printMessageWithoutNewLine(String message) {
        System.out.println(message);
        if (haveWriter) {
            try {
                writer.write(message);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print the help messages and close the application.
     */
    public static void helpByParamsError() {
        System.err.println("The command syntax is wrong.");
        System.err.println("You can write the command in the following way:");
        System.err.println("java -jar PortScanner-1.0.jar ipOrElseDomain minPortRange maxPortRange [file-to-save-open-ports.txt]");
        System.err.println("java -jar PortScanner-1.0.jar 127.0.0.1 0 65535 file.txt");
        System.err.println("or else");
        System.err.println("java -jar PortScanner-1.0.jar ipOrElseDomain [file-to-save-open-ports.txt]");
        System.err.println("java -jar PortScanner-1.0.jar cloudflare.com file.txt");
        System.err.println("The file is not obligatory option.");
        System.exit(1);
    }
}