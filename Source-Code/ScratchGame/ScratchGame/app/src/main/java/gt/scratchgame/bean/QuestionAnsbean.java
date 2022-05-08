package gt.scratchgame.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GT-5 on 12/13/2014.
 */
public class QuestionAnsbean {

    public int id;
    public int cat_id;
    public String url;
    public List<AnsOptionBean> ansOptionList = new ArrayList<>();

    public void release() {

    }
}
