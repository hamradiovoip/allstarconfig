package org.allstar.config.data;
import java.io.*;

public class RptConfigData {

	public String rxChannel = "dahdi/pseud";
	public String duplex ="1";
	public String nodeNumber = "1999";
	public String call = "";
	
	public StringBuffer lineStr;
	StringBuffer s;
	public String resultsStr = "none";
	
	/*
	 * Constructor
	 */
	public RptConfigData(String rxchannel, String nodeNumber,String  call, String duplex)
	{
		
	    this.nodeNumber= nodeNumber;
		this.rxChannel=rxchannel;	
		this.call = call;
		this.duplex= duplex;
		
		buildRptString();
	}

	/*
	 * Constructor
	 */
	public RptConfigData()
	{	
		buildRptString();
	}
	
	public String getrxChannel()
	{
		return(rxChannel);
	}
	

	public String getresultStr()
	{
		return(this.resultsStr);
	}
	
	public String getnodeNumber() 
	{
		return(this.nodeNumber);
	}
	public String getduplex() 
	{
		return(this.duplex);
	}
	
	public void setduplex(String duplex)
	{
		this.duplex = duplex;
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
	
	
	public void setnodeNumber(String nodeNumber)
	{
		this.nodeNumber = nodeNumber;
	}
	
	
	public String buildRptString()
	{
		
		
		 String channelType = channelStr(rxChannel,nodeNumber);
		
	    lineStr = new StringBuffer("        ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;\n");
	    s = new StringBuffer("; Radio Repeater configuration file (for use with app_rpt)\n");

	   s.append(" ");
	   s.append("; Your Repeater\n"); 
	 s.append("["+nodeNumber+ "] ; Change this to your assigned node number ");
	 s.append("; Must also be enabled in modules.conf\n");
	 s.append("; Rx audio/signalling channel. Choose ONLY 1 per node stanza");
	 s.append("; Enable the selected channel driver in modules.conf !!!\n");
	 
	 // *** rx channel
	 s.append("rxchannel = "+ channelType +"\n"); //TODO 
	 
	 s.append("; rxchannel = dahdi/pseudo	        ; No radio (hub)\n" + 
	 		"	; rxchannel = SimpleUSB/usb_1999	; SimpleUSB \n" + 
	 		"	; rxchannel = Pi/1                      ; Raspberry Pi PiTA \n" + 
	 		"	; rxchannel = Radio/usb_1999		; USBRadio (DSP) \n" + 
	 		"	; rxchannel = Dahdi/1			; PCI Quad card \n" + 
	 		"	; rxchannel = Beagle/1			; BeagleBoard \n" + 
	 		"	; rxchannel = USRP/127.0.0.1:34001:32001; GNU Radio interface USRP  \n");
	 s.append("duplex = "+ this.duplex + "             ; 0 = Half duplex with no telemetry tones or hang time.\n");
	 s.append("\n" + 
	 		"	                                        ;     Special Case: Full duplex if linktolink is set to yes.\n" + 
	 		"	                                        ;     This mode is preferred when interfacing with an external multiport repeater controller.\n" + 
	 		"						                    ;     Comment out idrecording and idtalkover to suppress IDs also\n" + 
	 		"	                                        ; 1 = Half duplex with telemetry tones and hang time. Does not repeat audio.\n" + 
	 		"	                                        ;     This mode is preferred when interfacing a simplex node.\n" + 
	 		"	                                        ; 2 = Full Duplex with telemetry tones and hang time.\n" + 
	 		"	                                        ;     This mode is preferred when interfacing a repeater.\n" + 
	 		"	                                        ; 3 = Full Duplex with telemetry tones and hang time, but no repeated audio.\n" + 
	 		"	                                        ; 4 = Full Duplex with telemetry tones and hang time. Repeated audio only when the autopatch is down.\n" + 
	 		"\n");
	 
	 s.append("linktolink = no				; disables forcing physical half-duplex operation of main repeater while\n" + 
	 		"						; still keeping half-duplex semantics (optional)\n" + 
	 		"\n" + 
	 		"	linkmongain = 0				; Link Monitor Gain adjusts the audio level of monitored nodes when a signal from another node or the local receiver is received.\n" + 
	 		"						; If linkmongain is set to a negative number the monitored audio will decrease by the set amount in db.\n" + 
	 		"						; If linkmongain set to a positive number monitored audio will increase by the set amount in db.\n" + 
	 		"						; The value of linkmongain is in db. The default value is 0 db.\n" + 
	 		"\n" + 
	 		"	erxgain = -3				; Echolink receive gain adjustment\n" + 
	 		"						; Note: Gain is in db-volts (20logVI/VO)\n" + 
	 		"	etxgain = 3				; Echolink transmit gain adjustment\n" + 
	 		"						; Note: Gain is in db-volts (20logVI/VO)\n" + 
	 		"	;eannmode = 1                           ; 1 = Say only node number on echolink connects (default = 1)\n" + 
	 		"	                                        ; 2 = say phonetic call sign only on echolink connects\n" + 
	 		"	                                        ; 3 = say phonetic call sign and node number on echolink connects\n");
	 s.append(";controlstates = controlstates		; system control state stanza\n" + 
	 		"\n" + 
	 		"	scheduler = schedule			; scheduler stanza\n" + 
	 		"	functions = functions			; Repeater Function stanza\n" + 
	 		"	phone_functions = functions		; Phone Function stanza\n" + 
	 		"	link_functions = functions		; Link Function stanza\n" + 
	 		"\n" + 
	 		"	telemetry = telemetry			; Telemetry stanza\n" + 
	 		"	morse = morse				; Morse stanza\n" + 
	 		"	wait_times = wait-times			; Wait times stanza\n" + 
	 		"\n" + 
	 		"	context = radio				; dialing context for phone\n" + 
	 		"	callerid = \\\"Repeater\\\" <0000000000>	; callerid for phone calls\n" + 
	 		"	accountcode = RADIO                     ; account code (optional)\n" + 
	 		"\n" + 
	 		"	hangtime = 5000				; squelch tail hang time (in ms) (optional, default 5 seconds, 5000 ms)\n" + 
	 		"	althangtime = 4000			; longer squelch tail\n" + 
	 		"	totime = 180000				; transmit time-out time (in ms) (optional, default 3 minutes 180000 ms)\n" + 
	 		"\n" + 
	 		"	idrecording = |iWA4XYZ			; cording or morse string see http://ohnosec.org/drupal/node/87\n" + 
	 		"	idtalkover = |iWA4XYZ                   ; Talkover ID (optional) default is none see http://ohnosec.org/drupal/node/129\n" + 
	 		"						; See Telemetry section Example: idrecording = rpt/nodenames/1999\n" + 
	 		"	idtime = 540000				; id interval time (in ms) (optional) Default 5 minutes (300000 ms)\n" + 
	 		"	politeid = 30000			; time in milliseconds before ID timer expires to try and ID in the tail. (optional, default 30000)\n" + 
	 		"\n" + 
	 		"	unlinkedct = ct2			; Send a this courtesy tone when the user unkeys if the node is not connected to any other nodes. (optional, default is none)\n" + 
	 		"	remotect = ct3				; remote linked courtesy tone (indicates a remote is in the list of links)\n" + 
	 		"	linkunkeyct = ct8			; sent when a transmission received over the link unkeys\n" + 
	 		"	;nolocallinkct = 0			; Send unlinkedct instead if another local node is connected to this node (hosted on the same PC).\n" + 
	 		"\n" + 
	 		"	;connpgm = yourconnectprogram		; Disabled. Execute a program you specify on connect. (default)\n" + 
	 		"						; passes 2 command line arguments to your program: \n" + 
	 		"						; 1. node number in this stanza (us)\n" + 
	 		"						; 2. node number being connected to us (them)\n" + 
	 		"	;discpgm = yourdisconnectprogram	; Disabled. Execute a program you specify on disconnect. (default)\n" + 
	 		"						; passes 2 command line arguments to your program: \n" + 
	 		"						; 1. node number in this stanza (us)\n" + 
	 		"						; 2. node number being disconnected from us (them)\n" + 
	 		"\n" + 
	 		"	;lnkactenable = 0			; Set to 1 to enable the link activity timer. Applicable to standard nodes only.\n" + 
	 		"\n" + 
	 		"	;lnkacttime = 1800			; Link activity timer time in seconds.\n" + 
	 		"	;lnkactmacro = *52			; Function to execute when link activity timer expires.\n" + 
	 		"	;lnkacttimerwarn = 30seconds		; Message to play when the link activity timer has 30 seconds left.\n" + 
	 		"\n" + 
	 		"	;remote_inact_timeout =			; Specifies the amount of time without keying from the link. Set to 0 to disable timeout. (15 * 60)\n" + 
	 		"	;remote_timeout =			; Session time out for remote base. Set to 0 to disable. (60 * 60)\n" + 
	 		"	;remote_timeout_warning_freq =		; 30\n" + 
	 		"	;remote_timeout_warning =		; (3 * 60) \n" + 
	 		"\n" + 
	 		"	;nounkeyct = 0				; Set to a 1 to eliminate courtesy tones and associated delays.\n" + 
	 		"\n" + 
	 		"	holdofftelem = 0			; Hold off all telemetry when signal is present on receiver or from connected nodes\n" + 
	 		"						; except when an ID needs to be done and there is a signal coming from a connected node.\n" + 
	 		"\n" + 
	 		"	telemdefault = 1                        ; 0 = telemetry output off\n" + 
	 		"	                                        ; 1 = telemetry output on (default = 1)\n" + 
	 		"	                                        ; 2 = timed telemetry output on command execution and for a short time thereafter.\n" + 
	 		"\n" + 
	 		"	telemdynamic = 1                        ; 0 = disallow users to change the local telemetry setting with a COP command,\n" + 
	 		"	                                        ; 1 = Allow users to change the setting with a COP command. (default = 1)\n" + 
	 		"\n" + 
	 		"	;beaconing = 0				; Send ID regardless of repeater activity (Required in the UK, but probably illegal in the US)\n" + 
	 		"\n" + 
	 		"	parrotmode = 0				; 0 = Parrot Off (default = 0)\n" + 
	 		"						; 1 = Parrot On Command\n" + 
	 		"						; 2 = Parrot Always\n" + 
	 		"						; 3 = Parrot Once by Command\n" + 
	 		"\n" + 
	 		"	parrottime = 1000			; Set the amount of time in milliseconds \n" + 
	 		"						; to wait before parroting what was received\n" + 
	 		"\n" + 
	 		"	;rxnotch=1065,40                        ; (Optional) Notch a particular frequency for a specified\n" + 
	 		"	                                        ; b/w. app_rpt must have been compiled with\n" + 
	 		"	                                        ; the notch option\n" + 
	 		"\n");
	 s.append("startup_macro = \n" + 
	 		"\n" + 
	 		"	; nodenames = /var/lib/asterisk/sounds/rpt/nodenames.callsign	; Point to alternate nodename sound directory\n" + 
	 		"\n" + 
	 		"	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;\n" + 
	 		"	; Need more information on these\n" + 
	 		"\n" + 
	 		"	;extnodes = extnodes-different	; section in extnodefile containing dynamic node information (optional)\n" + 
	 		"	;extnodefile = /foo/nodes	; Points to nodelist file containing dynamic node info default = /var/lib/asterisk/rpt_extnodes (optional)\n" + 
	 		"	;extnodefile2 =			; Is this a list of node files? Possible a list of private nodes or a list of static IPs for known nodes???? \n" + 
	 		"	;nodenames = /foo/names         ; locaton of node sound files default = /var/lib/asterisk/sounds/rpt/nodenames\n" + 
	 		"	;archivedir = /tmp              ; defines and enables activity recording into specified directory (optional)\n" + 
	 		"	;monminblocks = 2048            ; Min 1K blocks to be left on partition (will not save monitor output if disk too full)\n" + 
	 		"\n" + 
	 		"	;                               ; The tailmessagetime,tailsquashedtime, and tailmessagelist need to be set\n" + 
	 		"	;                               ; to support tail messages. They can be omitted otherwise.\n" + 
	 		"	;tailmessagetime = 300000       ; Play a tail message every 5 mins\n" + 
	 		"	;tailsquashedtime = 30000       ; If squashed by another user,\n" + 
	 		"	;                               ; try again after 30 seconds\n" + 
	 		"	;tailmessagelist = msg1,msg2    ; list of messages to be played for tail message\n" + 
	 		"\n" + 
	 		"	; alt_functions\n" + 
	 		"	; ctgroup\n" + 
	 		"	; dphone_functions\n" + 
	 		"	; idtime\n" + 
	 		"	; iobase\n" + 
	 		"	; iospeed\n" + 
	 		"	; locallist\n" + 
	 		"	; mars		Remote Base\n" + 
	 		"	; memory\n" + 
	 		"	; nobusyout\n" + 
	 		"	; nodes\n" + 
	 		"	; nolocallinkct\n" + 
	 		"	; notelemtx\n" + 
	 		"	; outxlat\n" + 
	 		"	; parrot\n" + 
	 		"	; propagate_phonedtmf\n" + 
	 		"	; rptnode\n" + 
	 		"	; rptinactmacro  Macro to execute when inactivity timer expires \n" + 
	 		"	; rptinacttime   Inactivity timer time in seconds  (0 seconds disables feature)\n" + 
	 		"	; rxnotch	Optional Audio notch\n" + 
	 		"	; simplexphonedelay\n" + 
	 		"	; tonemacro\n" + 
	 		"	; tonezone\n" + 
	 		"	; txlimits\n");
	 s.append(lineStr);
	 
	 /* *** Status Reporting ****/
	 s.append("statpost_program = /usr/bin/wget,-q,--timeout=15,--tries=1,--output-document=/dev/null\n");
	 s.append("statpost_url = http://stats.allstarlink.org/uhandler.php ; Status updates \n");
	 s.append("	; *** Status Reporting ***\n" + 
	 		"\n" + 
	 		"	; Uncomment either group following two statpost lines to report the status of your node to stats.allstarlink.org\n" + 
	 		"	; depending on whether you are running ACID, Debian or Limey Linux.\n" + 
	 		"	; The difference is simply where your wget is located.\n" + 
	 		"\n" + 
	 		"	; ** For ACID and Debian ***\n" + 
	 		"	;statpost_program = /usr/bin/wget,-q,--timeout=15,--tries=1,--output-document=/dev/null                       \n" + 
	 		"	;statpost_url = http://stats.allstarlink.org/uhandler.php ; Status updates \n" + 
	 		"\n" + 
	 		"	; ** For Limey Linux **\n" + 
	 		"	;statpost_program = /bin/wget,-q,--timeout=15,--tries=1,--output-document=/dev/null                       \n" + 
	 		"	;statpost_url = http://stats.allstarlink.org/uhandler.php ; Status updates \n");
	// functions
	 s.append("\n" + 
	 		"	[functions]\n" + 
	 		"\n" + 
	 		"	; Prefix	Functions\n" + 
	 		"	; *1		Disconnect Link\n" + 
	 		"	; *2		Monitor Link\n" + 
	 		"	; *3		Connect Link\n" + 
	 		"	; *4		Command Mode\n" + 
	 		"	; *5		Macros\n" + 
	 		"	; *6		User Functions\n" + 
	 		"	; *7		Connection Status/Functions\n" + 
	 		"	; *8		User Functions\n" + 
	 		"	; *9		User Functions\n" + 
	 		"	; *0		User Functions\n" + 
	 		"\n" + 
	 		"	; *A		User Functions\n" + 
	 		"	; *B		User Functions\n" + 
	 		"	; *C		User Functions\n" + 
	 		"	; *D		User Functions\n" + 
	 		"\n" + 
	 		"\n" + 
	 		"	; Mandatory Command Codes\n" + 
	 		"	; http://docs.allstarlink.org/drupal/node/91\n" + 
	 		"\n" + 
	 		"	1 = ilink,1		; Disconnect specified link\n" + 
	 		"	2 = ilink,2		; Connect specified link -- monitor only\n" + 
	 		"	3 = ilink,3		; Connect specified link -- tranceive\n" + 
	 		"	4 = ilink,4		; Enter command mode on specified link\n" + 
	 		"	70 = ilink,5		; System status\n" + 
	 		"	99 = cop,6              ; PTT (phone mode only)\n" + 
	 		"\n" + 
	 		"	; End Mandatory Command Codes\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 // macro cmds
	 s.append("; Macro Commands\n" + 
	 		"	5 = macro\n" + "\n");
	 s.append(lineStr);
	 
	 // auto patch
	 s.append("; Autopatch Commands\n" + 
	 		"	; Note, This may be a good place for other 2 digit frequently used commands  \n" + 
	 		"\n" + 
	 		"	61 = autopatchup,noct = 1,farenddisconnect = 1,dialtime = 20000  ; Autopatch up\n" + 
	 		"	62 = autopatchdn                                                 ; Autopatch down\n" + 
	 		"\n" + 
	 		"	; autopatchup can optionally take comma delimited setting=value pairs:\n" + 
	 		"\n" + 
	 		"	; context = string		; Override default context with \\\"string\\\"\n" + 
	 		"	; dialtime = ms			; Specify the max number of milliseconds between phone number digits (1000 milliseconds = 1 second)\n" + 
	 		"	; farenddisconnect = 1		; Automatically disconnect when called party hangs up\n" + 
	 		"	; noct = 1			; Don't send repeater courtesy tone during autopatch calls\n" + 
	 		"	; quiet = 1			; Don't send dial tone, or connect messages. Do not send patch down message when called party hangs up\n" + 
	 		"					; Example: 123=autopatchup,dialtime=20000,noct=1,farenddisconnect=1\n");
	 s.append(lineStr);
	 
	 // status cmds
	 s.append("; Status Commands\n" + 
	 		"\n" + 
	 		"	; 1 - Force ID (global)\n" + 
	 		"	; 2 - Give Time of Day (global)\n" + 
	 		"	; 3 - Give software Version (global)\n" + 
	 		"	; 4 - Give GPS location info\n" + 
	 		"	; 5 - Last (dtmf) user\n" + 
	 		"	; 11 - Force ID (local only)\n" + 
	 		"	; 12 - Give Time of Day (local only)\n" + 
	 		"\n" + 
	 		"	721 = status,1          ; Force ID (global)\n" + 
	 		"	722 = status,2          ; Give Time of Day (global)\n" + 
	 		"	723 = status,3          ; Give software Version (global)\n" + 
	 		"	724 = status,4          ; Give GPS location info\n" + 
	 		"	725 = status,5          ; Last (dtmf) user\n" + 
	 		"	711 = status,11         ; Force ID (local only)\n" + 
	 		"	712 = status,12         ; Give Time of Day (local only)\n");
	 
	 
	 s.append(lineStr);
	 
	 //link cmds
	 s.append("; Link Commands\n" + 
	 		"\n" + 
	 		"	; 1 - Disconnect specified link\n" + 
	 		"	; 2 - Connect specified link -- monitor only\n" + 
	 		"	; 3 - Connect specified link -- tranceive\n" + 
	 		"	; 4 - Enter command mode on specified link\n" + 
	 		"	; 5 - System status\n" + 
	 		"	; 6 - Disconnect all links\n" + 
	 		"	; 7 - Last Node to Key Up\n" + 
	 		"	; 8 - Connect specified link -- local monitor only\n" + 
	 		"	; 9 - Send Text Message (9,<destnodeno or 0 (for all)>,Message Text, etc.\n" + 
	 		"	; 10 - Disconnect all RANGER links (except permalinks)\n" + 
	 		"	; 11 - Disconnect a previously permanently connected link\n" + 
	 		"	; 12 - Permanently connect specified link -- monitor only\n" + 
	 		"	; 13 - Permanently connect specified link -- tranceive\n" + 
	 		"	; 15 - Full system status (all nodes)\n" + 
	 		"	; 16 - Reconnect links disconnected with \\\"disconnect all links\\\"\n" + 
	 		"	; 17 - MDC test (for diag purposes)\n" + 
	 		"	; 18 - Permanently Connect specified link -- local monitor only\n");
	 s.append(lineStr);
	 
	 // ilink
	 s.append("; ilink commands 1 through 5 are defined in the Mandatory Command section\n" + 
	 		"\n" + 
	 		"	806 = ilink,6			; Disconnect all links\n" + 
	 		"	807 = ilink,7			; Last Node to Key Up\n" + 
	 		"	808 = ilink,8			; Connect specified link -- local monitor only\n" + 
	 		"	809 = ilink,9,"+nodeNumber+",\\\"Testing\\\"	; would send a text message to node 1999 replace 1999 with 0 for all connected nodes\n" + 
	 		"	810 = ilink,10			; Disconnect all RANGER links (except permalinks)\n" + 
	 		"	811 = ilink,11			; Disconnect a previously permanently connected link\n" + 
	 		"	812 = ilink,12			; Permanently connect specified link -- monitor only\n" + 
	 		"	813 = ilink,13			; Permanently connect specified link -- tranceive\n" + 
	 		"	815 = ilink,15			; Full system status (all nodes)\n" + 
	 		"	816 = ilink,16			; Reconnect links disconnected with \\\"disconnect all links\\\"\n" + 
	 		"	817 = ilink,17			; MDC test (for diag purposes)\n" + 
	 		"	818 = ilink 18			; Permanently Connect specified link -- local monitor only\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 //  Control operator (cop) functions.
	 s.append("; Control operator (cop) functions. Change these to something other than these codes listed below!\n" + 
	 		"\n" + 
	 		"	901 = cop,1				; System warm boot \n" + 
	 		"	902 = cop,2				; System enable\n" + 
	 		"	903 = cop,3				; System disable\n" + 
	 		"\n" + 
	 		"	904 = cop,4				; Test tone on/off (toggle)\n" + 
	 		"	905 = cop,5				; Dump system variables on console (debug use only)\n" + 
	 		"\n" + 
	 		"	907 = cop,7				; Time out timer enable\n" + 
	 		"	908 = cop,8				; Time out timer disable\n" + 
	 		"\n" + 
	 		"	909 = cop,9				; Autopatch enable\n" + 
	 		"	910 = cop,10				; Autopatch disable\n" + 
	 		"\n" + 
	 		"	911 = cop,11				; User linking functions enable\n" + 
	 		"	912 = cop,12				; User linking functions disable\n" + 
	 		"\n" + 
	 		"	913 = cop,13				; Query system control state\n" + 
	 		"	914 = cop,14				; Set system control state\n" + 
	 		"\n" + 
	 		"	915 = cop,15				; Scheduler enable\n" + 
	 		"	916 = cop,16				; Scheduler disable\n" + 
	 		"\n" + 
	 		"	917 = cop,17				; User functions enable (time, id, etc)\n" + 
	 		"	918 = cop,18				; User functions disable\n" + 
	 		"\n" + 
	 		"	919 = cop,19				; Select alternate hang time (althangtime)\n" + 
	 		"	920 = cop,20				; Select standard hangtime (hangtime)\n" + 
	 		"\n" + 
	 		"	921 = cop,21				; Enable Parrot Mode\n" + 
	 		"	922 = cop,22				; Disable Parrot Mode\n" + 
	 		"	923 = cop,23				; Birdbath (Current Parrot Cleanup/Flush)\n" + 
	 		"\n" + 
	 		"	924 = cop,24				; Flush all telemetry\n" + 
	 		"	925 = cop,25				; Query last node un-keyed\n" + 
	 		"	926 = cop,26				; Query all nodes keyed/unkeyed\n" + 
	 		"	927 = cop,27				; Reset DAQ minimum on a pin\n" + 
	 		"	928 = cop,28				; Reset DAQ maximum on a pin\n" + 
	 		"\n" + 
	 		"	930 = cop,30				; Recall Memory Setting in Attached Xcvr\n" + 
	 		"\n" + 
	 		"	931 = cop,31				; Channel Selector for Parallel Programmed Xcvr\n" + 
	 		"\n" + 
	 		"	932 = cop,32				; Touchtone pad test: command + Digit string + # to playback all digits pressed\n" + 
	 		"\n" + 
	 		"	933 = cop,33				; Local Telemetry Output Enable\n" + 
	 		"	934 = cop,34				; Local Telemetry Output Disable\n" + 
	 		"	935 = cop,35				; Local Telemetry Output on Demand\n" + 
	 		"\n" + 
	 		"	936 = cop,36				; Foreign Link Local Output Path Enable\n" + 
	 		"	937 = cop,37				; Foreign Link Local Output Path Disable\n" + 
	 		"	938 = cop,38				; Foreign Link Local Output Path Follows Local Telemetry\n" + 
	 		"	939 = cop,39				; Foreign Link Local Output Path on Demand\n" + 
	 		"\n" + 
	 		"	942 = cop,42				; Echolink announce node # only\n" + 
	 		"	943 = cop,43				; Echolink announce node Callsign only\n" + 
	 		"	944 = cop,44				; Echolink announce node # & Callsign\n" + 
	 		"\n" + 
	 		"	945 = cop,45				; Link Activity timer enable\n" + 
	 		"	945 = cop,46				; Link Activity timer disable\n" + 
	 		"	947 = cop,47				; Reset \\\"Link Config Changed\\\" Flag \n" + 
	 		"\n" + 
	 		"	948 = cop,48				; Send Page Tone (Tone specs separated by parenthesis)\n" + 
	 		"\n" + 
	 		"	949 = cop,49				; Disable incoming connections (control state noice)\n" + 
	 		"	950 = cop,50				; Enable incoming connections (control state noicd)\n" + 
	 		"\n" + 
	 		"	951 = cop,51				; Enable sleep mode\n" + 
	 		"	952 = cop,52				; Disable sleep mode\n" + 
	 		"	953 = cop,53				; Wake up from sleep\n" + 
	 		"	954 = cop,54				; Go to sleep\n" + 
	 		"	955 = cop,55				; Parrot Once if parrot mode is disabled\n" + 
	 		"\n" + 
	 		"	956 = cop,56                            ; Rx CTCSS Enable\n" + 
	 		"	957 = cop,57                            ; Rx CTCSS Disable\n" + 
	 		"\n" + 
	 		"	958 = cop.58                            ; Tx CTCSS On Input only Enable\n" + 
	 		"	959 = cop,59                            ; Tx CTCSS On Input only Disable\n" + 
	 		"\n" + 
	 		"	960 = cop,60                            ; Send MDC-1200 Burst (cop,60,type,UnitID[,DestID,SubCode])\n" + 
	 		"	                                        ; Type is 'I' for PttID, 'E' for Emergency, and 'C' for Call\n" + 
	 		"	                                        ; (SelCall or Alert), or 'SX' for STS (ststus), where X is 0-F.\n" + 
	 		"	                                        ; DestID and subcode are only specified for  the 'C' type message.\n" + 
	 		"	                                        ; UnitID is the local systems UnitID. DestID is the MDC1200 ID of\n" + 
	 		"	                                        ; the radio being called, and the subcodes are as follows:\n" + 
	 		"	                                        ; Subcode '8205' is Voice Selective Call for Spectra ('Call')\n" + 
	 		"	                                        ; Subcode '8015' is Voice Selective Call for Maxtrac ('SC') or\n" + 
	 		"	                                        ; Astro-Saber('Call')\n" + 
	 		"	                                        ; Subcode '810D' is Call Alert (like Maxtrac 'CA')\n" + 
	 		"\n" + 
	 		"	961 = cop,61                            ; Send Message to USB to control GPIO pins (cop,61,GPIO1=0[,GPIO4=1].....)\n" + 
	 		"	962 = cop,62                            ; Send Message to USB to control GPIO pins, quietly (cop,62,GPIO1=0[,GPIO4=1].....)\n" + 
	 		"\n" + 
	 		"	963 = cop,63                            ; Send pre-configred APRSTT notification (cop,63,CALL[,OVERLAYCHR])\n" + 
	 		"	964 = cop,64                            ; Send pre-configred APRSTT notification, quietly (cop,64,CALL[,OVERLAYCHR])\n" + 
	 		"	965 = cop,65                            ; Send POCSAG page (equipped channel types only)\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 //[functions-remote]
	 s.append("	\n" + 
	 		"	[functions-remote]\n" + 
	 		"\n" + 
	 		"	0 = remote,1                            ; Retrieve Memory\n" + 
	 		"	1 = remote,2                            ; Set freq.\n" + 
	 		"	2 = remote,3                            ; Set tx PL tone\n" + 
	 		"	3 = remote,4                            ; Set rx PL tone\n" + 
	 		"	40 = remote,100                         ; Rx PL off\n" + 
	 		"	41 = remote,101                         ; Rx PL on\n" + 
	 		"	42 = remote,102                         ; Tx PL off\n" + 
	 		"	43 = remote,103                         ; Tx PL on\n" + 
	 		"	44 = remote,104                         ; Low Power\n" + 
	 		"	45 = remote,105                         ; Medium Power\n" + 
	 		"	46 = remote,106                         ; High Power\n" + 
	 		"	711 = remote,107                        ; Bump -20\n" + 
	 		"	714 = remote,108                        ; Bump -100\n" + 
	 		"	717 = remote,109                        ; Bump -500\n" + 
	 		"	713 = remote,110                        ; Bump +20\n" + 
	 		"	716 = remote,111                        ; Bump +100\n" + 
	 		"	719 = remote,112                        ; Bump +500\n" + 
	 		"	721 = remote,113                        ; Scan - slow\n" + 
	 		"	724 = remote,114                        ; Scan - quick\n" + 
	 		"	727 = remote,115                        ; Scan - fast\n" + 
	 		"	723 = remote,116                        ; Scan + slow\n" + 
	 		"	726 = remote,117                        ; Scan + quick\n" + 
	 		"	729 = remote,118                        ; Scan + fast\n" + 
	 		"	79 = remote,119                         ; Tune\n" + 
	 		"	51 = remote,5                           ; Long status query\n" + 
	 		"	52 = remote,140				; Short status query\n" + 
	 		"	67 = remote,210				; Send a *\n" + 
	 		"	69 = remote,211				; Send a #\n" + 
	 		"	;91 = remote,99,CALLSIGN,LICENSETAG     ; Remote base login.\n" + 
	 		"	                                        ; Define a different dtmf sequence for each user which is\n" + 
	 		"	                                        ; authorized to use the remote base to control access to it.\n" + 
	 		"	                                        ; For examble 9139583=remote,99,WB6NIL,G would grant access to\n" + 
	 		"	                                        ; the remote base and announce WB6NIL as being logged in.\n" + 
	 		"	                                        ; Another entry, 9148351=remote,99,WA6ZFT,E would grant access to\n" + 
	 		"	                                        ; the remote base and announce WA6ZFT as being logged in.\n" + 
	 		"	                                        ; When the remote base is disconnected from the originating node, the\n" + 
	 		"	                                        ; user will be logged out. The LICENSETAG argument is used to enforce\n" + 
	 		"						; tx frequency limits. See [txlimits] below.\n" + 
	 		"	85 = cop,6                              ; Remote base telephone key\n" + 
	 		"\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 
	 // [telemetry]
	 s.append("[telemetry]\n" + 
	 		"\n" + 
	 		"	; Telemetry entries can be shared across all repeaters, or defined for each repeater.\n" + 
	 		"	; Can be a tone sequence, morse string, or a file\n" + 
	 		"	;\n" + 
	 		"	; |t - Tone escape sequence\n" + 
	 		"	;\n" + 
	 		"	; Tone sequences consist of 1 or more 4-tuple entries (freq1, freq2, duration, amplitude)\n" + 
	 		"	; Single frequencies are created by setting freq1 or freq2 to zero.\n" + 
	 		"	;\n" + 
	 		"	; |m - Morse escape sequence\n" + 
	 		"	; \n" + 
	 		"	; Sends Morse code at the telemetry amplitude and telemetry frequency as defined in the\n" + 
	 		"	; [morse] section.\n" + 
	 		"	;\n" + 
	 		"	; Follow with an alphanumeric string\n" + 
	 		"	;\n" + 
	 		"	; |i - Morse ID escape sequence\n" + 
	 		"	;\n" + 
	 		"	; Sends Morse code at the ID amplitude and ID frequency as defined in the\n" + 
	 		"	; [morse] section.\n" + 
	 		"	;\n" + 
	 		"	; path/to/sound/file/without/extension\n" + 
	 		"	;\n" + 
	 		"	; Send the sound if in place of a constructed tone. Do not include the file extension\n" + 
	 		"	; Example: ct8 = rpt/bloop\n" + 
	 		"	; Example: idrecording = rpt/nodenames/1999\n" + 
	 		"\n" + 
	 		"	ct1 = |t(350,0,100,2048)(500,0,100,2048)(660,0,100,2048)\n" + 
	 		"	ct2 = |t(660,880,150,2048)  \n" + 
	 		"	ct3 = |t(440,0,150,4096) \n" + 
	 		"	ct4 = |t(550,0,150,2048)\n" + 
	 		"	ct5 = |t(660,0,150,2048)\n" + 
	 		"	ct6 = |t(880,0,150,2048)\n" + 
	 		"	ct7 = |t(660,440,150,2048)\n" + 
	 		"	ct8 = |t(700,1100,150,2048)\n" + 
	 		"	ranger = |t(1800,0,60,3072)(0,0,50,0)(1800,0,60,3072)(0,0,50,0)(1800,0,60,3072)(0,0,50,0)(1800,0,60,3072)(0,0,50,0)(1800,0,60,3072)(0,0,50,0)(1800,0,60,3072)(0,0,150,0)\n" + 
	 		"	remotemon = |t(1209,0,50,2048)                                  ; local courtesy tone when receive only\n" + 
	 		"	remotetx = |t(1633,0,50,3000)(0,0,80,0)(1209,0,50,3000)		; local courtesy tone when linked Trancieve mode\n" + 
	 		"	cmdmode = |t(900,903,200,2048)\n" + 
	 		"	functcomplete = |t(1000,0,100,2048)(0,0,100,0)(1000,0,100,2048)\n" + 
	 		"	remcomplete = |t(650,0,100,2048)(0,0,100,0)(650,0,100,2048)(0,0,100,0)(650,0,100,2048)\n" + 
	 		"	pfxtone = |t(350,440,30000,3072)\n" + 
	 		"	patchup = rpt/callproceeding\n" + 
	 		"	patchdown = rpt/callterminated\n" + 
	 		"\n" + 
	 		"	; As far as what the numbers mean,\n" + 
	 		"	; (000,000,010,000)\n" + 
	 		"	;   |   |   |   |-------amplitude\n" + 
	 		"	;   |   |   |-------------duration\n" + 
	 		"	;   |   |-------------------Tone 2\n" + 
	 		"	;   |-------------------------Tone 1\n" + 
	 		"\n" + 
	 		"	; So, with 0,0,10,0 That says No Tone1, No Tone2, 10ms duration, 0 Amplitude.\n" + 
	 		"	; Use it for a delay.  Fine tuning for how long before telemetry is sent, in conjunction with the telemdelay parameter)\n" + 
	 		"	; The numbers, like 350,440,10,2048 are 350Hz, 440Hz, 10ms delay, amplitude of 2048.\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 
	 //morse
	 s.append("\n" + 
	 		"	\n" + 
	 		"	; Morse code parameters, these are common to all repeaters.\n" + 
	 		"\n" + 
	 		"	[morse]\n" + 
	 		"	speed = 20				; Approximate speed in WPM\n" + 
	 		"	frequency = 800				; Morse Telemetry Frequency\n" + 
	 		"	amplitude = 4096			; Morse Telemetry Amplitude\n" + 
	 		"	idfrequency = 1065			; Morse ID Frequency	\n" + 
	 		"	idamplitude = 1024			; Morse ID Amplitude\n");
	 
	 s.append(lineStr);
	 
	 // wait times
	 s.append("	;\n" + 
	 		"	; This section allows wait times for telemetry events to be adjusted\n" + 
	 		"	; A section for wait times can be defined for every repeater\n" + 
	 		"	;\n" + 
	 		"\n" + 
	 		"	[wait-times]                                                                                                 \n" + 
	 		"	telemwait = 2000                        ; Time to wait before sending most telemetry\n" + 
	 		"	idwait = 500                            ; Time to wait before starting ID\n" + 
	 		"	unkeywait = 2000                        ; Time to wait after unkey before sending CT's and link telemetry\n" + 
	 		"	calltermwait = 2000                     ; Time to wait before announcing \\\"call terminated\\\"\n" + 
	 		"\n" + 
	 		"	;\n" + 
	 		"	; This is where you define your nodes which can be connected to.\n" + 
	 		"	;\n" + 
	 		"\n");
	 
	 s.append(lineStr);
	 
	 //nodes
	 s.append("\n" + 
	 		"	[nodes]\n" + 
	 		"	; Note, if you are using automatic update for allstar link nodes,\n" + 
	 		"	; no allstar link nodes should be defined here. Only place a definition\n" + 
	 		"	; for your local nodes, and private (off of allstar link) nodes here.\n" + 
	 		"\n" + 
	 		"	"+nodeNumber+" = radio@127.0.0.1:4569/"+ nodeNumber + ",NONE	; This must be changed to your node number\n" + 
	 		"	                                        ; and iax port number if not the default\n");
	 
	 s.append(lineStr);
	 s.append("\n" + 
	 		"\n" + 
	 		"	; Memories for remote bases\n" + 
	 		"\n" + 
	 		"	[memory]\n" + 
	 		"	;00 = 146.580,100.0,m\n" + 
	 		"	;01 = 147.030,103.5,m+t\n" + 
	 		"	;02 = 147.240,103.5,m+t\n" + 
	 		"	;03 = 147.765,79.7,m-t\n" + 
	 		"	;04 = 146.460,100.0,m\n" + 
	 		"	;05 = 146.550,100.0,m\n" + 
	 		"\n" + 
	 		"	; Place command macros here\n" + 
	 		"\n" + 
	 		"	[macro]\n" + 
	 		"	;1 = *32011#\n" + 
	 		"	;2 = *12001*12011*12043*12040*12050*12060*12009*12002*12003*12004*1113*12030#\n" + 
	 		"	;3 = *32001*32011*32050*32030*32060#\n" + 
	 		"\n" + 
	 		"\n" + 
	 		"	; Data Acquisition configuration\n" + 
	 		"\n" + 
	 		"	;[daq-list]\n" + 
	 		"	;device = device_name1\n" + 
	 		"	;device = device_name2\n" + 
	 		"\n" + 
	 		"	;Where: device_name1 and device_name2 are stanzas you define in this file\n" + 
	 		"\n" + 
	 		"	;device = daq-cham-1\n" + 
	 		"\n" + 
	 		"	; Device name\n" + 
	 		"\n" + 
	 		"	;[daq-cham-1]				; Defined in [daq-list]\n" + 
	 		"	;hwtype = uchameleon			; DAQ hardware type\n" + 
	 		"	;devnode = /dev/ttyUSB0			; DAQ device node (if required)\n" + 
	 		"	;1 = inadc				; Pin definition for an ADC channel\n" + 
	 		"	;2 = inadc\n" + 
	 		"	;3 = inadc\n" + 
	 		"	;4 = inadc\n" + 
	 		"	;5 = inadc\n" + 
	 		"	;6 = inadc\n" + 
	 		"	;7 = inadc\n" + 
	 		"	;8 = inadc\n" + 
	 		"	;9 = inp				; Pin definition for an input with a weak pullup resistor\n" + 
	 		"	;10 = inp\n" + 
	 		"	;11 = inp\n" + 
	 		"	;12 = inp\n" + 
	 		"	;13 = in				; Pin definition for an input without a weak pullup resistor\n" + 
	 		"	;14 = out				; Pin definition for an output\n" + 
	 		"	;15 = out\n" + 
	 		"	;16 = out\n" + 
	 		"	;17 = out\n" + 
	 		"	;18 = out\n" + 
	 		"\n" + 
	 		"	;[meter-faces]\n" + 
	 		"\n" + 
	 		"	;face = scale(scalepre,scalediv,scalepost),word/?,...\n" + 
	 		"	;\n" + 
	 		"	; scalepre = offset to add before dividing with scalediv\n" + 
	 		"	; scalediv = full scale/number of whole units (e.g. 256/20 or 12.8 for 20 volts).\n" + 
	 		"	; scalepost = offset to add after dividing with scalediv\n" + 
	 		"	;\n" + 
	 		"	;face = range(X-Y:word,X2-Y2:word,...),word/?,...\n" + 
	 		"	;face = bit(low-word,high-word),word/?,...\n" + 
	 		"	;\n" + 
	 		"	; word/? is either a word in /var/lib/asterisk/sounds or one of its subdirectories,\n" + 
	 		"	; or a question mark which is  a placeholder for the measured value.\n" + 
	 		"	;\n" + 
	 		"	;\n" + 
	 		"	; Battery voltage 0-20 volts\n" + 
	 		"	;batvolts = scale(0,12.8,0),rpt/thevoltageis,?,ha/volts\n" + 
	 		"	; 4 quadrant wind direction\n" + 
	 		"	;winddir = range(0-33:north,34-96:west,97-160:south,161-224:east,225-255:north),rpt/thewindis,?\n" + 
	 		"	; LM34 temperature sensor with 130 deg. F full scale\n" + 
	 		"	;lm34f = scale(0,1.969,0),rpt/thetemperatureis,?,degrees,fahrenheit\n" + 
	 		"	; Status poll (non alarmed)\n" + 
	 		"	;light = bit(ha/off,ha/on),ha/light,?\n" + 
	 		"\n" + 
	 		"	;[alarms]\n" + 
	 		"	;\n" + 
	 		"	;tag = device,pin,node,ignorefirst,func-low,func-hi\n" + 
	 		"	;\n" + 
	 		"	;tag = a unique name for the alarm\n" + 
	 		"	;device = daq device to poll\n" + 
	 		"	;pin = the device pin to be monitored\n" + 
	 		"	;ignorefirstalarm = set to 1 to throwaway first alarm event, or 0 to report it\n" + 
	 		"	;node = the node number to execute the function on\n" + 
	 		"	;func-low = the DTMF function to execute on a high to low transition\n" + 
	 		"	;func-high = the DTMF function to execute on a low to high transition\n" + 
	 		"	;\n" + 
	 		"	; a  '-' as a function name is shorthand for no-operation\n" + 
	 		"	;\n" + 
	 		"	;door = daq-cham-1,9,1,2017,*7,-\n" + 
	 		"	;pwrfail = daq-cham-1,10,0,2017,*911111,-\n" + 
	 		"	;\n" + 
	 		"	; Control states\n" + 
	 		"	; Allow several control operator functions to be changed at once using one command (good for scheduling)\n" + 
	 		"	;\n" + 
	 		"	;[controlstates]                                                          \n" + 
	 		"	;statenum = copcmd,[copcmd]...                                                  \n" + 
	 		"	;0 = rptena,lnkena,apena,totena,ufena,noicd  ; Normal operation                                  \n" + 
	 		"	;1 = rptena,lnkena,apdis,totdis,ufena,noice  ; Net and news operation                                             \n" + 
	 		"	;2 = rptena,lnkdis,apdis,totena,ufdis,noice  ; Repeater only operation\n" + 
	 		"\n" + 
	 		"	; Scheduler - execute a macro at a given time\n" + 
	 		"\n" + 
	 		"	[schedule]                                                                      \n" + 
	 		"	;dtmf_function =  m h dom mon dow  ; ala cron, star is implied                                                  \n" + 
	 		"	;2 = 00 00 * * *   ; at midnight, execute macro 2.       \n" + 
	 		"\n" + 
	 		"	#includeifexists custom/rpt.conf\n");
	 
	 s.append(lineStr);
	 s.append("end \n");
		
			 		
	  this.resultsStr= s.toString();
	  return(resultsStr);
	 
	}
	
	
	/*
	 * 
	 * 
	 */
	public String channelStr(String channel, String node)
	{
		String sR = "";
		
			
		  switch (channel) {
          case "hub":  sR = "dahdi/pseudo";
                break;
          case "simple":  sR = "SimpleUSB/usb_"+node;
                break;
          case "pi":  sR = "Pi/1";
                break;
          case "usbradio":  sR = "Radio/usb_"+node;
                break;
          case "pci":  sR = "Dahdi/1";
                break;
          case "bb":  sR = "Beagle/1";
                break;
          case "usrp":  sR = "USRP/127.0.0.1:34001:32001";
                break;
		  }   
                            
           
    
	 return(sR);
	}
}
