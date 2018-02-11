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
	public String rxChannel = "dahdi/pseud";
	public String duplex ="1";
	public String nodeNumber = "1999";
	public String call = "";
	
	public StringBuffer lineStr;
	StringBuffer s;
	public String resultsStr = "none";
	
	ExtensionsData extData;
	IaxConfigData iaxData;
	RptConfigData rptData;
	SimpleUSBConfigData simple;
	

	
	public ConfigData(){
		
				
		// echolink = new EchoLinkConfigData(call, pwd, name, qth, email, node, astnode,
        // lat,  lon);
		// rpt = new RptConfigData(ch, node, call, duplex);
		// simpleusb = new SimpleUSBConfigData(carrierfrom, ctcssfrom, astnode);  
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
		return(this.rxChannel);
	}

	public void setrxChannel(String rxChannel)
	{
		this.rxChannel = rxChannel;
	}
	
	
	public void setcall(String call)
	{
		this.call = call;
	}
	

	public String getcall()
	{
		return(this.call);
	}
	
	
	
	
	public String buildConfigfiles(){
		
		
		// rpt config
		rptData = new RptConfigData(this.rxChannel, this.node,  this.call, this.duplex);
		//rptData.buildRptString();
		
		
		// iax config
		iaxData = new IaxConfigData(this.bindport, this.node, this.call,  this.pwd); 
		
		//simple USB config
		simple = new SimpleUSBConfigData(this.carrierfrom, this.ctcssfrom, this.node);
		
		
		// echolink in future
		
		return(null);
		
	}
	
	
	
	
}
