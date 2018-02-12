package com.ccmsd.starters.rest.api;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.ccmsd.starters.init.CustomExceptionMapper;
import com.ccmsd.starters.rest.util.Commons;

@Consumes(
{ "application/json" })
@Produces(
{ "application/json" })
@Path("/v1/")
@Component
public class ServerApi
{
	@GET
	@Path("/{pathparam:.*}")
	public Object apiPareser(@PathParam("pathparam") String pathparam)
	{
		Map<String, Object> db = Commons.loadJson("/db.json", this.getClass());
		Object t_db = db;
		try
		{
			PathStrings p = new PathStrings(pathparam);
			do
			{
				if (t_db instanceof Map<?,?>)
				{
					t_db = getAll((Map<String, Object>) t_db, p.path);
				} else if (t_db instanceof ArrayList)
				{
					t_db = getOneById((ArrayList<Map<String, Object>>) t_db, p.path);
				}
				p = p.nextPath;
			} while (p != null);
			if (t_db == null)
			{
				throw new Exception();
			}
			return t_db;

		} catch (Exception e)
		{
			System.out.println("here");
			return CustomExceptionMapper.buildException(Response.Status.NOT_FOUND, "ID NOT FOUND");
		}

	}

	private Map<String, Object> getOneById(ArrayList<Map<String, Object>> resById, String id)
	{
		for (Map<String, Object> res : resById)
		{
			if (res.get("id").toString().equals(id))
			{
				return res;
			}
		}
		return null;

	}

	private Object getAll(Map<String, Object> db, String path)
	{
		return db.get(path);
	}

	class PathStrings
	{
		private String path;
		private PathStrings nextPath;

		PathStrings(String pathStr)
		{

			this.path = getCurrentString(pathStr);
			pathStr = getNextString(pathStr);
			if (pathStr != "")
			{
				this.nextPath = new PathStrings(pathStr);
			}
		}

		String getCurrentString(String pathStr)
		{
			if (pathStr.startsWith("/"))
			{
				pathStr = pathStr.substring(1);
			}
			if (pathStr.indexOf("/") >= 0)
			{
				return pathStr.substring(0, pathStr.indexOf("/"));
			} else
			{
				return pathStr.substring(0, pathStr.length());
			}
		}

		String getNextString(String pathStr)
		{
			if (pathStr.startsWith("/"))
			{
				pathStr = pathStr.substring(1);
			}
			if (pathStr.indexOf("/") >= 0)
			{
				return pathStr.substring(pathStr.indexOf("/"));
			}
			return "";
		}

		public String getPath()
		{
			return path;
		}

		public void setPath(String path)
		{
			this.path = path;
		}

		public PathStrings getNextPath()
		{
			return nextPath;
		}

		public void setNextPath(PathStrings nextPath)
		{
			this.nextPath = nextPath;
		}

	}
}
