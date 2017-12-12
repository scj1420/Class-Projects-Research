//
//  Student.h
//  Lab 7
//
//  Created by Seong-Eun Cho on 6/13/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef Student_h
#define Student_h
#include <iostream>
#include <sstream>
#include <iomanip>
#include "StudentInterface.h"
using namespace std;

class Student : public StudentInterface{
private:
    unsigned long long int ID;
    string name;
    string address;
    string number;
    double GPA;
    int credit;
public:
    Student(unsigned long long int ID, string name, string address, string number) : GPA(0), credit(0){
        this->ID = ID;
        this->name = name;
        this->address = address;
        this->number = number;
    }
    unsigned long long int getID(){
        return ID;
    }
    string getName(){
        return name;
    }
    string getGPA(){
        stringstream ss;
        ss << fixed << setprecision(2) << GPA;
        return ss.str();
    }
    void addGPA(double classGrade){
        GPA = ((GPA * credit) + classGrade) / (credit + 1);
        credit++;
    }
    string toString(){
        stringstream ss;
        ss << ID << "\n" << name << "\n" << address << "\n" << number << "\n" << getGPA();
        return ss.str();
    }
};

#endif /* Student_h */
