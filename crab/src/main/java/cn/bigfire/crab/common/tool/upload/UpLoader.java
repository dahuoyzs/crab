package cn.bigfire.crab.common.tool.upload;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  14:37
 * @ Desc   ：文件上传器
 */
public interface UpLoader{
    /**
     * 文件上传
     *
     * @param multipartFile         上传的文件
     * @return String               返回文件上传后的可访问路径
     * */
    @NonNull
    UploadResult upload(@NonNull MultipartFile multipartFile);
    /**
     * 文件上传
     *
     * @param multipartFile         上传的文件
     * @param fileStoragePath       自定义存储路径
     * @return String               返回文件上传后的可访问路径
     * */
    @NonNull
    UploadResult upload(@NonNull MultipartFile multipartFile, String fileStoragePath);

    /**
     * 删除文件.
     *
     * @param key           文件路径（包含文件名）
     */
    boolean delete(@NonNull String key);

}
