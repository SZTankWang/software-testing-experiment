package tarantula;

import java.util.ArrayList;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

/**
 * This class stores all the failed tests from a JUnit Test class.
 * It requires the the name of the JUnit test class as a parameter for the constructor.
 */

public class Runner {
    /** Holds a list of all the tests numbers that fail **/
    private ArrayList<Integer> failCases;
    public static ArrayList<Integer> failedCasesNum = new ArrayList<Integer>();
    public static int testCount=0; 
    /** Holds the results from the test program**/
    Result result;
     
    @SuppressWarnings("rawtypes")
    /**Initialize a Runner object**/
    public Runner(Class testClass){
        failCases = new ArrayList<Integer>();
        JUnitCore core = new JUnitCore();
        RunListener listener = new myListener();
        core.addListener(listener);
        result = core.run(testClass);
    }
    
    /** Loops through all the failures in result and gets the test header
     * pre: all the test headers must in the format test# (ex: test1, test 10) 
     * post: adds the test header integer segment to the failCases **/
    public void determineFailCases(){
    	System.out.println("run count: "+result.getRunCount()+"\n");
    	System.out.println("failure count: "+result.getFailureCount()+"\n");
        for (Failure failure : result.getFailures()) {
            String header = failure.getTestHeader();
            System.out.println("********");
            System.out.println(header);
            System.out.println("********");
            System.out.println(failure.getTrace());
            String header_segment = header.substring(0, header.indexOf("("));
            String test_case_num = header.substring(4, header_segment.length());
            failCases.add(Integer.parseInt(test_case_num));
        }
    }   
     
    /** Return the list containing the fail case indexes**/
    public ArrayList<Integer> getFailCases(){
        return failCases;
    }
}

class myListener extends RunListener{

	public void testFinished(Description description) throws Exception{
		Runner.testCount ++;
		System.out.println("this is test "+Runner.testCount);
	}
	public void testFailure(Failure failure) throws Exception{
		System.out.println("FAILURE, test "+Runner.testCount);
		Runner.failedCasesNum.add(Runner.testCount);		
	}
	
}