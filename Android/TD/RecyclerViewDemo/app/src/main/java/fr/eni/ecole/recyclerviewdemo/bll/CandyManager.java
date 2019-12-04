package fr.eni.ecole.recyclerviewdemo.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.recyclerviewdemo.bo.Candy;


public class CandyManager {


    public static List<Candy> getList(){
        List<Candy> lst = new ArrayList<>();

        lst.add(new Candy("Chamallow", "multicolor", 2.40F));
        lst.add(new Candy("Dragibus", "multicolor", 1.20F));
        lst.add(new Candy("Bêtise de Cambrai", "multicolor", 3.50F));
        lst.add(new Candy("Calisson", "Jaune et blanc", 5.50F));
        lst.add(new Candy("Macaron", "brun", 1.45F));
        lst.add(new Candy("Niniche de Quiberon", "brun", 1.60F));
        lst.add(new Candy("Vichy Pastilles", "blanc", 2.50F));
        lst.add(new Candy("Pez", "rose", 0.15F));
        lst.add(new Candy("Gummi bears", "multicolor", 1.75F));
        lst.add(new Candy("Fraise Tagada", "rose", 2.12F));

        return lst;
    }

}
