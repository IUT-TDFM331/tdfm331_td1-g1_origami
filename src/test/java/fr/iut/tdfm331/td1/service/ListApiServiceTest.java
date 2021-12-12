package fr.iut.tdfm331.td1.service;

import fr.iut.tdfm331.td1.model.Employee;
import fr.iut.tdfm331.td1.model.Meeting;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit test file to test ListApiService class
 */
public class ListApiServiceTest {

    private ListApiService service;

    @Before
    public void setupService() {
        service = new ListApiService();
    }

    /**
     * Test to check if list of Meeting is ∞correctly generated
     */
    @Test
    public void getListMeetingWithSuccess() {
        List<Meeting> listMeetings = service.getListMeetings();
        List<Meeting> expectedListMeetings = ListMeetingsGenerator.LIST_MEETINGS;
        assertThat(listMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListMeetings.toArray()));
    }

    /**
     * Test to check it list of Employee is correctly generated
     */
    @Test
    public void getListEmployeeWithSuccess() {
        List<Employee> listEmployees = service.getListEmployees();
        List<Employee> expectedListEmployees = ListEmployeesGenerator.LIST_EMPLOYEES;
        assertThat(listEmployees, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListEmployees.toArray()));

    }

    /**
     * Test to check if a new Meeting object is correctly added to the list
     */
    @Test
    public void addNewMeetingWithSuccess() {

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);
        Assert.assertTrue(service.getListMeetings().contains(newMeeting));
    }

    /**
     * Test to check if a selected Meeting is correctly removed from list
     */
    @Test
    public void removeMeetingWithSuccess() {
        Meeting meetingToRemoveM1 = service.getListMeetings().get(0);
        service.removeMeeting(meetingToRemoveM1);
        Assert.assertFalse(service.getListMeetings().contains(meetingToRemoveM1));
    }
    /**
     * Test to check if a selected Meeting is correctly removed from list
     */
    @Test
    public void removeMeeting(){

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);
        service.removeMeeting(newMeeting);
        Assert.assertFalse(service.getListMeetings().contains(newMeeting));
    }
    /**
     * Test when the meeting exists
     */
    @Test
    public void findByObjectWithSuccess() throws MeetingNotFound {
        Meeting searchMeeting = service.getListMeetings().get(1);
        assertEquals(searchMeeting, service.findByObject(service.getListMeetings().get(1).getObjectMeeting()));
    }

    /**
     * Test when the meeting doesn't exists
     */
    @Test
    public void findByObjectWithFail() {
        String fauxMeeting= "Meeting 2";
        Meeting meetingToFound = service.getListMeetings().get(2);
        try {
            assertNotEquals(meetingToFound, service.findByObject(fauxMeeting));
        } catch (MeetingNotFound e) {
            e.printStackTrace();
        }

    }

    /**
     * Test when the employee exists
     */
    @Test
    public void findByNameWithSuccess() throws EmployeeNotFound{
            Assert.assertEquals(service.findByName(service.getListEmployees().get(0).getName()), service.getListEmployees().get(0));
    }
    /**
     * Test when the employee doesn't exists
     */
    @Test
    public void findByNameWithFail()  {
        String nom= "Tato";

        try {
            Assert.assertNotEquals(service.findByName(nom), service.getListEmployees().get(2).getName());
        } catch (EmployeeNotFound e) {
            e.printStackTrace();
        }
    }






}