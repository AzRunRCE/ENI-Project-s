package fr.eni.ecole.androkado.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.androkado.bo.Article;

public class ArticleManager {


    /**
     *
     * @param id
     * @return
     */
    public static Article getArticle(int id){
        Article article = new Article();
        article.setId(id);
        article.setNom("Pain au chocolat");
        article.setDescription("Viennoiserie au beurre et au chocolat.");
        article.setUrl("http://www.painauchocolat.fr");
        article.setPrix(0.95F);
        article.setNote(2.5F);
        article.setAchete(true);
        article.setActive(false);

        return article;
    }

    public static List<Article> getArticles(){
        List<Article> articles = new ArrayList<>();

        articles.add(new Article(1,"Guitage","instrument de musique","http://www.guitare.fr",99.99f,4,false));
        articles.add(new Article(2,"Flûte","instrument de musique","http://www.flute.fr",89.99f,2.5F,false));
        articles.add(new Article(3,"Basse","instrument de musique","http://www.basse.fr",79.99f,3,false));
        articles.add(new Article(4,"Pain au chocolat","Viennoiserie au beurre et au chocolat.",
                                    "http://www.painauchocolat.fr",0.95F,2.5F,true));
        articles.add(new Article(5,"PS4","Console de jeux",
                                    "http://www.sony.com",399.95F,3.5F,false));

        articles.add(new Article(6,"Vélo","Vélo de randonnée",
                                        "https://cycles.peugeot.fr/",475,4,false));

        articles.add(new Article(7,"Voiture","Voiture hybride",
                                    "https://hybrides.fr/",25000,1.5F,false));

        articles.add(new Article(8,"Article 8","Description 8",
                                         "https://articles.fr/",25,2F,true));

        articles.add(new Article(9,"Article 9","Description 9",
                "https://articles.fr/",27,3F,false));

        articles.add(new Article(10,"Article 10","Description 10",
                "https://articles.fr/",21,2F,true));

        return articles;
    }

}
