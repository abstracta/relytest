/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.ui.common;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class StartBrowser {
    
    public void start(String url){      
	String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
	
	try{

	    if (os.indexOf( "win" ) >= 0) {

	        // this doesn't support showing urls in the form of "page.html#nameLink" 
	        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

	    } else if (os.indexOf( "mac" ) >= 0) {

	        rt.exec( "open " + url);

            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

	        // Do a best guess on unix until we get a platform independent way
	        // Build a list of browsers to try, in this order.
	        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
	       			             "netscape","opera","links","lynx"};
	        	
	        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
	        StringBuffer cmd = new StringBuffer();
	        for (int i=0; i<browsers.length; i++)
	            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
	        	
	        rt.exec(new String[] { "sh", "-c", cmd.toString() });

           } else {
                return;
           }
       }catch (Exception e){
	    return;
       }
        
    }
    
}
