package cn.bigfire.crab.sys.entity;

import cn.bigfire.crab.common.enums.MenuType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="SysMenu对象", description="")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "菜单类型:0目录,1菜单,2按钮,3外链")
    private MenuType menuType;

    @ApiModelProperty(value = "网页标题")
    private String title;

    @ApiModelProperty(value = "icon图标地址")
    private String icon;

    @ApiModelProperty(value = "外部链接，预跳转的页面")
    private String url;

    @ApiModelProperty(value = "授权唯一值")
    private String perms;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @TableLogic
    @ApiModelProperty(value = "是否删除,0为正常1为删除")
    private Integer deleted;
}
