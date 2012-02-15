package util;

import javax.faces.context.FacesContext;

public class Page 
{
	
	public static String getPassedParameter(String param) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get(param);
		return parametreId;
	}
	
	public static String pageCourante()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String view = facesContext.getViewRoot().getViewId();
		
		javax.faces.application.ViewHandler vh = facesContext.getApplication().getViewHandler();
		String pageCourante = vh.getActionURL(FacesContext.getCurrentInstance(), view);
		
		return pageCourante;
		
	}
}
