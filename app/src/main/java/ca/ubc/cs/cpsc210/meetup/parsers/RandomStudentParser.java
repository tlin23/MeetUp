package ca.ubc.cs.cpsc210.meetup.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Student;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;

/**
 * Created by Yu-Tang Clan on 2015-03-25.
 */
public class RandomStudentParser {
    StudentManager studentManager = new StudentManager();

    String firstName;
    String lastName;
    int id;

    public Student parse(String input){
        try {
            JSONObject toParse = new JSONObject(input);
            firstName = toParse.getString("FirstName");
            lastName = toParse.getString("LastName");
            id = toParse.getInt("Id");
            studentManager.addStudent(lastName, firstName, id);
            JSONArray sections = toParse.getJSONArray("Sections");
            for (int i = 0; i < sections.length(); i++) {
                JSONObject section = sections.getJSONObject(i);
                String courseName = section.getString("CourseName");
                int courseNumber = section.getInt("CourseNumber");
                String sectionName = section.getString("SectionName");
                studentManager.addSectionToSchedule(id, courseName, courseNumber, sectionName);
            }
            return studentManager.get(id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
