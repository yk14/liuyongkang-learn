package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.PictureResult;

public interface PictureService {
	public PictureResult uploadPic(MultipartFile picFile);
}
