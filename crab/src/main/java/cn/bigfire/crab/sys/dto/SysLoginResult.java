package cn.bigfire.crab.sys.dto;

import cn.bigfire.crab.sys.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/8  15:51
 * @ Desc   ：
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SysLoginResult对象", description="")
public class SysLoginResult {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    private String avatar;

    @ApiModelProperty(value = "Token")
    @TableField(exist = false)
    private String token;
}
