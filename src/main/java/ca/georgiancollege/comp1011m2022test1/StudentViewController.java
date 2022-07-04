package ca.georgiancollege.comp1011m2022test1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class StudentViewController implements Initializable {

    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> telephoneCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @FXML
    private TableColumn<Student, String> provinceCol;

    @FXML
    private TableColumn<Student, Integer> avgGradeCol;

    @FXML
    private TableColumn<Student, String> majorCol;

    @FXML
    private CheckBox ontarioCheckBox;

    @FXML
    private Label numOfStudentsLabel;

    @FXML
    private CheckBox honourRollCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    // Get the students Data from the Database
    public ArrayList<Student> students = DBManager.Instance().readStudentTable();
    @FXML
    private void applyFilter() {
        //applying various filter
        List<Student> studentbycode_list=new ArrayList<Student>();
        List<Student> student_ontario_list=new ArrayList<Student>();
        List<Student> final_list=new ArrayList<Student>();
        //clearing all data
        clear();
        //getting code value
        String code = areaCodeComboBox.getValue();
        //checking ontario check-box is checked or not
        Boolean ontario = ontarioCheckBox.isSelected();
        //checking honourRoll check-box is checked or not
        Boolean honourRoll = honourRollCheckBox.isSelected();
        if (code == "all" || code == null) {
            studentbycode_list=students;
        }
        else {
            //iterating through all list
            // adding all student data in studentbycode_list having selected code from combo-box
            for (var student : students) {
                if (student.phone_number.substring(0, 3).contains(code)) {
                    studentbycode_list.add(student);
                }
            }
        }
        //if ontario check-box is checked
        if (ontario) {
            //iterating through all list
            for (var student : studentbycode_list) {
                //Adding all student record in student_ontario_list that having province 'ON'
            if (student.province.contains("ON")) {
                student_ontario_list.add(student);
                }
            }
        }
        else{
            student_ontario_list=studentbycode_list;
        }
        //if honourRoll check-box is checked
        if(honourRoll==true){
            //iterating through all list
            for (var student : student_ontario_list) {
                //Adding all student data in final_list that having average grade>=80
                if (student.avg_grade>=80) {
                    final_list.add(student);
                }
            }
        }
        else{
            final_list=student_ontario_list;
        }
        //iterating through list
        for(var student: final_list)
        {
            //adding data to tableview
            tableView.getItems().addAll(student);
        }
        //setting number of student label
        numOfStudentsLabel.setText(String.valueOf("Number of Students: "+final_list.size()));
    }


    private void clear()  {
        //clear all data in tableview
        for ( int i = 0; i<tableView.getItems().size(); i++) {
            tableView.getItems().clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("student_number"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avg_grade"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));
        areaCodeComboBox.getItems().add("all");
        LoadData();
    }

    public void LoadData(){
        HashSet<String> codes = new HashSet<String>();
        // Display the Student Data in tableView
        for(var student: students)
        {
            //adding all data in table view
            tableView.getItems().addAll(student);
            // area code is the first 3 digits of their phone number.
            codes.add(student.phone_number.substring(0,3));
        }
        // Sorting HashSet using TreeSet
        TreeSet<String> treeSet = new TreeSet<String>(codes);
        for(var data: treeSet)
        {
            // adding area code to combobox
            areaCodeComboBox.getItems().add(data);
        }
        //setting the Number of Students in label
        numOfStudentsLabel.setText("Number of Students: "+String.valueOf(students.size()));
    }

}
