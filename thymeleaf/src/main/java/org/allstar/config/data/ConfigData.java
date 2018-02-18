package org.allstar.config.data;


/*
 * 
 */
public class ConfigData {
	
	//iax
	public String bindport = "4569"; // default port
	public String node;
	public String pwd = "";     // Allstar system password
	
	public String carrierfrom;
	public  String ctcssfrom; 
	
	// rpt
	public String rxchannel = "dahdi/pseud";
	public String duplex ="1";
	//public String nodeNumber = "1999";
	public String call = "";
	
	public StringBuffer lineStr;
	StringBuffer s;
	public String resultsSimpleUsbStr = "none";
	public String resultsRptStr = "none";
	public String resultsIaxStr = "none";
	public String resultsExtStr = "none";
	
	ExtensionsData extData;
	IaxConfigData iaxData;
	RptConfigData rptData;
	SimpleUSBConfigData simpleusb;


	
	public ConfigData()
	{	
				
		//EchoLinkConfigData echolink = new EchoLinkConfigData(call, pwd, name, qth, email, node, astnode, lat,  lon);
		rptData = new RptConfigData(rxchannel, node, call, duplex);
		simpleusb = new SimpleUSBConfigData(carrierfrom, ctcssfrom, node);
		iaxData = new IaxConfigData(bindport, node, call, pwd);
		 
	}
	
		
	public void buildConfigfiles()
	{		
		
		// rpt config
		rptData = new RptConfigData(this.rxchannel, this.node,  this.call, this.duplex);
		resultsRptStr = rptData.buildRptString();		
		
		// iax config
		iaxData = new IaxConfigData(this.bindport, this.node, this.call,  this.pwd); 
		resultsIaxStr = iaxData.buildIAX();
		
		//simple USB config
		simpleusb = new SimpleUSBConfigData(this.carrierfrom, this.ctcssfrom, this.node);
		resultsSimpleUsbStr = simpleusb.buildSimpleUsbString();
		
		extData = new ExtensionsData(this.node);
		resultsExtStr = extData.buildExtension();
		// echolink in future

		
	}
	

	public String getExtStr() {
		return(this.resultsExtStr);
		
	}
	
	public String getRptStr() {
		return(this.resultsRptStr);
		
	}	
	
	public String getIaxStr() {
		return(this.resultsIaxStr);
		
	}	
	public String getSimpleUsbStr() {
		return(this.resultsSimpleUsbStr);
		
	}	
	
	
	public String getcarrierfrom() 
	{
		return(this.carrierfrom);
	}
	
	public String getctcssfrom() 
	{
		return(this.ctcssfrom);
	}
	
	public void setnode(String node) 
	{
		this.node= node;
	}
	
	
	public String getnode() 
	{
		return(this.node);
	}
	
	public String getduplex() 
	{
		return(this.duplex);
	}
	
	public void setduplex(String duplex)
	{
		this.duplex = duplex;
	}
	
	public String getrxChannel() 
	{
		return(this.rxchannel);
	}

	public void setrxChannel(String rxchannel)
	{
		this.rxchannel = rxchannel;
	}
	
	
	public void setcall(String call)
	{
		this.call = call;
	}
	

	public String getcall()
	{
		return(this.call);
	}
	
	


	public void setpwd(String pwd)
	{
		this.pwd = pwd;
	}
	

	public String getpwd()
	{
		return(this.pwd);
	}



	public void setbindport(String bindport) 
	{
		this.bindport= bindport;
	}
	
	
	public String getbindport() 
	{
		return(this.bindport);
	}
	
	
	
}
