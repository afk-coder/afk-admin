package net.fux.auth.service.impl;

import net.fux.auth.entity.Permission;
import net.fux.auth.service.PermissionService;
import net.fux.support.service.IBaseService;
import net.fux.support.vo.JsTreeJson;
import net.fux.support.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<Permission> getListByRoleId(Integer roleId) {
        String sql = "select p.* from sys_role_permission rp, sys_permission p where rp.role_id = ? ";
        return baseService.findBySql(sql, Permission.class, roleId);
    }

    @Override
    public List<JsTreeJson> list() {
        return findPermission("");
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return baseService.getBySql("select * from sys_permission where id = ? ", Permission.class, id);
    }

    @Override
    public ResultVo saveOrUpdate(Permission permission) {
        try {
            baseService.saveOrUpdate(permission);
            return ResultVo.successInfo("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("保存过程中出现异常！");
        }

    }

    @Override
    public ResultVo delete(Integer id) {
        try {
            baseService.delete(Permission.class, id);
            return ResultVo.successInfo("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("删除过程中出现异常!");
        }
    }

    private List<JsTreeJson> findPermission(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from sys_permission where 1 = 1 ");
        List<Object> params = new ArrayList<>();
        if(StringUtils.isBlank(id)) {
            sql.append("and parent_id is null ");
        } else {
            sql.append("and parent_id = ? ");
            params.add(id);
        }
        sql.append("order by sorting ");
        List<Permission> list = baseService.findVoSQL(sql.toString(), params, Permission.class);
        List<JsTreeJson> childres = new ArrayList<>();
        for (Permission permission : list) {
            JsTreeJson treeJson = new JsTreeJson();
            treeJson.setId(permission.getId().toString());
            treeJson.setText(permission.getName());
            treeJson.setChildren(findPermission(permission.getId().toString()));
            childres.add(treeJson);
        }
        return childres;
    }
}
