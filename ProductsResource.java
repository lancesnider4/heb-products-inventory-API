package com.heb.products.resource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.heb.products.bo.ProductsBO;
import com.heb.products.exception.ProductsSystemException;
import com.heb.products.representation.ProductsDetailRO;

@Path("/v1/products")
public class ProductsResource 
{
	private static final Logger LOGGER = Logger.getLogger(ProductsResource.class.getCanonicalName());
	
	public List<ProductsDetailRO> productsList;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductDetails() 
	{
		try 
		{
			productsList = ProductsBO.getProducts();
			
		} 
		catch (ProductsSystemException exception) 
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.status(Response.Status.OK).entity(productsList).build();
	}

	@GET
	@Path("/descriptions/{description}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductDescriptions(@PathParam("description") String description) 
	{
		if(StringUtils.isNotBlank(description) && !StringUtils.isNumeric(description))
		{
			try 
			{
				productsList = ProductsBO.getProductDescription(description);
			} 
			catch (ProductsSystemException exception) 
			{
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		else
		{
			LOGGER.log(Level.SEVERE, "The description parameter in the request " + description + " was either null/empty/blank or numeric.");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).entity(productsList).build();
	}
	
	@GET
	@Path("/departments/{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductDepartments(@PathParam("department") String department) 
	{
		if(StringUtils.isNotBlank(department) && !StringUtils.isNumeric(department))
		{
			try 
			{
				productsList = ProductsBO.getProductDepartment(department);
			} 
			catch (ProductsSystemException exception) 
			{
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		else
		{
			LOGGER.log(Level.SEVERE, "The department parameter in the request " + department + " was either null/empty/blank or numeric.");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).entity(productsList).build();	
	}
	
	@GET
	@Path("/ids/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductIds(@PathParam("id") String id) 
	{
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id))
		{
			try 
			{
				productsList = ProductsBO.getProductId(id);
			} 
			catch (ProductsSystemException exception) 
			{
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		else
		{
			LOGGER.log(Level.SEVERE, "The id parameter in the request " + id + " was either null/empty/blank or non-numeric.");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).entity(productsList).build();	
	}
	
}
