package com.start.mb.boot.server.impl;

import com.start.mb.boot.model.entity.PersistentLogins;
import com.start.mb.boot.persistence.mapper.PersistentLoginsMapper;
import com.start.mb.boot.server.PersistentLoginsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wll
 * @since 2020-04-09
 */
@Service
public class PersistentLoginsServiceImpl extends ServiceImpl<PersistentLoginsMapper, PersistentLogins> implements PersistentLoginsService {

}
