package com.zjty.productshow.service;

import com.zjty.productshow.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image uploadImage(MultipartFile file,Image image) throws IOException;

    List<Image> uploadImage(List<MultipartFile> file) throws IOException;

    Image delete(Integer imageId);



}
