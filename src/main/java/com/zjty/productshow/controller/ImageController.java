package com.zjty.productshow.controller;

import com.sun.istack.NotNull;
import com.zjty.productshow.entity.Image;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/image")
@Api(value = "图片接口",description = "图片的上传 下载")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @ApiOperation("上传产品照片")
    @PostMapping("/upload")
    public ServerResponse uploadImage(@NotNull @RequestParam("file") List<MultipartFile>  files) throws IOException {
        /*return ServerResponse.ok(imageService.uploadImage(file, image));*/
        List<Image> imageList = imageService.uploadImage(files);
        return ServerResponse.ok(imageList);
    }

    @ApiOperation("删除产品图片")
    @DeleteMapping("/delete/{imageId}")
    public ServerResponse uploadImage(@NotNull @PathVariable Integer imageId) throws IOException {
        return ServerResponse.ok(imageService.delete(imageId));
    }


}
