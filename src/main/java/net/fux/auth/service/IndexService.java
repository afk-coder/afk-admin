package net.fux.auth.service;

import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/13
 */
public interface IndexService {

    List<Map<String, Object>> findPermissionByUserId(Integer userId);
}
