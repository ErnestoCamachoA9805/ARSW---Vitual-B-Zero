package edu.escuelaing.VirtualBLab.Exeptions;

public class VLabExeptions extends Exception{
    public static final String userNameTakenException= "THE USERNAME YOU CHOOSED IS TAKEN";

    public VLabExeptions(String message){
        super(message);
    }
}
