package org.allstar.spring.security.web;

import java.io.BufferedReader;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.JSONException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.allstar.config.data.ConfigData;
import org.allstar.config.data.EchoLinkConfigData;
import org.allstar.config.data.IaxConfigData;
import org.allstar.config.data.RptConfigData;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;


import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.allstar.config.data.SimpleUSBConfigData;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "index";
    }
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
   

    @GetMapping("/error")
    public String error() {
    	
        return "error/error";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
    
    @GetMapping("/basic")
    public String basic(Model model) {    	
    	
    	ConfigData basicdata= new ConfigData();
    	
    	
    	model.addAttribute("basicdata", basicdata); 
        	
        return "user/basic";
        
    }
    
   
    @PostMapping("/basic")    
    public void basicSubmit(HttpServletResponse response,
    		@ModelAttribute @Valid final ConfigData basicdata,
    		Model model,
            BindingResult bindingResult,
            HttpSession session) 
    {   	
     	
      	response.setContentType("application/zip");
      	response.setStatus(HttpServletResponse.SC_OK);
      	response.addHeader("Content-Disposition", "attachment; filename=\"configs.zip\"");
    	
    	//File f = new File("configs.zip");

    	basicdata.buildConfigfiles();

    	String resultsSimpleUsbStr = basicdata.getSimpleUsbStr();
    	String resultsRptStr = basicdata.getRptStr();
    	String resultsIaxStr = basicdata. getIaxStr();    
    	String resultsExtStr = basicdata.getExtStr();
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	ZipOutputStream zos = null;

    	try
    	{
          zos = new ZipOutputStream(baos);
    	  /* File is not on the disk, test.txt indicates only the file name to be put into the zip */
          
    	  ZipEntry entrySimpleUsb = new ZipEntry("simpleusb.conf");
    	  
    	  zos.putNextEntry(entrySimpleUsb);
    	  zos.write(resultsSimpleUsbStr.getBytes());
    	  zos.closeEntry();
    	  
    	  ZipEntry entryRpt = new ZipEntry("rpt.conf");
    	  zos.putNextEntry(entryRpt);
    	  zos.write(resultsRptStr.getBytes());
    	  zos.closeEntry();
    	  
    	  ZipEntry entryIax = new ZipEntry("iax.conf");
    	  zos.putNextEntry(entryIax);
    	  zos.write(resultsIaxStr.getBytes());
    	  zos.closeEntry();
    	  
    	  
    	  ZipEntry entryExt = new ZipEntry("extensions.conf");
    	  zos.putNextEntry(entryExt);
    	  zos.write(resultsExtStr.getBytes());
    	  zos.closeEntry();    	  
    	  
    	  String saveNode = basicdata.saveNodeData();
    	  ZipEntry entrySave = new ZipEntry("savenode.conf");
    	  zos.putNextEntry(entrySave);
    	  zos.write(saveNode.getBytes());
    	  zos.closeEntry();    	  
    	  
    	  zos.close();    
    	  baos.close();
         
          response.getOutputStream().write(baos.toByteArray());
          response.getOutputStream().close();
          response.getOutputStream().flush();
    	  
    	  

    	  /* use more Entries to add more files
    	     and use closeEntry() to close each file entry */

    	} catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}


    	model.addAttribute("basicdata", basicdata);    	 	

    }
    

    @GetMapping("/log")
    public String log() {
        return "user/log";
    }

    @GetMapping("/help")
    public String help() {
        return "user/help";
    }
    

    @GetMapping("/dialfaq")
    public String dialfaq() {
        return "user/dialfaq";
    }
    

    @GetMapping("/installhelp")
    public String installhelp() {
        return "user/installhelp";
    }
    
    
    @GetMapping("/live")
    public String live() {
        return "user/live";
    }
    @GetMapping("/userconfig")
    public String userconfig() {
        return "user/userconfig";
    }
    
    @GetMapping("/passwd")
    public String passwd() {
        return "user/passwd";
    }
    
    @GetMapping("/controller")
    public final ModelAndView  controller() {
    	final ModelAndView mav = new ModelAndView("user/controller");
       	
    	HttpHost proxy = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();    	
    	
    	HttpGet getRequest = new HttpGet("http://stats.allstarlink.org/jsondata.cgi");
    	getRequest.addHeader("accept", "application/json");

    	HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	if (response.getStatusLine().getStatusCode() != 200) {
    	    throw new RuntimeException("Failed : HTTP error code : "
    	             + response.getStatusLine().getStatusCode());
    	}

    	BufferedReader br = null;
		try {
			br = new BufferedReader(
			    new InputStreamReader( 
			        (response.getEntity().getContent())
			    )
			);
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	StringBuilder content = new StringBuilder();
    	String line;
    	try 
    	{
			while (null != (line = br.readLine())) 
			{
			    content.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
   
    	
     	@SuppressWarnings("deprecation")
    		JSONParser parser = new JSONParser();
        	 Object obj = null;
			try
			{
				obj = parser.parse(content.toString());
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		//	 JSONArray array = (JSONArray) obj;
						 	 
			 
			// JSONArray array = new JSONArray();
			 
			// array.add(obj);
			/*
			    for(int i=0;i<array.size();i++)
			    {
			        JSONObject site = (JSONObject)array.get(i); // Exception happens here.
			        System.out.println(site.get("article"));
			       }
			 */
		//JSONArray arr = ((Object) obj).getJSONArray("posts");
			 
  //  	 JSONObject site = (JSONObject)array.get(0); 
    	//  String test = (String) site.get("27265");
    	
  //  	if(site != null)
  //  		mav.addObject("json",site); // array.get(0));
  //  	else
    	
 //   		mav.addObject("json", "ERROR");
    	return mav; 
       
    }
    
    @GetMapping("/audio")
    public String audio() {
        return "user/audio";
    }

    @GetMapping("/home")
    public String home() {
        return "user/home";
    }
    

    @GetMapping("/iax")
    public String iax(Model model)
    {    	
    	IaxConfigData iax = new IaxConfigData();
    	model.addAttribute("iax", iax);    	
        return "user/iax";
    }
    
    @PostMapping("/iax")
    public ModelAndView iaxSubmit(@ModelAttribute @Valid final IaxConfigData iax,  Model model,
    		 BindingResult bindingResult)
    {

    	String bindport =iax.getbindport();
    	String node = iax.getnode(); 
    	String call = iax.getcall();
    	String pwd = iax.getpwd();    	
    	
    	iax.buildIAX();
    	
    	model.addAttribute("iax", iax);  	
    	
    	return new ModelAndView( "user/iaxresults");    	

    }

    
    @GetMapping("/rpt")
    public String rpt(Model model) 
    {    	
    	   	
    	RptConfigData rpt = new RptConfigData();   	
    	
    	model.addAttribute("rpt", rpt); 
        	
        return "user/rpt";
    }
    
    @PostMapping("/rpt")
    public String rptSubmit(@ModelAttribute @Valid final RptConfigData rpt,  Model model,
    		BindingResult bindingResult) 
    { 	 	   	    	
    	String node = rpt.nodeNumber;
    	String ch = rpt.rxchannel; 
    	String call= rpt.call;
    	String duplex= rpt.duplex;
    	
    	//rpt = new RptConfigData(ch, node, call, duplex);
    	
    	rpt.buildRptString();   	
    	model.addAttribute("rpt", rpt);    	 	
    
        return "user/rptresults"; 
    }
    
   
  
    @GetMapping("/echolink")
    public  String echolink(Model model) {    	
    	   	
    	EchoLinkConfigData echolink = new EchoLinkConfigData();    	
    	
    	model.addAttribute("echolink", echolink); 
        	
        return "user/echolink";
    }
    
    @PostMapping("/echolink")
    public String echolSubmit(@ModelAttribute @Valid final EchoLinkConfigData echolink,  Model model,
    		BindingResult bindingResult) 
    {   	
    	   	    	
    	String  node = echolink.node;
    	String  pwd = echolink.pwd; 
    	String  name = echolink.name;
    	String  call= echolink.call;    	
    	String  qth= echolink.qth;
    	String  email= echolink.email;
    	String  astnode = echolink.astnode;
    	String  lat = echolink. lat;
    	String  lon = echolink.lon;
    			
    	echolink.buildEcholink();

    	model.addAttribute("echolink", echolink);    	 	

        return "user/echoresults";
    }
      
    
    @GetMapping("/simpleusb")
    public String simpleusb(Model model) {    	
    	
    	SimpleUSBConfigData simpleusb = new SimpleUSBConfigData();    	
    	
    	model.addAttribute("simpleusb", simpleusb); 
        	
        return "user/simpleusb";
        
    }
    
    @PostMapping("/simpleusb")
    public String simpleusbSubmit(@ModelAttribute @Valid final SimpleUSBConfigData simpleusb1,  Model model,
    		BindingResult bindingResult) 
    {   	
    	
    	String carrierfrom = simpleusb1.getcarrierfrom();			
		String ctcssfrom = simpleusb1.getctcssfrom();	
		String astnode = simpleusb1.getastnode();	
    	   	    	    			
		SimpleUSBConfigData simpleusb = new SimpleUSBConfigData(carrierfrom, ctcssfrom, astnode);  	
    	
	
    	model.addAttribute("simpleusb", simpleusb);    	 	

        return "user/simpleusbresults";
    }
    
        
    @GetMapping("/status")
    public String status() {
        return "user/status";
    }

    @GetMapping("/webtrans")
    public String webtrans() {
        return "user/webtrans";
    }
    
  /*  
    @RequestMapping(value="/getpdf", method=RequestMethod.POST)
    public ResponseEntity<byte[]> getPDF(@RequestBody String json) {
        // convert JSON to Employee 
        Employee emp = convertSomehow(json);

        // generate the file
        PdfUtil.showHelp(emp);

        // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
        byte[] contents = (...);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    }
*/
    
    
@RequestMapping(value="/zip", produces="application/zip")
public void zipFiles(HttpServletResponse response) throws IOException {

    //setting headers  
    response.setStatus(HttpServletResponse.SC_OK);
    response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

    // create a list to add files to be zipped
    ArrayList<File> files = new ArrayList<>(2);
    files.add(new File("README.md"));

    // package files
    for (File file : files) {
        //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        FileInputStream fileInputStream = new FileInputStream(file);

        IOUtils.copy(fileInputStream, zipOutputStream);

        fileInputStream.close();
        zipOutputStream.closeEntry();
    }    

    zipOutputStream.close();
    
    
    
}



    
    public class NameBean implements Serializable{
    	public String name;
    	public String lastname;
    	public int age;

    	public String getName() {
    	    return name;
    	}

    	public void setName(String name) {
    	    this.name = name;
    	}

    	}
    
    
    
}
