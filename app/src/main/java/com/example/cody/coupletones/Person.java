package com.example.cody.coupletones;

import android.location.Location;

/**
 * Created by Cody on 4/26/16.
 */
import java.util.ArrayList;

public class Person {

    String name;
    ArrayList<Location> favorites;
    ArrayList<Location> visited;

    public Person(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Location> getFavorites()
    {
        return favorites;
    }

    public ArrayList<Location> getVisited()
    {
        return visited;
    }

    public void addFavorite(Location toAdd)
    {
        favorites.add(toAdd);
    }

    public void addVisited(Location toAdd)
    {
        visited.add(toAdd);
    }
}
