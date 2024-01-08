package com.study.common.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.study.attach.vo.AttachVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class StudyAttachUtils {

	/** 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴 */
	public List<AttachVO> getAttachListByMultiparts(MultipartFile[] multipartFiles, String category, String path)
			throws IOException {
		List<AttachVO> attachList=new ArrayList<>();
		for(MultipartFile multipartFile : multipartFiles){
			AttachVO attach=getAttachByMultipart(multipartFile,category,path);
			attachList.add(attach);
		}
		return  attachList;
	}

	/** MultipartFile에서 VO 설정 및 업로드 파일 처리 후 리턴, 없는 경우 null */
	public AttachVO getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			String fileName = UUID.randomUUID().toString();
			String filePath = "/home/ssam/upload/" + path;
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));

			AttachVO attach=new AttachVO();
			attach.setAtchCategory(category);
			attach.setAtchFileName(fileName);
			attach.setAtchOriginalName(multipart.getOriginalFilename());
			attach.setAtchFileSize(multipart.getSize());
			attach.setAtchFancySize( fancySize(multipart.getSize()) );
			attach.setAtchContentType(multipart.getContentType());
			attach.setAtchPath(filePath);
			return attach;
		} else {
			return null;
		}
	}

	private DecimalFormat df = new DecimalFormat("#,###.0");

	private String fancySize(long size) {
		if (size < 1024) { // 1k 미만
			return size + " Bytes";
		} else if (size < (1024 * 1024)) { // 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if (size < (1024 * 1024 * 1024)) { // 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}

}