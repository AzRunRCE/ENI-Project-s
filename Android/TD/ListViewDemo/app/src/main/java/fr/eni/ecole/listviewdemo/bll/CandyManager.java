package fr.eni.ecole.listviewdemo.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.listviewdemo.bo.Candy;

public class CandyManager {


    public static List<Candy> getList(){
        List<Candy> lst = new ArrayList<>();

        lst.add(new Candy("Chamallow", "multicolor", 2.40F));
        lst.add(new Candy("Dragibus", "multicolor", 1.20F));
        lst.add(new Candy("Carambar", "brun", 0.50F));

        return lst;
    }

}
