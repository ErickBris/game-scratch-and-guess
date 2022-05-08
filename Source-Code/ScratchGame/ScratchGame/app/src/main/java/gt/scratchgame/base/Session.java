package gt.scratchgame.base;

import java.util.ArrayList;
import java.util.List;

import gt.scratchgame.bean.BeanEachQuetionScore;
import gt.scratchgame.bean.CategoryList;


public class Session {
    private static Session session;

    private Session() {
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    private CategoryList selectedCategory;

    public CategoryList getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryList selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    private List<BeanEachQuetionScore> scoreBeen = new ArrayList<>();

    public List<BeanEachQuetionScore> getScoreBeen() {
        return scoreBeen;
    }

    public void setScoreBeen(List<BeanEachQuetionScore> scoreBeen) {
        this.scoreBeen = scoreBeen;
    }

    private float totalScoreInSession;

    public float getTotalScoreInSession() {
        return totalScoreInSession;
    }

    public void setTotalScoreInSession(float totalScoreInSession) {
        this.totalScoreInSession = totalScoreInSession;
    }
}
