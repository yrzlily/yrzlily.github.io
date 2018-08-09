package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.utils.FileUtils;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件处理控制器
 * @author yrz
 */
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private FileUtils fileUtils;

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        String url = fileUtils.upload(file , request);
        return ResultUtils.success(url);
    }

    /**
     * 富文本上传图片
     */
    @PostMapping("/artUpload")
    public Result artUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        String url = fileUtils.upload(file , request);
        Map<String , Object> map = new HashMap<>(50);
        map.put("src" , url);
        return ResultUtils.success(map);
    }
}
