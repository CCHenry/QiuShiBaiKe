/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.Bean.video;

import java.io.Serializable;

/**
 * Auto-generated: 2017-01-17 13:47:5
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Votes implements Serializable {

    private int down;

    private int up;

    public void setDown(int down){
        this.down = down;
    }
    public int getDown(){
        return this.down;
    }
    public void setUp(int up){
        this.up = up;
    }
    public int getUp(){
        return this.up;
    }


}