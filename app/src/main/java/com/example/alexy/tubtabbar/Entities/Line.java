package com.example.alexy.tubtabbar.Entities;
import com.example.alexy.tubtabbar.Repositories.TubDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by iem on 18/10/2016.
 */

@Table(database = TubDatabase.class)
public class Line extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    public Line(){

    }

    public Line(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
