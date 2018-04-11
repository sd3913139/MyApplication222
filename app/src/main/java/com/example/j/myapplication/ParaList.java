package com.example.j.myapplication;

/**
 * Created by j on 2018/1/23.
 */

public class ParaList {

    private String id_a;
    private String paraValueA;
    private String id_b;
    private String paraValueB;



    public  ParaList(String id, String goodsName, String codeBar, String num) {

        this.id_a = id;
        this.paraValueA = goodsName;
        this.id_b = codeBar;
        this.paraValueB = num;
   }

    public String getId_A() {
        return id_a;
    }

    public void setId_A(String id) {
        this.id_a = id;
    }

    public String getparaValueA() {
        return paraValueA;
    }

    public void setparaValueA(String goodsName) {
        this.paraValueA = goodsName;
    }

    public String getId_B() {
        return id_b;
    }

    public void setId_B(String id) {
        this.id_b = id;
    }

    public String getparaValueB() {
        return paraValueB;
    }

    public void setparaValueB(String goodsName) {
        this.paraValueB = goodsName;
    }
}
