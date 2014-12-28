package com.springapp.mvc;

/**
 * Created by SAURAV on 28-12-2014.
 */
import javax.persistence.*;
import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    String fName;
    String lName;

}
