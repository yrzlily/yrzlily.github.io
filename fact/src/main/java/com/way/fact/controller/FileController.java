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

/**
 * 文件处理控制器
 * @author Administrator
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

}
