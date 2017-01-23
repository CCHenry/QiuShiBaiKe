/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.video;

import java.io.Serializable;

/**
 * Auto-generated: 2017-01-17 13:47:5
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Votes implements Serializable {

    private String down;

    private String up;

    public void setDown(String down){
        this.down = down;
    }
    public String getDown(){
        return this.down;
    }
    public void setUp(String up){
        this.up = up;
    }
    public String getUp(){
        return this.up;
    }


}