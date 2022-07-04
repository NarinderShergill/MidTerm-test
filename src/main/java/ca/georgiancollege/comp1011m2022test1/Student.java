package ca.georgiancollege.comp1011m2022test1;

public class Student {
    //properties
    public int student_number;
    public String first_name;
    public String last_name;
    public String phone_number;
    public String address;
    public String province;
    public int avg_grade;
    public String major;

    //regex to check North American dialing plan
    public String phone_regex="^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
    //accepted province
    public String[] provincelist= new String[]{"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};

    //constructor
    public Student(int student_number, String first_name, String last_name, String telephone_number, String address, String province, int avg_grade, String major) {
        setStudent_number(student_number);
        setFirst_name(first_name);
        setLast_name(last_name);
        setTelephone_number(telephone_number);
        setAddress(address);
        setProvince(province);
        setAvg_grade(avg_grade);
        setMajor(major);
    }

   //getter and setter
    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        //implementing validation rule
        if(student_number < 200034000)
        {
            throw new IllegalArgumentException("The student number should be greater than 200034000");
        }
        else
        {
            this.student_number = student_number;
        }
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        //implementing validation rule
        if(first_name.length() <= 1)
        {
            throw new IllegalArgumentException("First name must be more than 1 character");
        }
        else
        {
            this.first_name = first_name;
        }
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        //implementing validation rule
        if(last_name.length() <= 1)
        {
            throw new IllegalArgumentException("Last name must be more than 1 character");
        }
        else
        {
            this.last_name = last_name;
        }
    }

    public String getTelephone_number() {
        return phone_number;
    }

    public void setTelephone_number(String telephone_number) {
        //implementing validation rule
        if(telephone_number.matches(phone_regex))
        this.phone_number = telephone_number;
        else
            throw new IllegalArgumentException("Telephone number should match the North American dialing plan");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        //implementing validation rule
        if(address.length() <= 6)
        {
            throw new IllegalArgumentException("Address must be more than 6 characters");
        }
        else
        {
            this.address = address;
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        //implementing validation rule
        int flag=0;
        for(int i=0;i<provincelist.length;i++){
            if(provincelist[i].equals(province)){
                flag++;
                this.province = province;
                break;
            }
        }
        if(flag==0)
        throw new IllegalArgumentException("Province should be in the list of \n\"AB\",\"BC\",\"MB\",\"NB\",\"NL\",\"NS\",\"NT\",\"NU\",\"ON\",”PE\",\"QC\",\"SK\",YT\"");
    }

    public int getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(int avg_grade) {
        //implementing validation rule
        if(avg_grade>=0  && avg_grade<= 100)
        {
            this.avg_grade = avg_grade;
        }
        else
        {
            throw new IllegalArgumentException("Average grade must be in the range of 0-100");
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        //implementing validation rule
        if(major.length() <= 5)
        {
            throw new IllegalArgumentException("Major must be greater than 5 characters");
        }
        else
        {
            this.major = major;
        }
    }
}
