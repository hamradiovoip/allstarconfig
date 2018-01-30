package org.allstar.config.data;


public class EchoLinkConfigData {

	
	String call = "NVALID";			//	; Change this!
	String pwd = "INVALID";			//	; Change this!
	String name = "YOUR NAME";			//; Change this!
	String qth = "INVALID";			//	; Change this!
	String email = "INVALID";			//	; Change this!
	String node = "000000" ;             //             ; Change this!
	//; Data for EchoLink Status Page
	String lat = "0.0";				//; Latitude in decimal degrees
	String lon = "0.0";				//; Longitude in decimal degrees
	String freq = "0.0";              //                ; not mandatory Frequency in MHz
	String tone = "0.0";              //                ; not mandatory CTCSS Tone (0 for none)
	String power = "0";				//; 0=0W, 1=1W, 2=4W, 3=9W, 4=16W, 5=25W, 6=36W, 7=49W, 8=64W, 9=81W (Power in Watts)
	String height = "0";				//; 0=10 1=20 2=40 3=80 4=160 5=320 6=640 7=1280 8=2560 9=5120 (AMSL in Feet)
	String gain = "0";				//; Gain in db (0-9)
	String dir = "0";				//	; 0=omni 1=45deg 2=90deg 3=135deg 4=180deg 5=225deg 6=270deg 7=315deg 8=360deg (Direction)
	String astnode = "1999";		//	; Change this!
	
	
	
/*
[el0]
call = INVALID				; Change this!
pwd = INVALID				; Change this!
name = YOUR NAME			; Change this!
qth = INVALID				; Change this!
email = INVALID				; Change this!
node = 000000                           ; Change this!
; Data for EchoLink Status Page
lat = 0.0				; Latitude in decimal degrees
lon = 0.0				; Longitude in decimal degrees
freq = 0.0                              ; not mandatory Frequency in MHz
tone = 0.0                              ; not mandatory CTCSS Tone (0 for none)
power = 0				; 0=0W, 1=1W, 2=4W, 3=9W, 4=16W, 5=25W, 6=36W, 7=49W, 8=64W, 9=81W (Power in Watts)
height = 0				; 0=10 1=20 2=40 3=80 4=160 5=320 6=640 7=1280 8=2560 9=5120 (AMSL in Feet)
gain = 0				; Gain in db (0-9)
dir = 0					; 0=omni 1=45deg 2=90deg 3=135deg 4=180deg 5=225deg 6=270deg 7=315deg 8=360deg (Direction)

maxstns = 20				; Max Stations

rtcptimeout = 10			; Max number of missed heartbeats from EL
recfile = /tmp/echolink_recorded.gsm	;
astnode = 1999				; Change this!
context = radio-secure			; Default in code is echolink-in

; Max 3 servers
server1 = nasouth.echolink.org
server2 = naeast.echolink.org
server3 = server3.echolink.org

; To deny w6xxx you would add the statement: deny = w6xxx
; To prohibit computer-based connections you would write: permit = *-*
; To allow access to only a select group of callsigns: permit = w6abc,w6def,...

; permit				; comma delimited list of callsign, type (-r)
; deny


; Remote text commands thru netcat:
; o.conip <IPaddress>    (request a connect)
; o.dconip <IPaddress>   (request a disconnect)
; o.rec                  (turn on/off recording)

; ipaddr
; port

#includeifexists custom/echolink.conf
*/
}
