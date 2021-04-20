package com.zjty.productshow.service.impl;

import com.zjty.productshow.dao.ImageDao;
import com.zjty.productshow.entity.Image;
import com.zjty.productshow.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("/data/apps/")
    private String path;

/*
    @Value("/home/tykj/Desktop/icon/")
    private String path;
*/


    @Autowired
    private ImageDao imageDao;

    /**
     * 上传图片
     * @param file
     * @param image
     * @return
     * @throws IOException
     */
    @Override
    public Image uploadImage(MultipartFile file, Image image) throws IOException {
        if (!file.isEmpty()) {
            //获取文件全名（名称+后缀）
            String fileName = file.getOriginalFilename();
            //获取文件后缀
            String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            //获取文件名
            String fileName1 = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("\\") + 1);
            String fileNewName = fileName1.substring(0, fileName1.lastIndexOf("."));

            image.setImageName(fileName);
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;
            //指定本地存入路径

            file.transferTo(new File(path + fileName));
            image.setImageUrl(fileName);
        }
        return imageDao.save(image);
    }

    @Override
    public List<Image> uploadImage(List<MultipartFile> files) throws IOException {
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()){
                continue;
            }
            Image image = new Image();
            //获取文件全名（名称+后缀）
            String fileName = file.getOriginalFilename();
            //获取文件后缀
            String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            //获取文件名
            String fileName1 = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("\\") + 1);
            String fileNewName = fileName1.substring(0, fileName1.lastIndexOf("."));

            fileName = UUID.randomUUID() + suffixName;
            //指定本地存入路径

            file.transferTo(new File(path + fileName));
            image.setImageUrl(path+fileName);

            imageList.add(image);
            return imageList;

        }
        return null;
    }

    @Override
    public Image delete(Integer imageId) {
        Image image = imageDao.findById(imageId).get();
        if (image!=null ){
            return imageDao.save(image);
        }
        return null;
    }
/*
    *//**
     * 根据产品系列id和logo标识查询 该产品系列的图片
     * @param productSeriesId
     * @param isLogo
     * @return
     *//*
    @Override
    public List<Image> findAllByProductSeriesIdAndIsLogo(Integer productSeriesId,Integer isLogo) {
        List<Image> imageList = imageDao.findAllByProductSeriesIdAndIsLogo(productSeriesId, isLogo);
        if (imageList != null){
            return imageList;
        }
        return null;
    }*/
}
