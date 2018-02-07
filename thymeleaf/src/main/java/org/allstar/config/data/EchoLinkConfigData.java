package org.allstar.config.data;


public class EchoLinkConfigData {

	
	public String call = "NVALID";			//	; Change this!
	public String pwd = "INVALID";			//	; Change this!
	public String name = "YOUR NAME";			//; Change this!
	public String qth = "INVALID";			//	; Change this!
	public String email = "INVALID";			//	; Change this!
	public String node = "000000" ;             //             ; Change this!
	//; Data for EchoLink Status Page
	String lat = "0.0";				//; Latitude in decimal degrees
	String lon = "0.0";				//; Longitude in decimal degrees
	String freq = "0.0";              //                ; not mandatory Frequency in MHz
	String tone = "0.0";              //                ; not mandatory CTCSS Tone (0 for none)
	String power = "0";				//; 0=0W, 1=1W, 2=4W, 3=9W, 4=16W, 5=25W, 6=36W, 7=49W, 8=64W, 9=81W (Power in Watts)
	String height = "0";				//; 0=10 1=20 2=40 3=80 4=160 5=320 6=640 7=1280 8=2560 9=5120 (AMSL in Feet)
	String gain = "0";				//; Gain in db (0-9)
	String dir = "0";				//	; 0=omni 1=45deg 2=90deg 3=135deg 4=180deg 5=225deg 6=270deg 7=315deg 8=360deg (Direction)
	public String astnode = "1999";		//	; Change this!
	
	StringBuffer s;
	public String resultsStr = "none";
	
	/*
	 * Constructor
	 * 
	 */
	public EchoLinkConfigData()
	{

	}


	
	
	/* Constructor 
	 * node = echolink node number
	 * 
	 */
	public EchoLinkConfigData(String call, String pwd, String name,String qth,
			           String email, String node, String astnode)
	{
		
		this.call = call;
		this.pwd = pwd;
		this.name  = name;
		this.qth  = qth;
		this.email = email;
		this.node  = node;		
		this.astnode = astnode;
	}


	/*
	 * call
	 */
	public String getcall() 
	{
		return(this.call);

	}


	/*
	 * call
	 */
	public void setcall(String call)
	{
		this.call = call;

	}

	/*
	 * pwd
	 */
	public String getpwd() 
	{
		return(this.pwd);

	}


	/*
	 * pwd
	 */
	public void setpwd(String pwd)
	{
		this.pwd = pwd;

	}



	/*
	 * name
	 */
	public String getname() 
	{
		return(this.name);

	}


	/*
	 * name
	 */
	public void setname(String name)
	{
		this.name = name;

	}


	/*
	 * qth
	 */
	public String getqth() 
	{
		return(this.qth);

	}


	/*
	 * qth
	 */
	public void setqth(String qth)
	{
		this.qth =  qth;

	}


	/*
	 * email
	 */
	public String getemail() 
	{
		return(this.email);

	}


	/*
	 * email
	 */
	public void setemail(String email)
	{
		this.email =  email;

	}

			 
	
	/*
	 * node
	 */
	public String getnode() 
	{
		return(this.node);

	}


	/*
	 * node
	 */
	public void setnode(String node)
	{
		this.node =  node;

	}


	/*
	 * astnode
	 */
	public String getastnode() 
	{
		return(this.astnode);

	}


	/*
	 * astnode
	 */
	void setastnode(String astnode)
	{
		this.astnode =  astnode;

	}
	
	
	
	
	
	/*
	 * 	buildEcholink() - 	 	 
	 */
	
	public String buildEcholink()
	{
	
		s.append(" [el0]\n" + 
				"call = " + call + "				; Change this!\n" + 
				
				"pwd = "+ pwd +"				; Change this!\n" + 
				"name = "+ name +"			; Change this!\n" + 
				"qth = "+ qth +"				; Change this!\n" + 
				"email = "+ email +"				; Change this!\n" + 
				"node = "+ node +"                           ; Change this!\n");
		s.append("; Data for EchoLink Status Page\n" + 
				"lat = 0.0				; Latitude in decimal degrees\n" + 
				"lon = 0.0				; Longitude in decimal degrees\n" + 
				"freq = 0.0                              ; not mandatory Frequency in MHz\n" + 
				"tone = 0.0                              ; not mandatory CTCSS Tone (0 for none)\n" + 
				"power = 0				; 0=0W, 1=1W, 2=4W, 3=9W, 4=16W, 5=25W, 6=36W, 7=49W, 8=64W, 9=81W (Power in Watts)\n" + 
				"height = 0				; 0=10 1=20 2=40 3=80 4=160 5=320 6=640 7=1280 8=2560 9=5120 (AMSL in Feet)\n" + 
				"gain = 0				; Gain in db (0-9)\n" + 
				"dir = 0					; 0=omni 1=45deg 2=90deg 3=135deg 4=180deg 5=225deg 6=270deg 7=315deg 8=360deg (Direction)\n" + 
				"\n" + 
				"maxstns = 20				; Max Stations\n" + 
				"\n" + 
				"rtcptimeout = 10			; Max number of missed heartbeats from EL\n" + 
				"recfile = /tmp/echolink_recorded.gsm	;\n" + 
				"astnode = "+astnode+"				; Change this!\n" + 
				"context = radio-secure			; Default in code is echolink-in\n");
		s.append("\n" + 
				"; Max 3 servers\n" + 
				"server1 = nasouth.echolink.org\n" + 
				"server2 = naeast.echolink.org\n" + 
				"server3 = server3.echolink.org\n" + 
				"\n" + 
				"; To deny w6xxx you would add the statement: deny = w6xxx\n" + 
				"; To prohibit computer-based connections you would write: permit = *-*\n" + 
				"; To allow access to only a select group of callsigns: permit = w6abc,w6def,...\n" + 
				"\n" + 
				"; permit				; comma delimited list of callsign, type (-r)\n" + 
				"; deny \n");
		s.append(" \n" + 
				"\n" + 
				"; Remote text commands thru netcat:\n" + 
				"; o.conip <IPaddress>    (request a connect)\n" + 
				"; o.dconip <IPaddress>   (request a disconnect)\n" + 
				"; o.rec                  (turn on/off recording)\n" + 
				"\n" + 
				"; ipaddr\n" + 
				"; port\n" + 
				"\n" + 
				"#includeifexists custom/echolink.conf\n");

		this.resultsStr= s.toString();
		return(resultsStr);		
		
	}
}
