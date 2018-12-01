package Utility;

public class UnexpectedElementNameException extends Exception {
    public void exportError()
    {
        System.out.println("Wrong element name! Choose unit one more time.");
    }
}
