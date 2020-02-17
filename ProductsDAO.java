package com.heb.products.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.heb.products.representation.ProductsDetailRO;
import com.heb.products.resource.ProductsResource;;

public class ProductsDAO 
{	
	private static final Logger LOGGER = Logger.getLogger(ProductsResource.class.getCanonicalName());
	
	private final String sql ="SELECT ID, DESCRIPTION, LASTSOLD, SHELFLIFE, DEPARTMENT, PRICE, UNIT, XFOR, COST FROM INVENTORY";
	private final String departmentClause = " WHERE DEPARTMENT = ?";
	private final String descriptionClause = " WHERE DESCRIPTION = ?";
	private final String idClause = " WHERE ID = ?";

	//Retrieve all product details
	public List<ProductsDetailRO> getProductDetails()
	{
		final String METHOD_NAME = "getProductDetails";
		
		List <ProductsDetailRO> allProductDetails = retrieveAndMapProducts(METHOD_NAME, sql);
		
		return allProductDetails;
	}
	
	//Retrieve product description
	public List<ProductsDetailRO> getProductDescription(String description)
	{
		final String METHOD_NAME = "getProductDescription";
		
		List <ProductsDetailRO> descriptionProductDetails = retrieveAndMapProductDetails(METHOD_NAME, sql + descriptionClause, description);
		
		return descriptionProductDetails;
	}
	
	//Retrieve product department
	public List<ProductsDetailRO> getProductDepartment(String department)
	{
		final String METHOD_NAME = "getProductDepartment";
		
		List <ProductsDetailRO> departmentProductDetails = retrieveAndMapProductDetails(METHOD_NAME, sql + departmentClause, department);
		
		return departmentProductDetails;
	}
	
	//Retrieve product ID
	public List<ProductsDetailRO> getProductId(String id) 
	{
		final String METHOD_NAME = "getProductId";
		
		List <ProductsDetailRO> idtProductDetails = retrieveAndMapProductDetails(METHOD_NAME, sql + idClause, id);
		
		return idtProductDetails;
	}
	
	private List<ProductsDetailRO> retrieveAndMapProducts(String METHOD_NAME, String sql) 
	{
		List <ProductsDetailRO> products = new ArrayList<ProductsDetailRO>();

		try 
		{
			//Setup Driver and connecting string details
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dheb", "heb_admin", "heb1234");
			
			//Setup prepared statement and result set
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			//Iterate through all results returned from query and set the values retrieved from the Inventory table accordingly
			while(resultSet.next()) 
			{
				ProductsDetailRO product = new ProductsDetailRO();
				
				product.setId(resultSet.getString("id"));
				product.setDescription(resultSet.getString("description"));
				product.setLastSold(resultSet.getString("lastSold"));
				product.setShelfLife(resultSet.getString("shelfLife"));
				product.setDepartment(resultSet.getString("department"));
				product.setPrice(resultSet.getString("price"));
				product.setUnit(resultSet.getString("unit"));
				product.setxFor(resultSet.getString("xFor"));
				product.setCost(resultSet.getString("cost"));
				
				//Add each row to the instantiated list (ProductsDetailRO)
				products.add(product);
			}	
		} 
		catch (ClassNotFoundException | SQLException sqlException) 
		{
			String sqlExceptionName = sqlException.getClass().getSimpleName();
			String sqlErrorMessage = "Caught " + sqlExceptionName + " in " + METHOD_NAME + ": unable to retrieve Product Details data.";
			LOGGER.log(Level.SEVERE, sqlErrorMessage, sqlException);
		}
		
		return products;	
	}
	
	private List<ProductsDetailRO> retrieveAndMapProductDetails(String METHOD_NAME, String sql, String productDetail) 
	{
		List <ProductsDetailRO> products = new ArrayList<ProductsDetailRO>();
		
		try 
		{
			//Setup Driver and connecting string details
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dheb", "heb_admin", "heb1234");
			
			//Setup prepared statement and result set
			PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setString(1, productDetail);
			ResultSet rs = stmt.executeQuery();
			
			/*Iterate through all results returned from query and set 
			the values retrieved from the Inventory table accordingly*/
			while(rs.next()) 
			{
				ProductsDetailRO productDetails = new ProductsDetailRO();
				
				productDetails.setId(rs.getString("id"));
				productDetails.setDescription(rs.getString("description"));
				productDetails.setLastSold(rs.getString("lastSold"));
				productDetails.setShelfLife(rs.getString("shelfLife"));
				productDetails.setDepartment(rs.getString("department"));
				productDetails.setPrice(rs.getString("price"));
				productDetails.setUnit(rs.getString("unit"));
				productDetails.setxFor(rs.getString("xFor"));
				productDetails.setCost(rs.getString("cost"));
				
				//Add each row to the instantiated list (ProductsDetailRO)
				products.add(productDetails);
			}
		} 
		catch (ClassNotFoundException | SQLException sqlException) 
		{
			String sqlExceptionName = sqlException.getClass().getSimpleName();
			String sqlErrorMessage = "Caught " + sqlExceptionName + " in " + METHOD_NAME + ": unable to retrieve Product Details data.";
			LOGGER.log(Level.SEVERE, sqlErrorMessage, sqlException);
		}
		
		return products;
	}
}
