package org.allstar.config.data;

public class IaxConfigData {

	
	int bindport = 4569;
	String bindaddr ="192.168.0.1"; //not always used
	String disallow ="all";
	String allow = "";
	/*allow = ulaw			; best		87 kbps
      allow = adpcm			; good		55 kbps
      allow = gsm			; medicore	36 kbps
     */
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
			
			
	/*
	 * 
	 ; Inter-Asterisk eXchange driver definition
; http://docs.allstarlink.org/drupal/node/15 

; ulaw, alaw, GSM and ADPCM should only be used, 
; the rest of the standard Asterisk codecs
; (speex, ilbc, lpc10, etc) should be avoided.

; The ulaw and alaw codecs have the best audio quality,
; followed by ADPCM, and lastly GSM,
; Bandwidth used is in the reverse order to audio quality. 
; GSM uses the least bandwidth, and alaw/ulaw the most. 

; CODEC         AUDIO QUALITY           BANDWIDTH (including IP and Ethernet headers)
; ULAW          best                    87 kbps
; ADPCM         good                    55 kbps
; GSM           mediocre                36 kbps


[general]
bindport = 4569			; bindport and bindaddr may be specified
                                ; NOTE: bindport must be specified BEFORE
				; bindaddr or may be specified on a specific
				; bindaddr if followed by colon and port
				;  (e.g. bindaddr=192.168.0.1:4569)

; bindaddr = 192.168.0.1	; more than once to bind to multiple
                                ; addresses, but the first will be the 
                                ; default

disallow = all			; The permitted codecs for outgoing connections 
				; Audio Quality	Bandwidth
allow = ulaw			; best		87 kbps
allow = adpcm			; good		55 kbps
allow = gsm			; medicore	36 kbps

jitterbuffer = yes                                                                
forcejitterbuffer = yes                                                           
dropcount = 2                                                                     
maxjitterbuffer = 4000                                                            
maxjitterinterps = 10                                                             
resyncthreshold = 1000                                                            
maxexcessbuffer = 80                                                              
minexcessbuffer = 10                                                              
jittershrinkrate = 1                                                              
tos = 0x1E                                                                  
autokill = yes                                                                    
delayreject = yes                                                                 
; iaxthreadcount = 30                                                              
; iaxmaxthreadcount = 150   

; register = 1999:123456@register.allstarlink.org	; This must be changed to your node number, password 
                                                	; remove the leading ";"

; Incoming radio connections

[radio]
type = user
disallow = all
allow = ulaw
allow = adpcm
allow = gsm

codecpriority = host
context = radio-secure
transfer = no

[iaxrpt]                        	; Connect from iaxrpt Username field (PC AllStar Client)
type = user                       	; Notice type is user here <---------------
context = iaxrpt			; Context to jump to in extensions.conf
auth = md5
secret = Your_Secret_Pasword_Here
host = dynamic
disallow = all                    
allow = ulaw
allow = adpcm
allow = gsm                       
transfer = no

[iaxclient]                     	; Connect from iax client (Zoiper...)
type = friend                     	; Notice type here is friend <--------------
context = iax-client              	; Context to jump to in extensions.conf
auth = md5
secret = Your_Secret_Password_Here
host = dynamic
disallow = all
allow = ulaw
allow = adpcm
allow = gsm
transfer = no

[allstar-sys]
type = user
context = allstar-sys
auth = rsa
inkeys = allstar
disallow = all
allow = ulaw

[allstar-public]
type = user
context = allstar-public
auth = md5
secret = allstar
disallow = all
allow = ulaw
allow = gsm

; The following should be un-commented to support Allstar Autopatch service
; [allstar-autopatch]
; type = peer
; host = register.allstarlink.org
; username = <One of the Node numbers on this server>
; secret = <The node password for the above node>
; auth = md5
; disallow = all
; allow = ulaw
; transfer = no

#includeifexists custom/iax.conf
*/
}
