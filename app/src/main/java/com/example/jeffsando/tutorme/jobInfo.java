package com.example.jeffsando.tutorme;

public class jobInfo {
    protected String TutorName;
    protected String ClassNum;
    protected String TutorInfo;

    public jobInfo(final String TutorName, final String ClassNum, final String TutorInfo){

    }

    public String getTutorName(){
        return TutorName;
    }

    public String getClassNum(){
        return ClassNum;
    }

    public String getTutorInfo(){
        return TutorInfo;
    }

    public void setTutorName(String TutorName){
        this.TutorName = TutorName;
    }

    public void setClassNum(String ClassNum){
        this.ClassNum = ClassNum;
    }

    public void setTutorInfo(String TutorInfo){
        this.TutorInfo = TutorInfo;
    }


}
