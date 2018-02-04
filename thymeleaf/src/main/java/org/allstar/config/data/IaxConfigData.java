package org.allstar.config.data;

public class IaxConfigData {

	
	int bindport = 4569;
	String bindaddr ="192.168.0.1"; //not always used
	String disallow ="all";
	String allow = "";
	Boolean jitterbuffer = true;                                                                
	Boolean	forcejitterbuffer = true;                                                           
	int	dropcount = 2;                                                                     
	int maxjitterbuffer = 4000;                                                            
	int	maxjitterinterps = 10;                                                             
	int	resyncthreshold = 1000;                                                            
	int maxexcessbuffer = 80;                                                              
	int minexcessbuffer = 10;                                                              
	int	jittershrinkrate = 1;    
	int tos = 0x1E;                                                                  
	Boolean autokill = true;  //yes                                                                  
	Boolean delayreject = true;     //yes                                                            
	//		; iaxthreadcount = 30                                                              
	//		; iaxmaxthreadcount = 150  
	
	StringBuffer s;
	public String resultsStr = "none";
	
	
	String buildIAX()
	{
	
	   s.append(" ; Inter-Asterisk eXchange driver definition\n" + 
	   		"; http://docs.allstarlink.org/drupal/node/15 \n" + 
	   		"\n" + 
	   		"; ulaw, alaw, GSM and ADPCM should only be used, \n" + 
	   		"; the rest of the standard Asterisk codecs\n" + 
	   		"; (speex, ilbc, lpc10, etc) should be avoided.\n" + 
	   		"\n" + 
	   		"; The ulaw and alaw codecs have the best audio quality,\n" + 
	   		"; followed by ADPCM, and lastly GSM,\n" + 
	   		"; Bandwidth used is in the reverse order to audio quality. \n" + 
	   		"; GSM uses the least bandwidth, and alaw/ulaw the most. \n" + 
	   		"\n" + 
	   		"; CODEC         AUDIO QUALITY           BANDWIDTH (including IP and Ethernet headers)\n" + 
	   		"; ULAW          best                    87 kbps\n" + 
	   		"; ADPCM         good                    55 kbps\n" + 
	   		"; GSM           mediocre                36 kbps\n" + 
	   		"\n");
	   s.append("[general]\n" + 
	   		"bindport = "+ bindport +"			; bindport and bindaddr may be specified\n" + 
	   		"                                ; NOTE: bindport must be specified BEFORE\n" + 
	   		"				; bindaddr or may be specified on a specific\n" + 
	   		"				; bindaddr if followed by colon and port\n" + 
	   		"				;  (e.g. bindaddr=192.168.0.1:4569)\n" + 
	   		"\n" + 
	   		"; bindaddr = 192.168.0.1	; more than once to bind to multiple\n" + 
	   		"                                ; addresses, but the first will be the \n" + 
	   		"                                ; default\n" + 
	   		"\n" + 
	   		"disallow = all			; The permitted codecs for outgoing connections \n" + 
	   		"				; Audio Quality	Bandwidth\n" + 
	   		"allow = ulaw			; best		87 kbps\n" + 
	   		"allow = adpcm			; good		55 kbps\n" + 
	   		"allow = gsm			; medicore	36 kbps\n" + 
	   		" \n");
	   s.append(" jitterbuffer = yes                                                           \n" + 
	   		"forcejitterbuffer = yes                                                           \n" + 
	   		"dropcount = 2                                                                     \n" + 
	   		"maxjitterbuffer = 4000                                                            \n" + 
	   		"maxjitterinterps = 10                                                             \n" + 
	   		"resyncthreshold = 1000                                                            \n" + 
	   		"maxexcessbuffer = 80                                                              \n" + 
	   		"minexcessbuffer = 10                                                              \n" + 
	   		"jittershrinkrate = 1                                                              \n" + 
	   		"tos = 0x1E                                                                        \n" + 
	   		"autokill = yes                                                                    \n" + 
	   		"delayreject = yes                                                                 \n" + 
	   		"; iaxthreadcount = 30                                                             \n" + 
	   		"; iaxmaxthreadcount = 150   \n" + 
	   		"\n");
	   s.append(" ; register = 1999:123456@register.allstarlink.org	; This must be changed to your node number, password \n" + 
	   		    "     	; remove the leading \";\"\n");
	   s.append("                                          \n" + 
	   		"\n" + 
	   		"; Incoming radio connections\n" + 
	   		"\n" + 
	   		"[radio]\n" + 
	   		"type = user\n" + 
	   		"disallow = all\n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm\n" + 
	   		"\n" + 
	   		"codecpriority = host\n" + 
	   		"context = radio-secure\n" + 
	   		"transfer = no\n");
	   
	   s.append(" [iaxrpt]                        	; Connect from iaxrpt Username field (PC AllStar Client)\n" + 
	   		"type = user                       	; Notice type is user here <---------------\n" + 
	   		"context = iaxrpt			; Context to jump to in extensions.conf\n" + 
	   		"auth = md5\n" + 
	   		"secret = Your_Secret_Pasword_Here\n" + 
	   		"host = dynamic\n" + 
	   		"disallow = all                    \n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm                       \n" + 
	   		"transfer = no\n");
	   s.append(" [iaxrpt]                        	; Connect from iaxrpt Username field (PC AllStar Client)\n" + 
	   		"type = user                       	; Notice type is user here <---------------\n" + 
	   		"context = iaxrpt			; Context to jump to in extensions.conf\n" + 
	   		"auth = md5\n" + 
	   		"secret = Your_Secret_Pasword_Here\n" + 
	   		"host = dynamic\n" + 
	   		"disallow = all                    \n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm                       \n" + 
	   		"transfer = no\n");
	   s.append(" [iaxrpt]                        	; Connect from iaxrpt Username field (PC AllStar Client)\n" + 
	   		"type = user                       	; Notice type is user here <---------------\n" + 
	   		"context = iaxrpt			; Context to jump to in extensions.conf\n" + 
	   		"auth = md5\n" + 
	   		"secret = Your_Secret_Pasword_Here\n" + 
	   		"host = dynamic\n" + 
	   		"disallow = all                    \n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm                       \n" + 
	   		"transfer = no\n");
	   s.append(" [iaxclient]                     	; Connect from iax client (Zoiper...)\n" + 
	   		"type = friend                     	; Notice type here is friend <--------------\n" + 
	   		"context = iax-client              	; Context to jump to in extensions.conf\n" + 
	   		"auth = md5\n" + 
	   		"secret = Your_Secret_Password_Here\n" + 
	   		"host = dynamic\n" + 
	   		"disallow = all\n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm\n" + 
	   		"transfer = no\n");
	   s.append(" [iaxclient]                     	; Connect from iax client (Zoiper...)\n" + 
	   		"type = friend                     	; Notice type here is friend <--------------\n" + 
	   		"context = iax-client              	; Context to jump to in extensions.conf\n" + 
	   		"auth = md5\n" + 
	   		"secret = Your_Secret_Password_Here\n" + 
	   		"host = dynamic\n" + 
	   		"disallow = all\n" + 
	   		"allow = ulaw\n" + 
	   		"allow = adpcm\n" + 
	   		"allow = gsm\n" + 
	   		"transfer = no\n");
	   s.append(" [allstar-sys]\n" + 
	   		"type = user\n" + 
	   		"context = allstar-sys\n" + 
	   		"auth = rsa\n" + 
	   		"inkeys = allstar\n" + 
	   		"disallow = all\n" + 
	   		"allow = ulaw\n");
	   s.append("[allstar-public]\n" + 
	   		"type = user\n" + 
	   		"context = allstar-public\n" + 
	   		"auth = md5\n" + 
	   		"secret = allstar\n" + 
	   		"disallow = all\n" + 
	   		"allow = ulaw\n" + 
	   		"allow = gsm \n");
	   s.append("; The following should be un-commented to support Allstar Autopatch service\n" + 
	   		"; [allstar-autopatch]\n" + 
	   		"; type = peer\n" + 
	   		"; host = register.allstarlink.org\n" + 
	   		"; username = <One of the Node numbers on this server>\n" + 
	   		"; secret = <The node password for the above node>\n" + 
	   		"; auth = md5\n" + 
	   		"; disallow = all\n" + 
	   		"; allow = ulaw\n" + 
	   		"; transfer = no\n" + 
	   		"\n" + 
	   		"#includeifexists custom/iax.conf \n");
	 	
				 		
	   this.resultsStr= s.toString();
	   return(resultsStr);
	   
	}
}
