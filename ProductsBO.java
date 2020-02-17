package com.heb.products.bo;

import java.util.List;

import com.heb.products.dao.ProductsDAO;
import com.heb.products.exception.ProductsSystemException;
import com.heb.products.representation.ProductsDetailRO;

public class ProductsBO 
{
	static List<ProductsDetailRO> product;
	static ProductsDAO productsDao = new ProductsDAO();
	
	//Retrieve all products from DAO call
	public static List<ProductsDetailRO> getProducts() throws ProductsSystemException
	{	
		try
		{
			product = productsDao.getProductDetails();
		}
		catch (Exception exception)
		{
			throw new ProductsSystemException(exception.getMessage());
		}
		return product;
	}

	//Retrieve product description from DAO call
	public static List<ProductsDetailRO> getProductDescription(String description) throws ProductsSystemException 
	{
		try
		{
			product = productsDao.getProductDescription(description);
		}
		catch (Exception exception)
		{
			throw new ProductsSystemException(exception.getMessage());
		}
		return product;
	}

	//Retrieve all product department from DAO call
	public static List<ProductsDetailRO> getProductDepartment(String department) throws ProductsSystemException 
	{	
		try
		{
			product = productsDao.getProductDepartment(department);
		}
		catch (Exception exception)
		{
			throw new ProductsSystemException(exception.getMessage());
		}
		return product;
	}

	//Retrieve all product ID from DAO call
	public static List<ProductsDetailRO> getProductId(String id) throws ProductsSystemException
	{
		try
		{
			product = productsDao.getProductId(id);
		}
		catch (Exception exception)
		{
			throw new ProductsSystemException(exception.getMessage());
		}
		return product;
	}
}
