package fr.eni.ecole.androkado.bll;

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

}
