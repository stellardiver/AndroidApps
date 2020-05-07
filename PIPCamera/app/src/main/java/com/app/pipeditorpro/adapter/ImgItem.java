package com.app.pipeditorpro.adapter;

import java.io.Serializable;

public class ImgItem implements Serializable {

	private static final long serialVersionUID = 1L;
	String ab_txt_img;

	public ImgItem() {
    }
	
	public ImgItem(String ab_txt_img) {
        this.ab_txt_img = ab_txt_img; 
    }

	public String getAb_txt_img() {
		return ab_txt_img;
	}

	public void setAb_txt_img(String ab_txt_img) {
		this.ab_txt_img = ab_txt_img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
