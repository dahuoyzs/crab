package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：${author}
* @ Date   ：${date}
* @ Desc   ：${table.comment!} 服务类
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
