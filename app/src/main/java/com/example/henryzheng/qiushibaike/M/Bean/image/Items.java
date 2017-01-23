/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.image;

import java.io.Serializable;

/**
 * Auto-generated: 2017-01-18 15:28:26
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Items implements Serializable{

    private String format;
    private String image;
    private String high_loc;
    private int published_at;
    private String tag;
    private User user;
    private ImageSize image_size;
    private String id;
    private String low_loc;
    private HotComment hot_comment;
    private Votes votes;
    private int created_at;
    private String content;
    private String state;
    private String comments_count;
    private boolean allow_comment;
    private String share_count;
    private String type;
    public void setFormat(String format) {
         this.format = format;
     }
     public String getFormat() {
         return format;
     }

    public void setImage(String image) {
         this.image = image;
     }
     public String getImage() {
         return image;
     }

    public void setHighLoc(String highLoc) {
         this.high_loc = highLoc;
     }
     public String getHighLoc() {
         return high_loc;
     }

    public void setPublishedAt(int publishedAt) {
         this.published_at = publishedAt;
     }
     public int getPublishedAt() {
         return published_at;
     }

    public void setTag(String tag) {
         this.tag = tag;
     }
     public String getTag() {
         return tag;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public void setImageSize(ImageSize imageSize) {
         this.image_size = imageSize;
     }
     public ImageSize getImageSize() {
         return image_size;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setLowLoc(String lowLoc) {
         this.low_loc = lowLoc;
     }
     public String getLowLoc() {
         return low_loc;
     }

    public void setHotComment(HotComment hotComment) {
         this.hot_comment = hotComment;
     }
     public HotComment getHotComment() {
         return hot_comment;
     }

    public void setVotes(Votes votes) {
         this.votes = votes;
     }
     public Votes getVotes() {
         return votes;
     }

    public void setCreatedAt(int createdAt) {
         this.created_at = createdAt;
     }
     public int getCreatedAt() {
         return created_at;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setState(String state) {
         this.state = state;
     }
     public String getState() {
         return state;
     }

    public void setCommentsCount(String commentsCount) {
         this.comments_count = commentsCount;
     }
     public String getCommentsCount() {
         return comments_count;
     }

    public void setAllowComment(boolean allowComment) {
         this.allow_comment = allowComment;
     }
     public boolean getAllowComment() {
         return allow_comment;
     }

    public void setShareCount(String shareCount) {
         this.share_count = shareCount;
     }
     public String getShareCount() {
         return share_count;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

}