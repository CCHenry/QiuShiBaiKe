/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.Bean.infoComment;
/**
 * Auto-generated: 2017-01-20 11:17:40
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Items {

    private String content;
    private int parent_id;
    private boolean liked;
    private int like_count;
    private String at_infos;
    private int floor;
    private int created_at;
    private int id;
    private User user;
    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setParentId(int parentId) {
         this.parent_id = parentId;
     }
     public int getParentId() {
         return parent_id;
     }

    public void setLiked(boolean liked) {
         this.liked = liked;
     }
     public boolean getLiked() {
         return liked;
     }

    public void setLikeCount(int likeCount) {
         this.like_count = likeCount;
     }
     public int getLikeCount() {
         return like_count;
     }

    public void setAtInfos(String atInfos) {
         this.at_infos = atInfos;
     }
     public String getAtInfos() {
         return at_infos;
     }

    public void setFloor(int floor) {
         this.floor = floor;
     }
     public int getFloor() {
         return floor;
     }

    public void setCreatedAt(int createdAt) {
         this.created_at = createdAt;
     }
     public int getCreatedAt() {
         return created_at;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

}