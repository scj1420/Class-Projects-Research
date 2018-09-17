//
//  GPA.h
//  Lab 7
//
//  Created by Seong-Eun Cho on 6/13/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef GPA_h
#define GPA_h
#include <iostream>
#include <fstream>
#include "GPAInterface.h"
#include "Student.h"
using namespace std;

class GPA : public GPAInterface{
private:
    map<unsigned long long int, StudentInterface*> studentMap;
    set<StudentInterface*, Comparator> studentSet;
public:
    GPA(){}
    ~GPA(){
        clear();
    }
    map<unsigned long long int, StudentInterface*> getMap(){
        return studentMap;
    }
    set<StudentInterface*, Comparator> getSet(){
        return studentSet;
    }
    
    bool importStudents(string mapFileName, string setFileName){
        ifstream in_fileM(mapFileName);
        ifstream in_fileS(setFileName);
        if (in_fileM.fail()){
            return false;
        }
        if (in_fileS.fail()){
            return false;
        }
        string mapID;
        while (getline(in_fileM, mapID)){
            for (int i = 0; i < mapID.size(); i++){
                if (!isdigit(mapID[i])) return false;
            }
            unsigned long long int ID = stoi(mapID);
            string name, address, number;
            if (!getline(in_fileM, name)) return false;
            if (!getline(in_fileM, address)) return false;
            if (!getline(in_fileM, number)) return false;
            StudentInterface* stu = new Student(ID, name, address, number);
            studentMap.insert(pair<unsigned long long int, StudentInterface*>(ID, stu));
        }
        string setID;
        while (getline(in_fileM, mapID)){
            for (int i = 0; i < mapID.size(); i++){
                if (!isdigit(mapID[i])) return false;
            }
            unsigned long long int ID = stoi(mapID);
            string name, address, number;
            if (!getline(in_fileM, name)) return false;
            if (!getline(in_fileM, address)) return false;
            if (!getline(in_fileM, number)) return false;
            StudentInterface* stu = new Student(ID, name, address, number);
            studentSet.insert(stu);
        }
        return true;
    }
    
    bool importGrades(string fileName){
        ifstream in_file(fileName);
        if (in_file.fail()) return false;
        string strID;
        while (getline(in_file, strID)){
            int ID = stoi(strID);
            string course, grade;
            getline(in_file, course);
            getline(in_file, grade);
            double GPA = convertGPA(grade);
            map<unsigned long long int, StudentInterface*>::iterator it;
            it = studentMap.find(ID);
            if (it != studentMap.end()){
                studentMap[ID]->addGPA(GPA);
            }
            for (StudentInterface* s: studentSet){
                if (s->getID() == ID){
                    s->addGPA(GPA);
                }
            }
        }
        return true;
    }
    double convertGPA(string grade){
        if (grade == "A") return 4.0;
        else if (grade == "A-") return 3.7;
        else if (grade == "B+") return 3.4;
        else if (grade == "B") return 3.0;
        else if (grade == "B-") return 2.7;
        else if (grade == "C+") return 2.4;
        else if (grade == "C") return 2.0;
        else if (grade == "C-") return 1.7;
        else if (grade == "D+") return 1.4;
        else if (grade == "D") return 1.0;
        else if (grade == "D-") return 0.7;
        else return 0.0;
    }
    
    string querySet(string fileName){
        ifstream in_file(fileName);
        stringstream ss;
        string sID;
        if (in_file.fail()) return "";
        while (getline(in_file, sID)){
            int ID = stoi(sID);
            for (StudentInterface* s: studentSet){
                if (s->getID() == ID){
                    ss << s->getID() << s->getGPA() << s->getName() << endl;
                }
            }
        }
        return ss.str();
    }
    string queryMap(string fileName){
        ifstream in_file(fileName);
        stringstream ss;
        string sID;
        if (in_file.fail()) return "";
        while (getline(in_file, sID)){
            int ID = stoi(sID);
            map<unsigned long long int, StudentInterface*>::iterator it;
            it = studentMap.find(ID);
            if (it != studentMap.end()){
                ss << studentMap[ID]->getID() << studentMap[ID]->getGPA() << studentMap[ID]->getName() << endl;
            }
        }
        return ss.str();
    }
    
    void clear(){
        studentMap.clear();
        studentSet.clear();
    }
};


#endif /* GPA_h */
