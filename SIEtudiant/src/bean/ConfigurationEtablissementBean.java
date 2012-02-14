package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.EtablissementConfig;

import ejb.EtablissementEJB;

@ManagedBean(name = "configBean")
@ViewScoped
public class ConfigurationEtablissementBean
{
	
	
	EtablissementEJB etab;
	
	@PostConstruct
	public void init() {
	}
	public Boolean TestEtab()
	{
		
		return false;
		
	}
}
