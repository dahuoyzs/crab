package cn.bigfire.crab.common.tool.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/9  13:03
 * @ Desc   ：
 */
@Slf4j
@Component
public class UpYunUploader implements UpLoader{

    @Override
    public UploadResult upload(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public UploadResult upload(MultipartFile multipartFile, String fileStoragePath) {
        return null;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
