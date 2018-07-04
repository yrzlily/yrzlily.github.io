package com.way.fact.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Administrator
 */
@Component
public class FileUtils {



    public String upload(MultipartFile file , HttpServletRequest request) throws IOException {

        String finType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        UUID uid = UUID.randomUUID();

        File path = new File(request.getSession().getServletContext().getRealPath("/images/upload/") + uid + finType);
        if(!path.getParentFile().exists()){
            path.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path);
        out.write(file.getBytes());
        out.flush();
        out.close();

        return "/images/upload/" + path.getName();
    }

}
