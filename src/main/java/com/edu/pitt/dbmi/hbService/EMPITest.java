package edu.pitt.dbmi.empi;
/* Richard Mau, rim20@pitt.edu, July 6, 2017
 * 
 * Situation:
 * When generating person_ids in the person_identity table on Neptune.
 * There is a case where a person does not have an EMPI ID at the time of ETL,
 * so the person is given a "2-series" person_id which is linked to an enterprise id.
 * 
 * Goal:
 * Instead of using an enterprise id, use this service to call the EMPI web services
 * to retrieve ids from the EMPI domain to link in the 2-series person_id. 
 * 
 * Status:
 * The current stage of this service is in its infancy where it's reading a flat
 * file to represent the case in the situation. The service then calls EMPI for each
 * record to retrieve an EMPI or MPACMRN id. The call to EMPI is done using the
 * first name, last name, and date of birth. 
 * 
 * Next Steps:
 * Validate search (i.e. there are cases like Patricia L Jackson where it returns a different 
 * person). Generate output to update into person_id.
 */
import com.initiate.bean.ArrayOfMemIdentWs;
import com.initiate.bean.MemIdentWs;
import com.initiate.bean.Member;
import com.initiate.bean.MemberSearchRequest;

import edu.pitt.dbmi.empi.scheduler.EmpiService;
import edu.pitt.dbmi.empi.scheduler.EmpiUtil;
import edu.pitt.dbmi.empi.scheduler.EmpiUtil.MemberSearchResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EMPITest
{
 // Reads a file into an ArrayList
 public static ArrayList<String> readFile(String file) throws IOException
 {
  ArrayList<String> list = new ArrayList<String>();
  try
  {
   FileReader fr = new FileReader(file);
   BufferedReader br = new BufferedReader(fr);
   String currentLine;
   
   while((currentLine = br.readLine()) != null)
   {
    list.add(currentLine);
   } 
   br.close();
   fr.close();
  }
  catch (IOException e) 
  {
   e.printStackTrace();
  }
  return list;
 }
 
 // Doesn't check current person_ids for duplication
 public static String mysqlOut(String code, String id) 
 {
  DateFormat df = new SimpleDateFormat("dd-MMM-yy");
  Date todayDate = Calendar.getInstance().getTime();
  String date = df.format(todayDate);
  String query = String.format(
    "INSERT INTO person_identity (" +
    "source_id, identity_id, identity_type_cd, " + 
    "identity_type_title, load_date) " +
    "VALUES (" +
    "hbService, %s, 3000, %s, %s)", id, code, date);
  return query;
 }
 
 public static void main(String [] args) throws ParseException
 { 
  // Set up service to EMPI
  EmpiService svc = new EmpiService();
  svc.initialze(); 
  MemberSearchRequest req;
  MemberSearchResult res;
  
  String row[];
  String fname, empi, code, id;
  // Retrieve patient info from txt file
  try 
  {
   ArrayList<String> patientList = readFile("EMPI_Scenarios2.txt");
   patientList.remove(0); // remove header
   for(int i = 0; i < patientList.size(); i++) {
    row = patientList.get(i).split("\\t");
    fname = row[0].replaceAll("\\s+", "");
    fname = fname.substring(1, fname.length()-1);
    empi = row[1];
    if(row.length > 2)
     req = EmpiUtil.searchByEmpiIdRequest(row[2]);
    else
     req = EmpiUtil.searchByEmpiIdRequest(empi);
    res = EmpiUtil.searchForMembers(svc, req);
    if(res == null) {
     req = EmpiUtil.searchByEmpiIdRequest(empi);
     res = EmpiUtil.searchForMembers(svc, req);
     if(res == null)
      continue;
    }
    for(Member memb : res.results)
    {
     System.out.println(fname);
     ArrayOfMemIdentWs pids = memb.getMemIdent();
     for(MemIdentWs pid : pids.getItem())
     {
      code = pid.getAttrCode();
      id = pid. getIdNumber(); 
      System.out.println("getMemIdnum: " + pid.getMemIdnum());
      System.out.println("getIdNumber: " + pid.getIdNumber());
      System.out.println("getMemRecno: " + pid.getMemRecno());
      System.out.println("getAttrCode: " + pid.getAttrCode());
      System.out.println();
     }
     System.out.println("----------------------");
    }
   }
  }
  catch(IOException e)
  {
   e.printStackTrace();
  }
 }
 
}
