package cn.bigfire.crab.common.tool.upload;

import lombok.Data;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/17  9:03
 * @ Desc   ：
 */
@Data
public class UploadResult {
    String ossUrl;          //访问路径
    String path;            //存放地址
    String fileName;        //文件名
    String fileBaseName;    //文件名去掉后缀
    String fileSuffix;      //文件名后缀
    Long fileSize;          //文件名大小 KB
    String deleteKey;       //删除id
}
