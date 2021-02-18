package duke;

import java.io.*;

public class Read extends Duke{

    static String home = System.getProperty("user.home");
    static String sentence;
    static String[] words;
    public Read(){
    }


    public static void readFile(){

        try{
            BufferedReader br = new BufferedReader(new FileReader(home+"/tasklist.txt")); //Read from this file
            while((sentence=br.readLine())!=null){
                words = sentence.split("\\|");
                switch(words[0]){
                    case "T":
                        tasks[count] = new ToDos(words[2]); //create new todo object
                        taskArrayList.add(tasks[count]);   //add todo object to arraylist
                        taskSentences.add("T"+"|"+words[1]+"|"+words[2]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                    case "D":
                        tasks[count] = new DeadLines(words[2],words[3]); //create new deadline object
                        taskArrayList.add(tasks[count]);   //add deadline object to arraylist
                        taskSentences.add("D"+"|"+words[1]+"|"+words[2]+words[3]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                    case "E":
                        tasks[count] = new Events(words[2],words[3]); //create new events object
                        taskArrayList.add(tasks[count]);   //add event object to arraylist
                        taskSentences.add("E"+"|"+words[1]+"|"+words[2]+words[3]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                }
            }

        }catch(Exception ex){
            System.out.println("No file at the moment");
        }

    }
}


