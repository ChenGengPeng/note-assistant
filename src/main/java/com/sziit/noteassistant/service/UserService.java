package com.sziit.noteassistant.service;

import com.sziit.noteassistant.pojo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface UserService {
   public User add(User user);

   public User findById(Integer uid);

   public String getToken(User user);

   public User findByName(String username);

   public void changePassword(Integer id,String newPassword);

   public boolean comparePassword(String newPassword,String oldPassword);
}
