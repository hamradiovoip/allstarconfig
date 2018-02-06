package org.allstar.config.data;

public class SimpleUSBConfigData 
{

	StringBuffer s;
	public String resultsStr = "none";

	
	public String buildRptString()
	{
			
	 s.append("	; SimpleUSB configuration\n" + 
	 		"\n" + 
	 		"	[general]\n" + 
	 		"\n" + 
	 		"	[usb_27265]\n" + 
	 		"\n" + 
	 		"	eeprom = 0              ; EEPROM installed: 0,1\n" + 
	 		"	                        ; 0 = no (default)\n" + 
	 		"	                        ; 1 = yes\n" + 
	 		"\n" + 
	 		"	hdwtype = 0		; Leave this set to 0 for USB sound fobs modified using\n" + 
	 		"				; the instructions from usbfob.pdf. Use a setting of \n" + 
	 		"				; 1 is for Dingotel/Sph interfaces.\n" + 
	 		"\n" + 
	 		"	; Receiver parameters\n" + 
	 		"\n" + 
	 		"	rxboost = 0             ; 0 = 20db attenuator inserted, 1= 20db attenuator removed\n" + 
	 		"	                        ; Set to 1 for additonal gain if using a low-level receiver output\n" + 
	 		"\n" + 
	 		"	carrierfrom = usbinvert ; no,usb,usbinvert\n" + 
	 		"	                        ; no - no carrier detection at all\n" + 
	 		"	                        ; usb - from the COR line on the USB sound fob (Active high)\n" + 
	 		"	                        ; usbinvert - from the inverted COR line on the USB sound fob (Active low)\n" + 
	 		"\n" + 
	 		"	ctcssfrom = usbinvert	; no,usb,usbinvert\n" + 
	 		"	                        ; no - CTCSS decoding, system will be carrier squelch\n" + 
	 		"	                        ; usb - CTCSS decoding using input from USB sound fob (Active high)\n" + 
	 		"	                        ; usbinvert - from the inverted CTCSS line on the USB sound fob (Active low)\n" + 
	 		"\n" + 
	 		"	deemphasis = no         ; enable de-emphasis (input from discriminator)\n" + 
	 		"\n" + 
	 		"	plfilter = no           ; enable PL filter\n" + 
	 		"\n" + 
	 		"	;rxondelay = 0		; number of 20ms intervals to hold off receiver turn-on indication\n");
	 s.append("\n" + 
	 		"\n" + 
	 		"	; Transmitter parameters\n" + 
	 		"\n" + 
	 		"	txmixa = voice          ; Left channel output (A): no,voice\n" + 
	 		"	                        ; no - Do not output anything\n" + 
	 		"	                        ; voice - output voice only\n" + 
	 		"\n" + 
	 		"	txmixb = no		; Right channel output (B): no,voice\n" + 
	 		"	                        ; no - Do not output anything\n" + 
	 		"	                        ; voice - output voice only\n" + 
	 		"\n" + 
	 		"	txboost = 0\n" + 
	 		"\n" + 
	 		"	invertptt = 0           ; Invert PTT: 0,1\n" + 
	 		"	                        ; 0 - ground to transmit\n" + 
	 		"	                        ; 1 - open to transmit\n" + 
	 		"\n" + 
	 		"	preemphasis = 0		; Perform standard 6db/octave pre-emphasis\n" + 
	 		"\n" + 
	 		"	; pager = no            ; no,a,b (e.g. pager = b means \"put the normal repeat audio on channel A, and the pager audio on channel B\")\n" + 
	 		"\n" + 
	 		"	; duplex3 = 0           ; duplex 3 gain setting (0 to disable)\n" + 
	 		"\n" + 
	 		"	duplex = 0              ; Duplex 0,1\n" + 
	 		"	                        ; 0 - half duplex\n" + 
	 		"	                        ; 1 - full duplex\n" + 
	 		"	                        \n");
                      		
			 		
	  this.resultsStr= s.toString();
	  return(resultsStr);
	}
	
}
