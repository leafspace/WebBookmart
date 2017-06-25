package cn.cslg.WebBookmart.model;

import java.util.LinkedList;

public class ShowingBean {
    public static final int N = 8;
    public String classificationName;
    public LinkedList<Bookmart> bookmarts;
    public boolean isRemove;
    public int indexNow;
    public int classificationSize;

    public ShowingBean(Classification classification, int index, boolean isRemove){
        this.setShowing(classification,index,isRemove);
    }

    public boolean setShowing(Classification classification, int index, boolean isRemove){   //index[1,+00)
        this.classificationName = classification.getName();
        this.bookmarts = new LinkedList<Bookmart>();

        int classificationSize = classification.getSize();
        classificationSize = classificationSize / ShowingBean.N + ((classificationSize % ShowingBean.N > 0) ? 1 : 0);

        this.indexNow = index;
        this.classificationSize = classificationSize;

        if(index > classificationSize){
            return false;
        }else{
            for(int i = (index-1) * ShowingBean.N; i < Math.min(index * ShowingBean.N, classification.getSize()); ++i){
                this.bookmarts.add(classification.getBookmart(i));
            }
        }

        this.isRemove = isRemove;
        return true;
    }
}
