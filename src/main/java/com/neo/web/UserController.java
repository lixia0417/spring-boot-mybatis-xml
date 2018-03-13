package com.neo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.UserEntity;
import com.neo.mapper.UserMapper;
import com.neo.route.RoutingDataSourceContext;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		String key = "slaveDataSource";
		RoutingDataSourceContext ctx = new RoutingDataSourceContext(key);
		List<UserEntity> users = userMapper.getAll();
		return users;
	}
	
	@RequestMapping("/getMaxId")
	public Integer getMaxId() {
//		String key = "masterDataSource";
		String key = "slaveDataSource";
		RoutingDataSourceContext ctx = new RoutingDataSourceContext(key);
		Integer maxId =  userMapper.getMaxId();
		return maxId;
	}

	@RequestMapping("/getUser")
	public UserEntity getUser(Long id) {
		String key = "masterDataSource";
		RoutingDataSourceContext ctx = new RoutingDataSourceContext(key);
		UserEntity user = userMapper.getOne(id);
		return user;
	}

	@RequestMapping("/add")
	public void save(UserEntity user) {
		userMapper.insert(user);
	}

	@RequestMapping(value = "update")
	public void update(UserEntity user) {
		userMapper.update(user);
	}

	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		userMapper.delete(id);
	}

}