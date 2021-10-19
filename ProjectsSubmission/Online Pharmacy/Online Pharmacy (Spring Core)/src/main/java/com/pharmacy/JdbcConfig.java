package com.pharmacy;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.pharmacy.dao.DistributorItemDAO;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.OrdersDAO;
import com.pharmacy.dao.ParticularProductDAO;
import com.pharmacy.dao.UserDAO;

@Configuration
@ComponentScan(basePackages = {"com.pharmacy.dao"})
public class JdbcConfig {
	@Bean("ds")
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/pharmacy");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	
	@Bean(name = {"jdbcTemplate"})
	public JdbcTemplate getTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}
	@Bean(name = {"userDao"})
	public UserDAO getStudentDao() {
		UserDAO userDao = new UserDAO();
		userDao.setJdbcTemplate(getTemplate());
		return userDao;
	}
	
	@Bean(name = {"particularProductDao"})
	public ParticularProductDAO getParticularProductDao() {
		ParticularProductDAO productDao = new ParticularProductDAO();
		productDao.setJdbcTemplate(getTemplate());
		return productDao;
	}
	
	@Bean(name = {"ordersDao"})
	public OrdersDAO getOrdersDao() {
		OrdersDAO ordersDao = new OrdersDAO();
		ordersDao.setJdbcTemplate(getTemplate());
		ordersDao.setPopb(getParticularProductDao());
		return ordersDao;
	}
	@Bean(name = {"DistributorItemDao"})
	public DistributorItemDAO getDistributorItemDao() {
		DistributorItemDAO distributorDao = new DistributorItemDAO();
		distributorDao.setJdbcTemplate(getTemplate());
		return distributorDao;
	}
	@Bean(name = {"ItemsDao"})
	public ItemsDAO getItemsDao() {
		ItemsDAO itemsDao = new ItemsDAO();
		itemsDao.setJdbcTemplate(getTemplate());
		itemsDao.setItemDAO(getDistributorItemDao());
		return itemsDao;
	}

}
