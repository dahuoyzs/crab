package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：${author}
* @ Date   ：${date}
* @ Desc   ：${table.comment!} Mapper 接口
*/
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
