package tasklist;

import exceptions.DukeException;
import storage.Storage;

/**
 * Contains the list of operations such as list,done,delete and find
 */
public class TaskList extends Storage {

    /**
     * Lists all task in the task list to the user.
     */
    public static void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.print(i + 1 + ".");
            taskArrayList.get(i).printStatus();
        }
    }

    /**
     * Set the indexed task as done
     *
     * @param userInput contains the full user input
     * @throws NullPointerException if array is empty. e.g, over the size of the current array
     * @throws ArrayIndexOutOfBoundsException if index is out of bound e.g, 0.
     */
    public static void done(String userInput) {
        try {
            String text;
            int index = Integer.parseInt(userInput.substring(5));
            tasks[index - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            tasks[index - 1].printStatus();
            text = taskSentences.get(index - 1);
            text = text.replace("false", "true");
            taskSentences.set(index - 1, text);                              //rewrite and update the status
            Storage.writeFile();                                             //update file
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Index out of bound, please re-enter.");
        }
    }

    /**
     * Delete the indexed task
     *
     * @param userInput contains the full user input
     * @throws DukeException if index is out of bound
     */
    public static void delete(String userInput) {
        int index = Integer.parseInt(userInput.substring(7));

        try {
            if (index > taskCount || index < 1) {
                throw new DukeException();
            }
            System.out.println("Noted. I've removed this task:");
            taskArrayList.get(index - 1).printStatus();
            taskArrayList.remove(index - 1);
            taskSentences.remove(index - 1);
            taskCount = taskCount - 1;
            System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
            Storage.writeFile();                                    //update file after deleting
        } catch (DukeException e) {
            System.out.println("Index out of bound, please re-enter.");
        }
    }

    /**
     * Finds all task related to the keyword as given by the user
     * @param userInput contains the full user input
     */
    public static void find(String userInput) {

        String keyword = userInput.substring(5);
        int index = 1;
        int rememberIndex = -1;
        boolean hasFound = false;

        for (int i = 0; i < taskArrayList.size(); i++) {        //first loop to check if there is at least 1 match
            if (taskArrayList.get(i).getName().contains(keyword)) {
                rememberIndex = i;
                hasFound = true;
                break;                                          //break out of loop after finding 1 match
            }
        }

        if (hasFound) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = rememberIndex; i < taskArrayList.size(); i++) {    //loop again starting from the 1st match
                if (taskArrayList.get(i).getName().contains(keyword)) {
                    System.out.print(index++ + ".");
                    taskArrayList.get(i).printStatus();
                }
            }
        } else {
            System.out.println("No matching task found.");
        }
    }
}

