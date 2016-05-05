package com.example.cody.coupletones;

/**
 * Created by Cody on 4/28/16.
 */
public class Pair {

    Person user;
    Person partner;
    int days;

    public Pair(Person user, Person partner)
    {
        this.user = user;
        this.partner = partner;
        days = 0;
    }

     public int getDays()
    {
        return days;
    }

}
