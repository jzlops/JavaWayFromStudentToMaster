package ru.stikhonov;

/**
 * Created by Sergey Tikhonov on 07.09.2016.
 */
public class ChangeState {
    public static void main(String[] args) {
        Claim claim = new Claim();
        claim.name = "bug";
        processClaim(claim);
        System.out.println(claim.name);
    }

    private static void processClaim(Claim value) {
        value.name = "task";
    }

    public static class Claim {
        public String name;


    }

}


