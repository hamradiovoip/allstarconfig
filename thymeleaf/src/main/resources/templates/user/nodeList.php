<?php

function nodetolist($n)
{
  $key = preg_split("/[T,]+/", $n);
  return $key;

}

        $nodeno =  '27265'; // htmlspecialchars($_GET["node"]) ;

        $url = "http://stats.allstarlink.org/jsondata.cgi"; // The URL...

        $ch = curl_init();                      // Initiate CURL 

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_ENCODING, 'gzip'); // Enable compression
        curl_setopt($ch, CURLOPT_URL, $url);    // Set the url 
        $result=curl_exec($ch);                 // Execute
        curl_close($ch);                        // Close CURL

        $j = json_decode($result, true); // Parse returned JSON data

         //var_dump($results);//testing

         //  var_dump($j);         // Dump all data for testing!
        $nodenumber = $nodeno;
        $k = $j[$nodenumber];     
        $nodes= $k["CONNS"];
        $totaltime =$k["TOTALTXTIME"];
        $keytime = $k["KEYTIME"];
        $totalexec=  $k["TOTALEXECDCOMMANDS"];
        $timestamp  = $k["TSTAMP"];
        $totalkeyups = $k["TOTALKEYUPS"];

        //      var_dump($k);//testing
        $keywords = nodetolist($nodes);

      printf("<h4>Nodes Connected to $nodenumber</h4><br>");
      date_default_timezone_set("America/New_York");
      echo date(DATE_RFC850);
      printf("<table  bgcolor=\"#ECECEC\" rules=\"all\" frames=\"void\"><th>Node </th><th>Call</th> <th>Freq</th> <th>Location</th><th> Node List </th><th>Coords </th>");//header

        foreach(array_keys($keywords) as $p)
        {

            if($p!=0)
            {
              $x = $j[$keywords[$p]];
              $loc= $x["LOC"]; 
              $place = "<a href=\"https://www.google.com/maps/place/$loc\"> $loc</a>"; // city,state etc on Google map
              $lat = $x["LAT"]; // latitude
              $long = $x["LON"]; // longitude
              $coords = "<a href=\"https://www.google.com/maps/place/$lat,$long\"> $lat,$long</a>"; // link for node location google map

              $call= $x["CALL"];
              $channel = $x["CHANNEL"];
              $allstarnode = $keywords[$p];
              $k2 = $j[$allstarnode];        
              $nodes1= $k2["CONNS"];

              $callsignweb= "<a href=\"http://kd3su.crabdance.com/maps/callAllstarNodes.php?callsign=$call\">$call</a>";
              $allstarnodeweb = "<a href= \"http://kd3su.crabdance.com/maps/nodeList.php?node=$allstarnode\"> $allstarnode</a>"; 
               
               echo "<tr><td> $allstarnodeweb</a> </td><td>$callsignweb</td><td>$channel</td><td>$place</td><td><font size =-1> $nodes1</font></td><td>$coords</td>";

               printf("</tr><P>");
  
            }

        } // for
        printf("</table> ");
        printf("<h3>$nodenumber Statistics </h3><P><table><tr><td> total time</td><td> $totaltime</td></tr><tr><td> keytime</td> <td>$keytime</td><tr><td> total exec time</td><td> $totalexec</td></tr><tr><td>time stamp</td><td> $timestamp</td></tr><tr><td>  keyups</td><td> $totalkeyups</td></tr></table> <br>");

?>

