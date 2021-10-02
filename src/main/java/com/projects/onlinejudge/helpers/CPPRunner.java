package com.projects.onlinejudge.helpers;

import com.projects.onlinejudge.dto.RunRequest;
import com.projects.onlinejudge.dto.RunResponse;
import com.sun.tools.javac.util.Pair;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class CPPRunner implements IRunner{
    @Override
    public RunResponse runTests(RunRequest attribute)  {
        Integer passedCount =0 , failedCount =0;
        try {

            // TODO : service2.call(attribute) For future

            String lang= attribute.getLang();
            String userId= attribute.getUserId();
            String submissionId= attribute.getSubmissionId();
            String name_of_the_problem = attribute.getQname();

            // Code is compiled and I got the executable file
            String filePath = "/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/tempFiles/";
            File sourceFile = new File(filePath.trim()+"sourceFile.txt");
            File cppFile =  new File(filePath.trim()+name_of_the_problem+".cpp");
            copyContent(sourceFile,cppFile);

            // Count the total input files
            File questionDirectory = new File("/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/"+ name_of_the_problem +"/tests");

            String[] pathnames = questionDirectory.list(filter);
            int size = pathnames.length;
            ArrayList<Pair<Integer,String>>  testResults = new ArrayList<>();
            compileCode(name_of_the_problem);;
            for ( int input = 1; input <= size; input++){
                /*
                    Input File: /Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/Addition/tests/input-1.txt
                    User OutputFile: /Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/Addition/tests/user-output-1.txt

                 */
                String inputFile="/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/"+name_of_the_problem+"/tests/input-" + input+".txt";
                String useroutputFile="/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/"+name_of_the_problem+"/tests/user-output-" + input+".txt";
                String actualoutputFile="/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/"+name_of_the_problem+"/tests/output-" + input+".txt";
//
                run_one_test(name_of_the_problem,inputFile,useroutputFile);
                String result = compare_one_test(useroutputFile,actualoutputFile);
                System.out.println("Input: "+input +" => " + result);
                Pair<Integer,String> testResult = new Pair<>(input,result);
                testResults.add(testResult);
                if(result.equals("Passed")) {
                    passedCount++;
                } else {
                    failedCount++;
                }

            }
            RunResponse runResponse = new RunResponse(passedCount, failedCount,testResults);


            return runResponse;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    public void compileCode(String nameOfProblem) {
        String filePath = "/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/tempFiles/";
        String compileCode = "cd " + filePath + " && g++ -std=c++11 " + nameOfProblem + ".cpp -o " + nameOfProblem + ".out";
        try {
            // Process to compile the code
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("bash", "-c", compileCode);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            printResults(p);
            System.out.println("Code Compiled Successfully");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Command to run and test
            cd /Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/tempFiles/ && g++ -std=c++11 Addition.cpp -o Addition.out
            Projects/codefoxes/OnlineJudge/Questions/Addition/tests/sample-input-1.txt > /Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/Questions/Addition/tests/sample-output-1.txt

     */
    public void run_one_test(String nameOfProblem, String  input_file_name, String output_file_name){
        String filePath = "/Users/amankumarkeshu/Documents/Projects/codefoxes/OnlineJudge/tempFiles/";

        String runAndOutput = "cd "+ filePath+" && ./"+nameOfProblem +".out" + " < " + input_file_name + " > " + output_file_name;
        try {

            // Process to run and execute the code
            ProcessBuilder builder2 = new ProcessBuilder();
            builder2.command("bash","-c", runAndOutput);
            builder2.redirectErrorStream(true);
            Process p2 = builder2.start();
            printResults(p2);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String compare_one_test(String userOutput, String actualOutput ) {
//        File userOutputFile = new File(userOutput);
//        File actualOutputFile = new File(actualOutput);
        String userText = removeSpace(userOutput);
        String actualText = removeSpace(actualOutput);
        if(userText.equals(actualText)){
            return "Passed";
        }
        else return "Failed";
        // only sample
    }
    public String removeSpace( String file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(file), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    // Printing the terminal output after code execution
    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    // This filter will only include files starting  with input
    FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(File f, String name) {
            return name.startsWith("input");
        }
    };

    public static void copyContent(File a, File b) throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);
        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }
}
