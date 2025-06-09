import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WritingToJSON {

    public static void main(String[] args) {

        // Create the Student object
        Student student = new Student("Yusra", 21, "Computer Science");

        // Create Gson instance
        Gson gson = new Gson();

        // Read the existing JSON file (if it exists)
        JsonArray studentsArray = new JsonArray();
        try {
            // Try reading the existing JSON file into a JsonArray
            FileReader reader = new FileReader("students.json");

            // If the file exists and has data, load it into the JsonArray
            studentsArray = gson.fromJson(reader, JsonArray.class);
            reader.close();

        } catch (IOException e) 
        
        {
            System.out.println("No existing file found, starting fresh.");
        }

        // Convert the student object into a JSON object
        JsonObject studentJson = gson.toJsonTree(student).getAsJsonObject();

        // Add the new student data to the existing JSON array
        studentsArray.add(studentJson);

        // Write the updated array back to the same file
        try (FileWriter writer = new FileWriter("students.json")) 
        {
            gson.toJson(studentsArray, writer);  // Write the updated JSON array to the file
            System.out.println("Student added to students.json");
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }
}
