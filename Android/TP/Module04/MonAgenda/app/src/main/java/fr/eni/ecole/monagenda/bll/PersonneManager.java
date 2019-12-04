package fr.eni.ecole.monagenda.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.monagenda.bo.Personne;

public class PersonneManager {

    public static List<Personne> getPersonnes(){

        List<Personne> p = new ArrayList<>();

        p.add(new Personne("Dupont", "Martin", "0244000000"));
        p.add(new Personne("Dupond", "Alain", "0244000010"));
        p.add(new Personne("Martin", "Bruno", "0244000100"));
        p.add(new Personne("Bouvet", "Laurent", "0244001000"));
        p.add(new Personne("Tran", "Jérôme", "0244010000"));
        p.add(new Personne("Hitch", "Annie", "0244100000"));
        p.add(new Personne("Grust", "Isabelle", "0244000001"));
        p.add(new Personne("Gravier", "Pierre", "0244000110"));
        p.add(new Personne("Richard", "Thierry", "0244011100"));
        p.add(new Personne("Sanchez", "Denis", "0244111000"));

        return p;

    }

}



