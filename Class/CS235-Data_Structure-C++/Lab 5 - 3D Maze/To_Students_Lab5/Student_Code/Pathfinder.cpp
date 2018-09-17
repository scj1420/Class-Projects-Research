//
//  Pathfinder.cpp
//  Lab 5
//
//  Created by Seong-Eun Cho on 6/1/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <stdio.h>
#include "Pathfinder.h"
#include <sstream>
#include <fstream>
using namespace std;

Pathfinder::Pathfinder(){
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                maze[i][j][k] = 1;
            }
        }
    }
}

string Pathfinder::getMaze(){
    stringstream ss;
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                if (k < 4){
                    ss << maze[k][j][i] << " ";
                }
                else ss << maze[k][j][i];
            }
            ss << endl;
        }
        if (i < 4){
            ss << endl;
        }
    }
    return ss.str();
}

void Pathfinder::createRandomMaze(){
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                maze[k][j][i] = rand() % 2;
            }
        }
    }
}

bool Pathfinder::importMaze(string file_name){
    int count = 0;
    string smaze[5][5][5];
    ifstream in_file(file_name);
    if (in_file.fail()){
        return false;
    }
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                
                if (in_file >> smaze[k][j][i]) count++;
                if (smaze[k][j][i] != "1" && smaze[k][j][i] != "0") return false;
            }
        }
    }
    if (count != 125) return false;
    string dummy;
    if (in_file >> dummy) return false;
    if (smaze[0][0][0] != "1" || smaze[4][4][4] != "1") return false;
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                
                maze[k][j][i] = stoi(smaze[k][j][i]);
            }
        }
    }
    return true;
}

vector<string> Pathfinder::solveMaze(){
    find(0, 0, 0, 0, 0, 0, 0, 0);
    return path;
}

void Pathfinder::find(int r, int c, int d, int precedence, int order, int check, int final, int count){
    stringstream ss;
    if (count >= 1000){
        path.clear();
        return;
    }
    if (r == 4 && c == 4 && d == 4){
        ss << "(" << r << ", " << c << ", " << d << ")";
        path.push_back(ss.str());
    }
    
    else{
        if (maze[r+1][c][d] == 0 || r + 1 >= 5 || precedence == 4 || order == 4 || check == 4 || final == 4){
            if (maze[r][c+1][d] == 0 || c + 1 >= 5 || precedence == 5 || order == 5 || check == 5 || final == 5){
                if (maze[r][c][d+1] == 0 || d + 1 >= 5 || precedence == 6 || order == 6 || check == 6 || final == 6){
                    if (maze[r-1][c][d] == 0 || r - 1 < 0 || precedence == 1 || order == 1 || check == 1 || final == 1){
                        if (maze[r][c-1][d] == 0 || c - 1 < 0 || precedence == 2 || order == 2 || check == 2 || final == 2){
                            if (maze[r][c][d-1] == 0 || d - 1 < 0 || precedence == 3 || order == 3 || check == 3 || final == 3){
                                final = check;
                                check = order;
                                order = precedence;
                                find(r, c, d, 0, 0, check, final, count);
                            }
                            else{
                                ss << "(" << r << ", " << c << ", " << d << ")";
                                path.push_back(ss.str());
                                count++;
                                final = check;
                                check = order;
                                order = precedence;
                                find(r, c, d - 1, 6, order, check, final, count);
                            }
                        }
                        else{
                            ss << "(" << r << ", " << c << ", " << d << ")";
                            path.push_back(ss.str());
                            count++;
                            final = check;
                            check = order;
                            order = precedence;
                            find(r, c - 1, d, 5, order, check, final, count);
                        }
                    }
                    else{
                        ss << "(" << r << ", " << c << ", " << d << ")";
                        path.push_back(ss.str());
                        count++;
                        final = check;
                        check = order;
                        order = precedence;
                        find(r - 1, c, d, 4, order, check, final, count);
                    }
                }
                else{
                    ss << "(" << r << ", " << c << ", " << d << ")";
                    path.push_back(ss.str());
                    count++;
                    final = check;
                    check = order;
                    order = precedence;
                    find(r, c, d + 1, 3, order, check, final, count);
                }
            }
            else{
                ss << "(" << r << ", " << c << ", " << d << ")";
                path.push_back(ss.str());
                count++;
                final = check;
                check = order;
                order = precedence;
                find(r, c + 1, d, 2, order, check, final, count);
            }
        }
        else{
            ss << "(" << r << ", " << c << ", " << d << ")";
            path.push_back(ss.str());
            count++;
            final = check;
            check = order;
            order = precedence;
            find(r + 1, c, d, 1, order, check, final, count);
        }
    }
}

void Pathfinder::print(){
    for (int i = 0; i < path.size(); i++){
        cout << path[i] << endl;
    }
}