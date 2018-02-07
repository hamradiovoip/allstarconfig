package org.allstar.spring.security.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.JSONException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.allstar.config.data.EchoLinkConfigData;
import org.allstar.config.data.RptConfigData;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;


import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.http.fileupload.IOUtils;


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
    public String basic() {
    	
    	
        return "user/basic";
    }

    
    /*
    @RequestMapping(value = "/basic",  method = RequestMethod.POST)
    public String basic(@Valid @ModelAttribute("save") RptConfigData data, 
    	      BindingResult result, ModelMap model) {
    	if (result.hasErrors()) {
            return "error";
    	}
    	
    	// model.addAttribute("name", data.getName());
        
    //	 save data user typed in and create a file to save to disk 
    	
    	
    	
        return "user/basic";
    }

*/

    @GetMapping("/iax")
    public String iax() {
        return "user/iax";
    }


    @GetMapping("/log")
    public String log() {
        return "user/log";
    }

    @GetMapping("/help")
    public String help() {
        return "user/help";
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
    @GetMapping("/rpt")
    public String rpt(Model model) {
    	
    	   	
    	RptConfigData rpt = new RptConfigData();
    	
    	
    	model.addAttribute("rpt", rpt); 
        	
        return "user/rpt";
    }
    
    @PostMapping("/rpt")
    public String rptSubmit(@ModelAttribute RptConfigData rpt,  Model model) 
    {   	
    	   	
    	
    	String node = rpt.nodeNumber;
    	String ch = rpt.rxChannel; 
    	String call= rpt.call;
    	String duplex= rpt.duplex;
    	
    	rpt = new RptConfigData(ch, node, call, duplex);
    	    	
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
    public String echolSubmit(@ModelAttribute EchoLinkConfigData echolink,  Model model) 
    {   	
    	   	    	
    	String  node = echolink.node;
    	String  pwd = echolink.pwd; 
    	String  name = echolink.name;
    	String  call= echolink.call;
    	String  astnode = echolink.astnode;
    	String  qth= echolink.qth;
    	String  email= echolink.email;    	

    	echolink = new EchoLinkConfigData(call, pwd, name, qth, email, node, astnode);

    	model.addAttribute("echolink", echolink);    	 	

        return "user/echoresults";
    }
    
    
    
    @GetMapping("/simpleusb")
    public String simpleusb() {
        return "user/simpleusb";
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
