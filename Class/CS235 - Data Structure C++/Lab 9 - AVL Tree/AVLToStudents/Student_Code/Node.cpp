//
//  Node.cpp
//  Lab 6
//
//  Created by Seong-Eun Cho on 6/3/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include "Node.h"
using namespace std;

Node::Node (const int& the_data, Node* left_val = NULL, Node* right_val = NULL) : data(the_data), left(left_val), right(right_val){}

int Node::getData(){
    return data;
}

Node* Node::getLeftChild(){
    return left;
}

Node* Node::getRightChild(){
    return right;
}

int Node::getHeight(){
    return height;
}