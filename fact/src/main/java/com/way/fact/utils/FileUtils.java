package com.way.fact.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author yrz
 */
@Component
public class FileUtils {



    public String upload(MultipartFile file , HttpServletRequest request) throws IOException {

        //文件储存路径
        String uploadDir = request.getSession().getServletContext().getRealPath("/") + "images/upload/";

        File path = new File(uploadDir);
        if(!path.exists()){
            path.mkdirs();
        }

        //重置文件名
        String finType = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        UUID uid = UUID.randomUUID();
        String fileName = uid + finType;

        File saveFile = new File(uploadDir + fileName);
        file.transferTo(saveFile);
        return "/images/upload/" + fileName;
    }

}
