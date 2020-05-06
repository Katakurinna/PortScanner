package me.cerratolabs.portscanner.net.printer;

public interface Printer {
    /**
     * Print a message new message.
     *
     * @param message message to print (and save).
     */
    void printMessage(String message);

    /**
     * Print a message without new line separator.
     *
     * @param message message to print (and save)
     */
    void printLastMessage(String message);

    void helpByParamsError();
}