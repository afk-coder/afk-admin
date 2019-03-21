package net.fux.auth.service.impl;

import net.fux.auth.service.IndexService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/13
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<Map<String, Object>> findPermissionByUserId(Integer userId) {
        String sql = "select * from sys_permission where is_menu = 1 ";
        return baseService.queryForList(sql);
    }
}
