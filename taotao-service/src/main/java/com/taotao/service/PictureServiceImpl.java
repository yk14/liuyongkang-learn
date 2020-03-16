package com.taotao.service;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.PictureResult;

@Service
public class PictureServiceImpl implements PictureService{
	@Value("${urlPath}")
	String urlPic;
	public String getUrlPic() {
		return urlPic;
	}
	public void setUrlPic(String urlPic) {
		this.urlPic = urlPic;
	}
	@Override
	public PictureResult uploadPic(MultipartFile picFile) {
		//String urlPic="D:\\nginx-1.8.1\\html";
		System.out.println("urlPic++++++++"+urlPic);
				PictureResult result = null;
				if (picFile.isEmpty()) {
					result=PictureResult.error("图片为空");
					return result;
				}
				//取图片扩展名
				String originalFileName=picFile.getOriginalFilename();
				System.out.println("文件原始名:"+originalFileName);
				System.out.println(urlPic+File.separator+originalFileName);
				File file=new File(urlPic+File.separator+originalFileName);
				try {
						
						picFile.transferTo(file);
						result=PictureResult.ok("http://localhost:8000/"+originalFileName);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
				return result;
	}
	
}