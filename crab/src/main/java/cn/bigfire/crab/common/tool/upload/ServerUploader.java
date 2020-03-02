package cn.bigfire.crab.common.tool.upload;

import cn.bigfire.crab.common.config.CrabProperties;
import cn.bigfire.crab.common.exception.SysException;
import cn.bigfire.crab.common.util.Constant;
import cn.bigfire.crab.common.util.PathUtil;
import cn.bigfire.crab.common.util.UUIDUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/11  12:10
 * @ Desc   ：
 */
@Slf4j
@Component
public class ServerUploader implements UpLoader {

    @Autowired
    CrabProperties crabProperties;

    @Override
    public UploadResult upload(@NonNull MultipartFile file) {
        return upload(file,"upload/");
    }

    /**
     * 上传文件   Client->Server
     *
     * @param multipartFile         用户发送的MultipartFile对象
     * @param path       自定义文件存储位置
     * @return String               返回文件访问路径url
     * @throws IOException
     */
    @Override
    public UploadResult upload(@NonNull MultipartFile multipartFile, String path){

        String monthPath = PathUtil.toMonthPath(path);
        String fileName = multipartFile.getOriginalFilename();
        String saveName = UUIDUtil.uuid()+ "-" + fileName;
        String pathName = monthPath + saveName;
        String filePath = Constant.DIR + monthPath;
        String filePathName = Constant.DIR + pathName;

        try {
            FileUtil.mkdir(filePath);
            multipartFile.transferTo(new File(filePathName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new SysException("IOException"+e.getMessage());
        }

        String ossUrl = StrUtil.appendIfMissing(crabProperties.getBaseUrl(),"/") + pathName;
        UploadResult uploadResult = new UploadResult();
        uploadResult.setOssUrl(ossUrl);
        uploadResult.setPath(monthPath);
        uploadResult.setFileName(fileName);
        uploadResult.setFileBaseName(PathUtil.getFileBaseName(fileName));
        uploadResult.setFileSuffix(PathUtil.getFileSuffix(fileName));
        uploadResult.setFileSize(multipartFile.getSize());
        uploadResult.setDeleteKey(pathName);
        log.debug(uploadResult.toString());

        return uploadResult;
    }

    @Override
    public boolean delete(@NonNull String key) {
        String filePathName = Constant.DIR + key;
        return FileUtil.del(filePathName);
    }

}
