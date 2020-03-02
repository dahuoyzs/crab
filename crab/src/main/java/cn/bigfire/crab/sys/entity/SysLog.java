package cn.bigfire.crab.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-10
* @ Desc   ：
*/
@Data
@Accessors(chain = true)
@ApiModel(value="SysLog对象", description="")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "请求地址URL")
    private String url;

    @ApiModelProperty(value = "公网IP地址")
    private String ip;

    @ApiModelProperty(value = "调用的方法")
    private String method;

    @ApiModelProperty(value = "接口请求输入的参数")
    private String params;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "此次请求总耗时")
    private Long useTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @TableLogic
    @ApiModelProperty(value = "是否删除,0为正常1为删除")
    private Integer deleted;
}
