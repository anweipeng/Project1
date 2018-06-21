package com.chinaztt.fda.bean;
public class ItemBean {
    public int itemImage;
    public String itemTitle;
    public String itemContent;

    public ItemBean(int itemImage , String itemTitle, String itemContent) {
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
        this.itemImage = itemImage;
    }
}
