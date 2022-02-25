package hellojpa;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
    public String getAritst() {
        return aritst;
    }

    public void setAritst(String aritst) {
        this.aritst = aritst;
    }

    private String aritst;
}
