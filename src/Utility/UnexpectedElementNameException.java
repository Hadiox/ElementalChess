package Utility;

class UnexpectedElementNameException extends Exception {
    void exportError()
    {
        System.out.println("Wrong element name! Choose unit one more time.");
    }
}
