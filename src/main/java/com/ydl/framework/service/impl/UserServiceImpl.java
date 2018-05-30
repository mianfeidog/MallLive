package com.ydl.framework.service.impl;

import com.ydl.framework.entity.User;
import com.ydl.framework.mapper.UserMapper;
import com.ydl.framework.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ydl
 * @since 2017-09-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
}
